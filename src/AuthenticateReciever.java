import java.sql.*;

public class AuthenticateReciever {
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    public boolean availableStudent(String username) {
        String sql = "SELECT * FROM Students WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean MemberRole(String user_name) {
        String sql = "select * from Students u join TeamMembers t on t.user_id = u.user_id AND t.MemberRole <> 'member' AND u.username = ?";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user_name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean availableCoordinator(String username) {
        String sql = "SELECT * FROM Coordinator WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean availableInstructor(String username) {
        String sql = "SELECT * FROM Instructors WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
