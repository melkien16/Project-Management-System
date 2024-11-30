import javax.swing.*;
import java.sql.*;

public class AssignMembers {
    Database dbHelper = new Database();
    TeamCreateDatabase teamCreateDatabase = new TeamCreateDatabase();
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    public String teamId;
    public String user_Id;
    public String memberRole;

    AssignMembers(String teamId, String user_Id, String memberRole) {
        this.teamId = teamId;
        this.user_Id = user_Id;
        this.memberRole = memberRole;
    }
    AssignMembers(){

    };

    String getTeamId() {
        return teamId;
    }
    String getUserId() {
        return user_Id;
    }

    String getMemberRole() {
        return memberRole;
    }

    public void assignTeamMember() {

        Students userAvailable = dbHelper.availableUser(user_Id);
        TeamCreateDatabase teamAvailable = teamCreateDatabase.availableTeam(teamId);

        if (userAvailable != null && teamAvailable != null) {
            String sql = "INSERT INTO TeamMembers(team_id, user_id, MemberRole) VALUES(?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(connectionUrl);
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                // Set the values for the prepared statement
                stmt.setString(1, this.getTeamId());
                stmt.setString(2, this.getUserId());
                stmt.setString(3, this.getMemberRole());

                // Execute the insert statement
                int rowsAffected = stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Member Name: "+ userAvailable.getFullName()+" Assigned "+ " Team Name: "+ teamAvailable.getTeamName()+"  Successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Login failed. It's Already available.");
            }

        } else if(userAvailable != null && teamAvailable == null){
            JOptionPane.showMessageDialog(null, "Login failed. The Team is not available.");
        } else if(userAvailable == null && teamAvailable != null){
            JOptionPane.showMessageDialog(null, "Login failed. The Member is not available.");
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Both The Team and The Member are not available.");
        }
    }
}
