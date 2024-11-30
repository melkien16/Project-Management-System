import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Coordinatore extends JFrame {
    private JPanel panel1;
    private JButton searchButton;
    private JButton projectInformationButton;
    private JButton createTeamButton;
    private JButton assignTeamMembersButton;
    private JButton instructorRegistrationButton;
    private JButton homeButton;
    private JButton messageButton;
    private JButton changePasswordButton;
    private JButton studentLegalityButton;
    private JButton assignAdvisor;
    private JButton assignCommite;

    Coordinatore() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeDesign homeDesign = new HomeDesign();
                homeDesign.setVisible(true);
                dispose();
            }
        });

        studentLegalityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentLegality studentLegality = new StudentLegality();
                dispose();
            }
        });
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeamCreate teamCreate = new TeamCreate();
                dispose();
            }
        });
        assignTeamMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignGroup assignGroup = new AssignGroup();
                TeamCreateDatabase teamCreateDatabase = new TeamCreateDatabase();
                assignGroup.showTeamsField.append(teamCreateDatabase.retrieveTeams());
                dispose();
            }
        });
        instructorRegistrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InstructorRegistration instructoreRegistration = new InstructorRegistration();
                dispose();
            }
        });
        messageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Message message = new Message();
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePassword changePassword = new ChangePassword();
                dispose();
            }
        });

        assignCommite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TitleView titleView = new TitleView();
                titleSubmision ts = new titleSubmision();
                String values = ts.retrieveProjecTitle();
                titleView.textArea1.append(values);
                dispose();
            }
        });
        assignAdvisor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignAdvisors assignAdvisors = new AssignAdvisors();
                titleSubmision ts = new titleSubmision();
                String allTitles = ts.retrievePassedProjectTitle();
                assignAdvisors.textArea1.setText(allTitles);
                dispose();
            }
        });
    }
}
