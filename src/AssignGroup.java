import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignGroup extends JFrame {
    private JPanel pane;
    public JTextArea showTeamsField;
    private JTextField studentIdField;
    private JTextField studentRoleField;
    private JTextField teamIdField;
    private JButton backButton;
    private JButton assignButton;
    public AssignGroup() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pane);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(studentIdField, "Student ID...");
        PlaceholderUtil.addPlaceholder(studentRoleField, "Member || Group Leader");
        PlaceholderUtil.addPlaceholder(teamIdField, "Team ID...");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinatore coordinatore = new Coordinatore();
                dispose();
            }
        });
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamId = teamIdField.getText();
                String role = studentRoleField.getText();
                String id = studentIdField.getText();
                AssignMembers assignMembers = new AssignMembers(teamId,id,role);
                assignMembers.assignTeamMember();
            }
        });
    }
}
