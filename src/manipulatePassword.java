import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class manipulatePassword {
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";
    private String notification;

    public String changePassword(String username, String oldPassword, String newPassword, String sql) {
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            stmt.setString(3, oldPassword);

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0) {
                notification = "Password Changed successfully!";
                return notification;
            };

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed...\nPlease try again!";
    }

    public String forgatePassword(String username, String sQuestions, String newPassword, String sql) {
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            stmt.setString(3, sQuestions);

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0) {
                notification = "Password Changed successfully!";
                return notification;
            };

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed...\nPlease try again!";
    }
}
