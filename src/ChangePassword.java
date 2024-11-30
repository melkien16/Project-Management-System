import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassword extends JFrame {
    private JPanel panel1;
    private JPasswordField oldpassField;
    private JPasswordField newPassField;
    private JPasswordField confirmPassField;
    private JButton homeButton;
    private JButton changeButton;
    private registerActiveUser rg = new registerActiveUser();
    private manipulatePassword mp = new manipulatePassword();
    public ChangePassword() {
            setSize(1600,900);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setContentPane(panel1);
            setVisible(true);

        PlaceholderUtil.addPlaceholder(oldpassField, "password");
        PlaceholderUtil.addPlaceholder(newPassField, "password");
        PlaceholderUtil.addPlaceholder(confirmPassField, "password");

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeDesign homeDesign = new HomeDesign();
                homeDesign.setVisible(true);
                dispose();
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPass = new String(oldpassField.getPassword());
                String newPass = new String(newPassField.getPassword());
                String confirmPass = new String(confirmPassField.getPassword());
                if (confirmPass.equals(newPass)) {
                    registerActiveUser active = rg.getActiveUser();
                    String username = active.getActiveUsername();
                    String Role = active.getActiveRole();
                    if (Role.equalsIgnoreCase("student")) {
                        String sql = "update Students set password = ? where username = ? AND password = ?";
                        JOptionPane.showMessageDialog(null,mp.changePassword(username,oldPass,confirmPass,sql));
                    } else if (Role.equalsIgnoreCase("coordinator")) {
                        String sql = "update Coordinator set password = ? where username = ? AND password = ?";
                        JOptionPane.showMessageDialog(null,mp.changePassword(username,oldPass,confirmPass,sql));
                    } else {
                        String sql = "update Instructors set password = ? where username = ? AND password = ?";
                        JOptionPane.showMessageDialog(null,mp.changePassword(username,oldPass,confirmPass,sql));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                }
            }
        });
    }
}
