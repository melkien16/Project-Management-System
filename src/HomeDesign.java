import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeDesign extends JFrame{
    private Database dbHelper = new Database();
    private JPanel panel;
    private JPanel innerLabel;
    private JLabel userNameLabel;
    public JTextField usernameField;
    private JPasswordField passwordField;
    public JRadioButton studentRadioButton;
    public JRadioButton coordinatorRadioButton;
    public JRadioButton instructorRadioButton;
    private JButton loginButton;
    private JButton signUp;
    private JLabel loginLabel;
    private JLabel messageLabel;
    private JButton forgatePassworedButton;
    public int count = 0;

    HomeDesign()
    {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(studentRadioButton);
        roleGroup.add(coordinatorRadioButton);
        roleGroup.add(instructorRadioButton);

        PlaceholderUtil.addPlaceholder(usernameField, "username");
        PlaceholderUtil.addPlaceholder(passwordField, "passwordField");

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RegistrationDesign registerFrame = new RegistrationDesign();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (studentRadioButton.isSelected()) {
                    Students authenticatedUserName = dbHelper.authenticateUserNameLegal(username);
                    Students authenticatedUser = dbHelper.authenticateStudent(username, password);

                    count++;
                    if(authenticatedUserName != null) {
                        if(count < 3) {
                            if (authenticatedUser != null) {
                                registerActiveUser activeUser = new registerActiveUser(username,"student",authenticatedUser.getUserId());
                                activeUser.assignActiveUser();
                                StudentHomePage studentHomePage = new StudentHomePage();
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null,"Login failed.\nInvalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else if(count < 4) {
                            if (authenticatedUser != null) {
                                registerActiveUser activeUser = new registerActiveUser(username,"student",authenticatedUser.getUserId());
                                activeUser.assignActiveUser();
                                StudentHomePage studentHomePage = new StudentHomePage();
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Login failed.\nPlease Login Properly!\nYou have only one Trial", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                        } else if(count ==4) {
                            Students lockUser = dbHelper.LockUser(username,"Many tries of wrong Password!");
                        }
                    } else if(authenticatedUser != null){
                        JOptionPane.showMessageDialog(null, "Locked Account", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed.\nInvalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }


                } else if (coordinatorRadioButton.isSelected()) {
                    Coordinator authenticatedCoordinator = dbHelper.authenticateCoordinator(username, password);
                    if (authenticatedCoordinator != null) {
                        registerActiveUser activeUser = new registerActiveUser(username,"coordinator",authenticatedCoordinator.getUserId());
                        activeUser.assignActiveUser();
                        Coordinatore cd = new Coordinatore();
                        setVisible(false);
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null,"Login failed.\nInvalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else if (instructorRadioButton.isSelected()) {
                    Instructors authenticatedInstructor = dbHelper.authenticateInstructor(username, password);
                    if (authenticatedInstructor != null) {
                        registerActiveUser activeUser = new registerActiveUser(username,"instructor",authenticatedInstructor.getUserId());
                        activeUser.assignActiveUser();
                        Instructor instructor = new Instructor();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,"Login failed.\nInvalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        forgatePassworedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if(studentRadioButton.isSelected()) {
                    registerActiveUser activeUser = new registerActiveUser(username,"student","null");
                    activeUser.assignActiveUser();
                    dispose();
                    ForgatePassword fp = new ForgatePassword();
                } else if (instructorRadioButton.isSelected()) {
                    registerActiveUser activeUser = new registerActiveUser(username,"Instructor","null");
                    activeUser.assignActiveUser();
                    ForgatePassword fp = new ForgatePassword();
                    dispose();
                } else {
                    registerActiveUser activeUser = new registerActiveUser(username,"Coordinator","null");
                    activeUser.assignActiveUser();
                    ForgatePassword fp = new ForgatePassword();
                    dispose();
                }

            }
        });
    }
}
