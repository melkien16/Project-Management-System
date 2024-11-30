import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentLegality extends JFrame {
    private JPanel panel1;
    private JTextField studentusenameField;
    private JTextArea reasonField;
    private JButton backButton;
    private JButton realeseButton;
    private JButton punishButton;
    Database dbHelper = new Database();
    public StudentLegality() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(studentusenameField, "Student username...");
        PlaceholderUtil.addPlaceholder(reasonField, "Write Your Reason...");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinatore coordinatore = new Coordinatore();
                dispose();
            }
        });

        punishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = studentusenameField.getText();
                String reason = reasonField.getText();
                dbHelper.lockStudent(username,reason);
            }
        });

        realeseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = studentusenameField.getText();
                String reason = reasonField.getText();
                dbHelper.ReleaseUser(username,reason);
            }
        });
    }
}
