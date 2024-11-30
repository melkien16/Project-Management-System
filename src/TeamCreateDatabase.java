import java.sql.*;

public class TeamCreateDatabase {
    private static final String connectionUrl =
            "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=INDUSTRIAL_PROJECT_MANAGEMENT_SYSTEM;"
                    + "user=sa;"
                    + "password=1234;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    public String teamId;
    public String teamName;

    TeamCreateDatabase(String teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }
    TeamCreateDatabase(){

    };

    String getTeamId() {
        return teamId;
    }

    String getTeamName() {
        return teamName;
    }

    public void createTeam() {
        String sql = "INSERT INTO Teams(team_id, team_name) VALUES(?, ?)";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Set the values for the prepared statement
            stmt.setString(1, this.getTeamId());
            stmt.setString(2, this.getTeamName());

            // Execute the insert statement
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String retrieveTeams() {
        String sql = "SELECT team_id, team_name FROM Teams";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            StringBuilder all = new StringBuilder("");
            // Process the result set
            while (rs.next()) {
                String teamId = rs.getString("team_id");
                String teamName = rs.getString("team_name");
                all.append("Team ID: ").append(teamId).append(", Team Name: ").append(teamName).append("\n");
            }
            return all.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TeamCreateDatabase availableTeam(String teamId) {
        String sql = "SELECT * FROM Teams WHERE team_id = ?";
        try (Connection connection = DriverManager.getConnection(connectionUrl);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, teamId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TeamCreateDatabase(rs.getString("team_id"), rs.getString("team_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
