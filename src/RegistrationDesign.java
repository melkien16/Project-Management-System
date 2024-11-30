import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationDesign extends JFrame {
    private Database dbHelper = new Database();
    private JPanel pannel;
    private JPanel innerPannel;
    private JTextField nameField;
    private JTextField idField;
    private JTextField emailField;
    private JTextField usernameField;
    private JLabel nameLable;
    private JLabel idLable;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;
    private JLabel registrationLabel;
    private JLabel messageLabel;
    private JPasswordField confirmPasswordField;
    private JTextField securityQuestionField;

    RegistrationDesign() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pannel);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(nameField, "Full Name");
        PlaceholderUtil.addPlaceholder(idField, "Your ID");
        PlaceholderUtil.addPlaceholder(emailField, "Email");
        PlaceholderUtil.addPlaceholder(usernameField, "Username");
        PlaceholderUtil.addPlaceholder(passwordField, "Password");
        PlaceholderUtil.addPlaceholder(confirmPasswordField, "Confirm ");
        PlaceholderUtil.addPlaceholder(securityQuestionField, "Security Question");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                HomeDesign loginFrame = new HomeDesign();
                loginFrame.setVisible(true);
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = nameField.getText();
                String user_id = idField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String email = emailField.getText();
                String sQuestion = securityQuestionField.getText();
                Students newStudent = new Students(user_id, fullName, username, password, email, sQuestion);
                if(password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, dbHelper.registerUser(newStudent));
                } else {
                    JOptionPane.showMessageDialog(null,"Login Failed! Confirm password properly","Oops",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
