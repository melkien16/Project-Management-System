import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructoresView extends JFrame{
    private JPanel panel1;
    public JTextArea projectViewField;
    private JTextField projectIdField;
    private JTextField statusField;
    private JTextArea commentField;
    private JButton submitButton;
    private JButton backButton;

    InstructoresView() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(projectViewField, "There is no available project Progress!");
        PlaceholderUtil.addPlaceholder(projectIdField, "Report Number...");
        PlaceholderUtil.addPlaceholder(statusField, "In progress || failed || passed");
        PlaceholderUtil.addPlaceholder(commentField, "Write Your Recommendation...");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instructor instructor = new Instructor();
                dispose();
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String projectId = projectIdField.getText();
                String status = statusField.getText();
                String comment = commentField.getText();
                RetrieveProjects retrieveProjects = new RetrieveProjects();
                JOptionPane.showMessageDialog(null,retrieveProjects.evaluateProject(status,comment,projectId));
            }
        });
    }
}
