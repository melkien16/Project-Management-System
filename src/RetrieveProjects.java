import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RetrieveProjects {
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";
    private String searchTitle;
    private String team_name;
    private String advisor_name;
    private String title;
    private String synopsis;
    private String Status;
    private String timeStatus;
    private String Comment;
    private String projectID;
    private String team_id;
    private String notification;

    RetrieveProjects(String team_name, String advisor_name, String title, String synopsis, String Status, String timeStatus, String Comment, String projectID, String team_id) {
        this.team_name = team_name;
        this.advisor_name = advisor_name;
        this.title = title;
        this.synopsis = synopsis;
        this.Status = Status;
        this.timeStatus =timeStatus;
        this.Comment = Comment;
        this.projectID = projectID;
        this.team_id = team_id;
    }
    RetrieveProjects() {

    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getAdvisor_name() {
        return advisor_name;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getStatus() {
        return Status;
    }

    public String getTimeStatus() {
        return timeStatus;
    }

    public String getComment() {
        return Comment;
    }
    public String getProjectID() {
        return projectID;
    }


    public String viewProjects() {
        String sqlQuery = "select * from ProgressReports";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                team_name = rs.getString("team_id");
                title = rs.getString("title");
                synopsis = rs.getString("synopsis");
                String reportID = rs.getString("reportID");
                Comment = rs.getString("Comment");
                all.append("Group ID: ").append(team_name).append("\nTitle: ").append(title)
                        .append("\nProject: ").append(synopsis).append("\nComment: ").append(Comment).append("\n\n");
            } return all.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No project has found!!";
    }

    public String searchProjects(String title) {
        StringBuilder all = new StringBuilder();

        String sql = "select title,synopsis from ProgressReports where title = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                title = rs.getString("title");
                synopsis = rs.getString("synopsis");
                all.append("\nTitle: ").append(title)
                        .append("\nProject: ").append(synopsis).append("\n\n");
            } return all.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No project has found!!";
    }


    public String viewProjectStatus(String sqlQuery) {

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {
            StringBuilder all = new StringBuilder();
            // Process the result set
            while (rs.next()) {
                advisor_name = rs.getString("IName");
                Status = rs.getString("p.Status");
                timeStatus = rs.getString("timeStatus");
                Comment = rs.getString("Comment");
                synopsis = rs.getString("synopsis");
                all.append("Advisor: ").append(advisor_name).append("\nStatus: ").append(Status).append("\nProjects: ").append(synopsis).append("Comment: ").append(Comment).append("\n");
            }
            return all.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String allProjectInformationForCoordinator(String sqlQuery, String parameter) {
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {

            stmt.setString(1, parameter);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                team_id = rs.getString("team_id");
                team_name = rs.getString("team_name");
                advisor_name = rs.getString("IName");
                Status = rs.getString("p.Status");
                timeStatus = rs.getString("timeStatus");
                all.append("Team ID: ").append(team_id).append("\nTeam: ").append(team_name).append("\nAdvisor: ").append(advisor_name).append("\nTime Status: ").append(timeStatus).append("\n");
            }
            return all.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String received_Project(String instructorID) {
        String sql = "select * from ProgressReports where Status  = 'In progress' AND advisor_id = ?";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, instructorID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                team_name = rs.getString("team_id");
                title = rs.getString("title");
                synopsis = rs.getString("synopsis");
                projectID = rs.getString("reportID");
                Comment = rs.getString("Comment");
                String reportID = rs.getString("reportID");
                all.append("Group ID: ").append(team_name).append("\nReport NO: ").append(reportID).append("\nComment: ").append(Comment).append("\nTitle: ").append(title)
                        .append("\nProject: ").append(synopsis).append("\n\n");
            } return all.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String evaluateProject(String status, String Comment, String projectID) {
        String sql = "update ProgressReports set Status = ?, Comment = ? where reportID = ?";

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

    public String getProjectStatus(String team_id) {
        String sql = "select * from ProgressReports where team_id = ?";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1,team_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String project_description = rs.getString("synopsis");
                String Ptitle = rs.getString("title");
                String pStatus = rs.getString("Status");
                String recommendation = rs.getString("Comment");
                all.append("Title: ").append(Ptitle).append("\nProject: ").append(project_description)
                        .append("\nStatus: ").append(pStatus).append("\nRecommendation: ").append(recommendation);
            } return all.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "The project is not found!";
    }
}
