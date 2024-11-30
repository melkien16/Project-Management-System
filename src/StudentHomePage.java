import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHomePage extends JFrame {
    private JPanel panel1;
    private JButton previousProjectsButton;
    private JButton messageButton;
    private JButton homePageButton;
    private JButton titleSubmition;
    private JButton ProjectProgress;
    private JButton changePass;

    public StudentHomePage() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        homePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeDesign homeDesign = new HomeDesign();
                homeDesign.setVisible(true);
                dispose();
            }
        });
        messageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message message = new Message();
                dispose();
            }
        });

        titleSubmition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TitleSubmisionForm titleSubmisionForm = new TitleSubmisionForm();
                dispose();
            }
        });
        ProjectProgress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerActiveUser rg = new registerActiveUser();
                registerActiveUser gn = rg.getActiveUser();
                String j = gn.getActiveTeamId();
                titleSubmision ts = new titleSubmision();
                ts.getStatus(j);
                String status = ts.getpStatus();
                if(status == null) {
                    JOptionPane.showMessageDialog(null,"Please make sure First your title has been approved!");
                }
                else if(status.equalsIgnoreCase("accepted")) {
                    Student student = new Student();
                    dispose();;
                } else if (status.equalsIgnoreCase("under review")) {
                    JOptionPane.showMessageDialog(null,"Your project Title is under review\nPlease wait patiently until your title has accepted!...");
                } else {
                    JOptionPane.showMessageDialog(null,"Your Title had been rejected\nPlease submit another title and wait until your title has accepted!");
                }
            }
        });
        changePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePassword changePassword = new ChangePassword();
                dispose();
            }
        });
        previousProjectsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectInfo p = new projectInfo();
                dispose();
            }
        });
    }
}
