import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructoresApproveTitle extends JFrame {
    public JTextArea projectViewField;
    private JTextField projectIdField;
    private JTextField statusField;
    private JTextArea commentField;
    private JButton submitButton;
    private JButton backButton;
    private JPanel panel;

    public InstructoresApproveTitle() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(projectViewField, "There is no available project titles!");
        PlaceholderUtil.addPlaceholder(projectIdField, "Report Number...");
        PlaceholderUtil.addPlaceholder(statusField, "under review || rejected || accepted");
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
                titleSubmision ts = new titleSubmision();
                String projectNO = projectIdField.getText();
                String status = statusField.getText();
                String comment = commentField.getText();
                JOptionPane.showMessageDialog(null,ts.evaluateTitle(status,comment,projectNO));
            }
        });
    }
}
