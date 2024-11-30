import java.sql.*;

public class registerActiveUser {
    private String activeUsername;
    private String activeRole;
    private String activeID;
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    public registerActiveUser(String activeUsername, String activeRole, String activeID) {
        this.activeUsername = activeUsername;
        this.activeRole = activeRole;
        this.activeID = activeID;
    }

    registerActiveUser() {

    }

    public String getActiveUsername() {
        return activeUsername;
    }

    public String getActiveRole() {
        return activeRole;
    }
    public String getActiveID() {
        return activeID;
    }

    public boolean assignActiveUser() {
        String sql = "update activeUser set activeUserName = ?, Roles = ?, ID = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, this.activeUsername);
            pstmt.setString(2, this.activeRole);
            pstmt.setString(3, this.activeID);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public registerActiveUser getActiveUser() {
        String sql = "select * from activeUser";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new registerActiveUser(rs.getString("activeUserName"), rs.getString("Roles"),rs.getString("ID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getActiveTeamId() {
        String sql = "select t.team_id from TeamMembers t join Students s ON t.user_id = s.user_id where s.username = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.getActiveUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("team_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getActiveTeamAdvisor() {
        String sql = "select T.team_id, T.advisor_id  from Teams T join TeamMembers M ON T.team_id = M.team_id where M.user_id = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, this.getActiveID());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("advisor_ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
