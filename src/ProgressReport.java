import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ProgressReport {
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    private String Project_description;
    private String team_id;
    private String advisor_id;
    private String title;
    private static final double DIFFERENCE_THRESHOLD = 0.3;
    private String timeStatus;

    ProgressReport(String Project_description, String team_id, String advisor_id, String title) {
        this.Project_description = Project_description;
        this.team_id = team_id;
        this.advisor_id = advisor_id;
        this.title = title;
    }
    ProgressReport() {

    }

    public String getProject_description() {
        return Project_description;
    }

    public String getTeam_id() {
        return team_id;
    }

    public String getAdvisor_id() {
        return advisor_id;
    }

    public String getTitle() {
        return title;
    }

    // Method to retrieve texts from the database
    private static List<String> getTextsFromDatabase() {
        List<String> texts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement stmt = connection.createStatement()) {
            String sql = "SELECT synopsis FROM ProgressReports";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                texts.add(rs.getString("synopsis"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texts;
    }

    private void insertProjectIntoDatabase() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO ProgressReports (team_id,advisor_id,title,synopsis,Status,submission_date,timeStatus) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, this.getTeam_id());
            stmt.setString(2, this.getAdvisor_id());
            stmt.setString(3, this.getTitle());
            stmt.setString(4, this.getProject_description());
            stmt.setString(5, "In progress");
            stmt.setTimestamp(6, Timestamp.valueOf(currentDateTime)); // Corrected line
            stmt.setString(7, calculateTimeStatus());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "The project submitted successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "The project is not submitted please make sure the available user and instructor Id", "Oops", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void insertCopyProjectIntoDatabase(String comment, String status) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO ProgressReports (team_id,advisor_id,title,synopsis,Status,submission_date,timeStatus, comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, this.getTeam_id());
            stmt.setString(2, this.getAdvisor_id());
            stmt.setString(3, this.getTitle());
            stmt.setString(4, this.getProject_description());
            stmt.setString(5, status);
            stmt.setTimestamp(6, Timestamp.valueOf(currentDateTime)); // Corrected line
            stmt.setString(7, calculateTimeStatus());
            stmt.setString(8, comment);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "The project submitted successfully");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The project is not submitted please make sure the available user and instructor Id", "Oops", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method to compute Jaccard similarity between two texts
    private static double computeJaccardSimilarity(String text1, String text2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(text1.split("\\s+")));
        Set<String> set2 = new HashSet<>(Arrays.asList(text2.split("\\s+")));

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / (double) union.size();
    }

    public void submit() {
        List<String> textsFromDatabase = getTextsFromDatabase();
        boolean isTextSimilar = false;

        for (String dbText : textsFromDatabase) {
            double similarity = computeJaccardSimilarity(this.getProject_description(), dbText);
            if (similarity >= DIFFERENCE_THRESHOLD) {
                isTextSimilar = true;
                break;
            }
        }

        // If the text is not similar to any text in the database, insert it
        if (!isTextSimilar) {
            insertProjectIntoDatabase();
        } else {
            String comment = "The submitted project is coping from previous works.";
            String status = "Failed";
            insertCopyProjectIntoDatabase(comment, status);
        }
    }

    public String calculateTimeStatus() {
        String SqlQuery = "select d.finalSubmitionDate, d.team_id from DEADLINE D join teams t ON d.team_id = t.team_id AND d.team_id = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(SqlQuery)) {
            stmt.setString(1, getTeam_id());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // Retrieve the timestamp from the result set
                    Timestamp tsFromSQL = rs.getTimestamp("finalSubmitionDate");

                    // Calculate difference between retrieved timestamp and current time
                    LocalDateTime currentTime = LocalDateTime.now();
                    LocalDateTime sqlTime = tsFromSQL.toLocalDateTime();

                    long diffInMinutes = ChronoUnit.MINUTES.between(sqlTime, currentTime);

                    // Print the difference
                    if (diffInMinutes > 0) {
                        timeStatus = "Late submitted";
                    } else if(diffInMinutes < 0) {
                        timeStatus = "Early submitted";
                    } else {
                        timeStatus = "Submitted On time";
                    }
                    return timeStatus;
                } else {
                    System.out.println("No timestamp found in the database.");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
