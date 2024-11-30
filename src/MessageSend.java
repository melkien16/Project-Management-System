import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MessageSend {
    String messageReceiver;
    String message;
    String messageSender;
    Timestamp SentTimeFromSQL;
    private String timeStatus;
    private String notfication;

    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    public MessageSend(String messageReceiver, String message, String messageSender, Timestamp SentTimeFromSQL) {
        this.messageReceiver = messageReceiver;
        this.message = message;
        this.messageSender = messageSender;
        this.SentTimeFromSQL = SentTimeFromSQL;
    }

    public MessageSend(){

    }

    public String getMessageReceiver() {
        return messageReceiver;
    }
    public String getMessage() {
        return message;
    }
    public String getMessageSender() {
        return messageSender;
    }

    public String send_message() {
        String sql = "INSERT INTO Message(accepted_userName, Message, SenderName,Time) VALUES(?, ?, ?, ?)";
        LocalDateTime currentDateTime = LocalDateTime.now();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Set the values for the prepared statement
            stmt.setString(1, getMessageReceiver());
            stmt.setString(2, getMessage());
            stmt.setString(3, getMessageSender());
            stmt.setTimestamp(4, Timestamp.valueOf(currentDateTime));

            // Execute the insert statement
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                notfication = "Message sent successfully For: ";
                return notfication;
            };

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while sending Message");
            e.printStackTrace();
        }
        notfication = "Message sent failed!";
        return notfication;
    }

    public String received_message(String receiver) {
        String sql = "SELECT * FROM Message WHERE accepted_userName = ?";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, receiver);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                messageSender = rs.getString("SenderName");
                message = rs.getString("Message");
                Timestamp timeFromSQL = rs.getTimestamp("Time");

                String timeString = timeFromSQL.toString();
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime sqlTime = timeFromSQL.toLocalDateTime();

                long diffInMinutes = ChronoUnit.MINUTES.between(sqlTime, currentTime);
                long diffInHours = ChronoUnit.HOURS.between(sqlTime, currentTime);
                long diffInDays = ChronoUnit.DAYS.between(sqlTime, currentTime);
                String DayString = String.valueOf(diffInDays);
                String HourString = String.valueOf(diffInHours);
                String MinuteString = String.valueOf(diffInMinutes);

                if(diffInDays > 0) {
                    timeStatus = DayString + " Days ago";
                } else if(diffInHours > 0) {
                    timeStatus = HourString + " Hours ago";
                } else if(diffInMinutes > 0) {
                    timeStatus = MinuteString + " Minutes ago";
                } else {
                    timeStatus = "Just now";
                }
                all.append("From: ").append(messageSender).append(", ").append(timeStatus).append(", ").append(timeString).append("\nMessage: ").append(message);
            }
            return all.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteMessage(String receiver) {
        String sql = "delete from Message where accepted_userName = ?";
        StringBuilder all = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, receiver);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                notfication = "Message Deleted Successfully";
                return notfication;
            };

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "You have no message's to delete";
    }

}
