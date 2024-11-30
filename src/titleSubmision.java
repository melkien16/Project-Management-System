import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class titleSubmision {
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    private String project_description;
    private String team_id;
    private String Ptitle;
    private String pStatus;
    private String notification;
    private static final double DIFFERENCE_THRESHOLD = 0.3;

    public titleSubmision(String team_id, String Ptitle, String project_description) {
        this.project_description = project_description;
        this.Ptitle = Ptitle;
        this.team_id = team_id;
    }
    public titleSubmision() {

    }

    public String getProject_description() {
        return project_description;
    }

    public String getTeam_id() {
        return team_id;
    }

    public String getPtitle() {
        return Ptitle;
    }

    public String getpStatus() {
        return this.pStatus;
    }
    private static List<String> getProjectDescriptionFromDatabase() {
        List<String> projectDescription = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement stmt = connection.createStatement()) {
            String sql = "SELECT pdescription FROM Title_approval";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                projectDescription.add(rs.getString("pdescription"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectDescription;
    }

    private static List<String> getTitleFromDatabase() {
        List<String> projectTitle = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement stmt = connection.createStatement()) {
            String sql = "SELECT pTitle FROM Title_approval";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                projectTitle.add(rs.getString("pTitle"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectTitle;
    }

    private void insertTitleIntoDatabase() {
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO Title_approval (team_id,pTitle,pdescription,status) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, this.getTeam_id());
            stmt.setString(2, this.getPtitle());
            stmt.setString(3, this.getProject_description());
            stmt.setString(4, "under review");
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "The project submitted successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "The project is not submitted please make sure the available user and instructor Id", "Oops", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void insertCopyTitleIntoDatabase(String comment, String status) {
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO Title_approval (team_id,pTitle,pdescription,status, recommendation) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, this.getTeam_id());
            stmt.setString(2, this.getPtitle());
            stmt.setString(3, this.getProject_description());
            stmt.setString(4, status);
            stmt.setString(5, comment);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "The project submitted successfully");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "The project is not submitted please make sure the available user and instructor Id", "Oops", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    private static double computeJaccardSimilarity(String text1, String text2) {
        Set<String> set1 = new HashSet<>(Arrays.asList(text1.split("\\s+")));
        Set<String> set2 = new HashSet<>(Arrays.asList(text2.split("\\s+")));

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / (double) union.size();
    }

    public void submitProjectTitle() {
        List<String> descriptionFromDatabase = getProjectDescriptionFromDatabase();
        List<String> titlesFromDatabase = getProjectDescriptionFromDatabase();
        boolean isPdescriptionSimilar = false;
        boolean isTitleSimilar = false;

        for (String dbText : descriptionFromDatabase) {
            double descriptionSimilarity = computeJaccardSimilarity(this.getProject_description(), dbText);
            if (descriptionSimilarity >= DIFFERENCE_THRESHOLD) {
                isPdescriptionSimilar = true;
                break;
            }

        for (String title : titlesFromDatabase) {
            double titleSimilarity = computeJaccardSimilarity(this.getPtitle(), title);
            if (titleSimilarity >= DIFFERENCE_THRESHOLD) {
                isTitleSimilar = true;
                break;
            }
        }
        }

        if (!isPdescriptionSimilar && !isTitleSimilar) {
            insertTitleIntoDatabase();
        } else {
            String comment = "Your Title is worked by someone before!";
            String status = "rejected";
            insertCopyTitleIntoDatabase(comment, status);
        }
    }

    public String getStatus(String team_id) {
        String sql = "select * from Title_approval where team_id = ?";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1,team_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                project_description = rs.getString("pdescription");
                Ptitle = rs.getString("pTitle");
                pStatus = rs.getString("status");
                String recommendation = rs.getString("recommendation");
                all.append("Title: ").append(Ptitle).append("\nProject Description: ").append(project_description)
                        .append("\nStatus: ").append(pStatus).append("\nRecommendation: ").append(recommendation);
            } return all.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "The project is not found!";
    }

    public String retrieveProjecTitle() {
        String sql = "select * from Title_approval where status = 'under review'";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                project_description = rs.getString("pdescription");
                Ptitle = rs.getString("pTitle");
                String reportID = rs.getString("report_ID");
                all.append("Title: ").append(Ptitle).append("\nProject Description: ").append(project_description)
                        .append("\nReport NO: ").append(reportID);
            } return all.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "The project is not found!";
    }

    public String retrievePassedProjectTitle() {
        String sql = "select * from Title_approval where status = 'accepted'";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                project_description = rs.getString("pdescription");
                Ptitle = rs.getString("pTitle");
                String reportID = rs.getString("team_id");
                all.append("Title: ").append(Ptitle).append("\nProject Description: ").append(project_description)
                        .append("\nTeam NO: ").append(reportID);
            } return all.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "The project is not found!";
    }

    public String assignTitleCommite(String commite, String reportID) {
        String sql = "update Title_approval set commite_id = ? where report_ID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, commite);
            pstmt.setString(2, reportID);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return "New Commite Assigned Successfully";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Commite Assigned Failed";
    }

    public String evaluateTitle(String status, String Comment, String projectID) {
        String sql = "update Title_approval set Status = ?, recommendation = ? where report_ID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setString(2, Comment);
            pstmt.setString(3, projectID);

            int rowsAffected = pstmt.executeUpdate();

            if(rowsAffected > 0) {
                notification = "The project has been evaluated successfully";
                return notification;
            }

        } catch (SQLException e) {
            notification = "There was an error while evaluating the project\nPlease make sure the status must be in\n'failed','pass','In progress'";
            e.printStackTrace();
        }
        return "Error...\nNo project found";
    }

    public String received_Title(String instructorID) {
        String sql = "select * from reviewCommiteMembers R join Title_approval T ON R.commite_id = T.commite_id where Status  = 'under review' AND R.advisor_id = ?";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, instructorID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("Ptitle");
                String synopsis = rs.getString("pDescription");
                String reportID = rs.getString("report_ID");
                all.append("\nReport NO: ").append(reportID).append("\nTitle: ").append(title)
                        .append("\nProject: ").append(synopsis).append("\n\n");
            } return all.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
