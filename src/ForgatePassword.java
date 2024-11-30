import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgatePassword extends JFrame {
    private JPasswordField newPassField;
    private JPasswordField confirmPassField;
    private JButton homeButton;
    private JButton changeButton;
    private JTextField sQuestion;
    private JPanel panel;
    private registerActiveUser rg = new registerActiveUser();
    private manipulatePassword mp = new manipulatePassword();

    public ForgatePassword() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        PlaceholderUtil.addPlaceholder(newPassField, "password");
        PlaceholderUtil.addPlaceholder(confirmPassField, "password");
        PlaceholderUtil.addPlaceholder(sQuestion, "Security question");

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String question = sQuestion.getText();
                String pass = new String(newPassField.getPassword());
                String confirmPass = new String(confirmPassField.getPassword());
                if (confirmPass.equals(pass)) {
                    registerActiveUser active = rg.getActiveUser();
                    String Role = active.getActiveRole();
                    String username = active.getActiveUsername();
                    if (Role.equalsIgnoreCase("student")) {
                        String sql = "update Students set password = ? where username = ? AND securityQuestion = ?";
                        JOptionPane.showMessageDialog(null,mp.forgatePassword(username,question,pass,sql));
                    } else if (Role.equalsIgnoreCase("coordinator")) {
                        String sql = "update Coordinator set password = ? where username = ? AND securityQuestion = ?";
                        JOptionPane.showMessageDialog(null,mp.forgatePassword(username,question,pass,sql));
                    } else {
                        String sql = "update Instructors set password = ? where username = ? AND securityQuestion = ?";
                        JOptionPane.showMessageDialog(null,mp.forgatePassword(username,question,pass,sql));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                }
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeDesign hd = new HomeDesign();
                hd.setVisible(true);
                dispose();
            }
        });
    }
}

//                if (confirmPass.equals(newPass)) {
//registerActiveUser active = rg.getActiveUser();
//String username = active.getActiveUsername();
//String Role = active.getActiveRole();
//                    if (Role.equalsIgnoreCase("student")) {
//String sql = "update Students set password = ? where username = ? AND password = ?";
//                        JOptionPane.showMessageDialog(null,mp.changePassword(username,oldPass,confirmPass,sql));
//        } else if (Role.equalsIgnoreCase("coordinator")) {
//String sql = "update Coordinator set password = ? where username = ? AND password = ?";
//                        JOptionPane.showMessageDialog(null,mp.changePassword(username,oldPass,confirmPass,sql));
//        } else {
//String sql = "update Instructors set password = ? where username = ? AND password = ?";
//                        JOptionPane.showMessageDialog(null,mp.changePassword(username,oldPass,confirmPass,sql));
//        }
//        } else {
//        JOptionPane.showMessageDialog(null, "Passwords do not match");
//                }