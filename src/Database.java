import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;

public class Database{
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";
    private String notification;
    public String registerUser(Users user) {
        String sql = "INSERT INTO Students(user_id, StudentName, username, password, Email, Legal, Reason,SecurityQuestion) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set the values for the prepared statement
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getFullName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, "Legal");
            pstmt.setString(7, "Legal Student");
            pstmt.setString(8, user.getSecurityQuestion());

            // Execute the insert statement
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0) {
                notification = "A Student registered successfully";
                return notification;
            };

        } catch (SQLException e) {
            notification = "Register failed, A Student has been registered before";
            e.printStackTrace();
        }
        return "Register failed...";
    }

    public String InstructorRegistration(Instructors instructors) {
        String sql = "INSERT INTO Instructors(instructor_id, IName, username, password, expertise, topic_preference, Email,SecurityQuestion) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set the values for the prepared statement
            pstmt.setString(1, instructors.getUserId());
            pstmt.setString(2, instructors.getFullName());
            pstmt.setString(3, instructors.getUsername());
            pstmt.setString(4, instructors.getPassword());
            pstmt.setString(5, instructors.getExperience());
            pstmt.setString(6, instructors.getTopicPreferences());
            pstmt.setString(7, instructors.getEmail());
            pstmt.setString(8, instructors.getSecurityQuestion());

            // Execute the insert statement
            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0) {
                notification = "Instructor Registered successfully";
                return notification;
            };

        } catch (SQLException e) {
            notification = "Register failed, Instructor has been registered before";
            e.printStackTrace();
        }
        return "Register failed...";
    }

    public Students authenticateStudent(String username, String password) {
        String sql = "SELECT * FROM Students WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Students(rs.getString("user_id"), rs.getString("StudentName"), rs.getString("username"),
                        rs.getString("password"), rs.getString("email"),rs.getString("Legal"),rs.getString("reason"),rs.getString("SecurityQuestion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Coordinator authenticateCoordinator(String username, String password) {
        String sql = "SELECT * FROM Coordinator WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Coordinator(rs.getString("coordinator_ID"), rs.getString("CoordinatorName"), rs.getString("username"),
                        rs.getString("password"),rs.getString("email"),rs.getString("SecurityQuestion")) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Instructors authenticateInstructor(String username, String password) {
        String sql = "SELECT * FROM Instructors WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Instructors(rs.getString("instructor_ID"), rs.getString("IName"), rs.getString("username"),
                        rs.getString("password"),rs.getString("email"),rs.getString("expertise"),rs.getString("topic_preference"),rs.getString("SecurityQuestion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String findInstructor(String topicPreference) {
        String sql = "SELECT * FROM Instructors WHERE  topic_preference= ?";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, topicPreference);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                all.append("Instructor ID: ").append(rs.getString("instructor_ID")).append("\nFull Name: ").append(rs.getString("IName")).append("\nExpertize: ").append(rs.getString("expertise")).append("\nTopic Preferences: ").append(rs.getString("topic_preference")).append("\n\n");
            }
            return all.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "There is no instructor tha has been expertise in this field";
    }

    public String showAllInstructor() {
        String sql = "SELECT * FROM Instructors";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                all.append("Instructor ID: ").append(rs.getString("instructor_ID")).append("\nFull Name: ").append(rs.getString("IName")).append("\nExpertize: ").append(rs.getString("expertise")).append("\nTopic Preferences: ").append(rs.getString("topic_preference")).append("\n\n");
            }
            return all.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "There is no instructor tha has been expertise in this field";
    }

    public Students availableUser(String user_id) {
        String sql = "SELECT * FROM Students WHERE user_id = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Students(rs.getString("user_id"), rs.getString("StudentName"), rs.getString("username"),
                        rs.getString("password"), rs.getString("email"),rs.getString("Legal"),rs.getString("Reason"),rs.getString("SecurityQuestion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Students authenticateUserNameLegal(String username) {
        String sql = "SELECT * FROM Students WHERE username = ? AND Legal = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, "Legal");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Students(rs.getString("user_id"), rs.getString("StudentName"), rs.getString("username"),
                        rs.getString("password"), rs.getString("email"),rs.getString("Legal"),rs.getString("Reason"),rs.getString("SecurityQuestion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Students LockUser(String username, String Reason) {
        String sql = "UPDATE Students SET Legal = 'Locked', Reason = ? where username = ?";
        LocalDateTime currentDateTime = LocalDateTime.now();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, Reason);
            stmt.setString(2, username);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Your Account has been locked\nPlease Check Registrar", "warning", JOptionPane.WARNING_MESSAGE);
                String message = "Your Account had been locked because of Many times Attempt Wrong password\n Please safe your account";
                String senderMessage = "Registrar";
                MessageSend messageSend = new MessageSend(username, message,senderMessage,Timestamp.valueOf(currentDateTime));
                messageSend.send_message();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void ReleaseUser(String username, String Reason) {
        String sql = "UPDATE Students SET Legal = 'Legal', Reason = ? where username = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, Reason);
            stmt.setString(2, username);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Username: "+username+" released successfully!", "warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void lockStudent(String username, String Reason) {
        String sql = "UPDATE Students SET Legal = 'Locked', Reason = ? where username = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, Reason);
            stmt.setString(2, username);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Username: "+username+" Locked successfully!", "warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
