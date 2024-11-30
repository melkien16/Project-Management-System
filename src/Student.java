import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student extends JFrame {
    private JPanel pannel;
    private JPanel innerPane;
    private JTextArea projectArea;
    private JLabel messageLabel;
    private JButton submitProjectButton;
    private JTextField projectTitleField;
    private JButton backButton;
    private JButton StatusBtn;

    Student() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(pannel);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(projectArea, "Write Your Project...");
        PlaceholderUtil.addPlaceholder(projectTitleField, "Enter Your Project Title...");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomeDesign homeDesign = new HomeDesign();
                homeDesign.setVisible(true);
                dispose();
            }
        });
        submitProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String projectTitle = projectTitleField.getText();
                String submitted_project = projectArea.getText();

                registerActiveUser rg = new registerActiveUser();
                registerActiveUser gn = rg.getActiveUser();
                String team_id = gn.getActiveTeamId();
                String adv = gn.getActiveTeamAdvisor();
                ProgressReport submitAssignment = new ProgressReport(submitted_project,team_id,adv,projectTitle);
                submitAssignment.submit();
            }
        });
        StatusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerActiveUser rg = new registerActiveUser();
                registerActiveUser gn = rg.getActiveUser();
                String j = gn.getActiveTeamId();
                RetrieveProjects retrieveProjects = new RetrieveProjects();
                projectArea.append(retrieveProjects.getProjectStatus(j));
                String status = retrieveProjects.getProjectStatus(j);
                if(status == null) {
                    JOptionPane.showMessageDialog(null,"You Don't have any Project Status!");
                } else if(status.equalsIgnoreCase("passed")) {
                    JOptionPane.showMessageDialog(null,"Congratulation Your Project has been approved\nStart to work project progress");
                } else if (status.equalsIgnoreCase("In progress")) {
                    JOptionPane.showMessageDialog(null,"Your project is under review\nPlease wait patiently...");
                } else {
                    JOptionPane.showMessageDialog(null,"Your Project had been rejected");
                }
            }
        });
    }

}
