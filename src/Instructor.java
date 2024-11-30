import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Instructor extends JFrame{
    private JPanel panel;
    private JButton showproject;
    private JButton messageButton;
    private JButton homeButton;
    private JButton changePasswodButton;
    private JButton ApproveTitle;

    public Instructor() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);
        messageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message message = new Message();
                dispose();
            }
        });
        showproject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstructoresView instructoresView = new InstructoresView();
                registerActiveUser rg = new registerActiveUser();
                registerActiveUser active = rg.getActiveUser();
                String ID = active.getActiveID();
                RetrieveProjects retrieveProjects = new RetrieveProjects();
                instructoresView.projectViewField.append(retrieveProjects.received_Project(ID));
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
        changePasswodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePassword changePassword = new ChangePassword();
                dispose();
            }
        });
        ApproveTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstructoresApproveTitle approveTitle = new InstructoresApproveTitle();
                registerActiveUser rg = new registerActiveUser();
                registerActiveUser gn = rg.getActiveUser();
                String k = gn.getActiveID();
                titleSubmision title = new titleSubmision();
                approveTitle.projectViewField.setText(title.received_Title(k));
                dispose();
            }
        });
    }
}
