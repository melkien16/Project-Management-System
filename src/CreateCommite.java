import java.sql.*;

public class CreateCommite {
    private String notification;
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    public String commite_id;
    public String commite_name;

    CreateCommite(String commite_id, String commite_name) {
        this.commite_id = commite_id;
        this.commite_name = commite_name;
    }

    CreateCommite() {

    }

    String getCommite_id() {
        return commite_id;
    }
    String getCommite_name() {
        return commite_name;
    }

    public String createCommite() {
        String sql = "INSERT INTO reviewCommite(commite_id,commite_name) VALUES(?,?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, this.getCommite_id());
            stmt.setString(2, this.getCommite_name());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                notification = "New Commite created successfully";
                return notification;
            }

        } catch (SQLException e) {
            notification = "Error creating commite";
            e.printStackTrace();
        }
        return notification;
    }

    public String asignInstructors(String advisor_id) {
        String sql = "INSERT INTO reviewCommiteMembers(commite_id,advisor_id) VALUES(?,?)";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, this.getCommite_id());
            stmt.setString(2, advisor_id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                notification = "New Commite created successfully";
                return notification;
            }

        } catch (SQLException e) {
            notification = "Error creating commite";
            e.printStackTrace();
        }
        return notification;
    }

    public String assignprojectAdvisors(String advisor, String team_id) {
        String sql = "update Teams set advisor_id = ? where team_id = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, advisor);
            pstmt.setString(2, team_id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return "New Advisor Assigned Successfully";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Advisor Assigned Failed";
    }
}
