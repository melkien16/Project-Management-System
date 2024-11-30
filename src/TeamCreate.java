import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamCreate extends JFrame {
    private JPanel panel;
    private JPanel innerPane;
    private JTextField teamIdField;
    private JTextField teamName;
    private JButton createButton;
    private JButton backButton;

    TeamCreate() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(teamIdField, "Enter Team Number...");
        PlaceholderUtil.addPlaceholder(teamName, "Team Created For...");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinatore coordinatore = new Coordinatore();
                dispose();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String team_id = teamIdField.getText();
                String team_name = teamName.getText();
                TeamCreateDatabase teamCreateDatabase = new TeamCreateDatabase(team_id, team_name);
                teamCreateDatabase.createTeam();
                JOptionPane.showMessageDialog(null, "Team Created successfully");
            }
        });
    }
}
