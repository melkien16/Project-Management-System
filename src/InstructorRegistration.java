import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructorRegistration extends JFrame{
    private JPanel pane;
    private JTextField InsID;
    private JTextField FullName;
    private JTextField userName;
    private JTextField Experience;
    private JTextField topicPreference;
    private JTextField Email;
    private JButton registerButton;
    private JButton backButton;
    private JButton homeButton;
    private JLabel messageLabel;
    private JTextField Squestion;
    private JPasswordField passwordField1;

    public InstructorRegistration() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pane);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(InsID, "Instructor ID");
        PlaceholderUtil.addPlaceholder(FullName, "Full Name");
        PlaceholderUtil.addPlaceholder(userName, "Username");
        PlaceholderUtil.addPlaceholder(passwordField1, "Password");
        PlaceholderUtil.addPlaceholder(Experience, "Experience");
        PlaceholderUtil.addPlaceholder(topicPreference, "Field");
        PlaceholderUtil.addPlaceholder(Email, "Email");
        PlaceholderUtil.addPlaceholder(Squestion, "Security Question");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinatore coordinatore = new Coordinatore();
                dispose();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeDesign homeDesign = new HomeDesign();
                homeDesign.setVisible(true);
                dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String insId = InsID.getText();
                String fullName = FullName.getText();
                String username = userName.getText();
                String password = new String(passwordField1.getPassword());
                String experience = Experience.getText();
                String field = topicPreference.getText();
                String email = Email.getText();
                String sQuestion = Squestion.getText();
                Instructors newIns = new Instructors(insId,fullName,username,password,email,experience,field,sQuestion);
                Database dbHelper = new Database();
                JOptionPane.showMessageDialog(null,dbHelper.InstructorRegistration(newIns));
            }
        });
    }
}
