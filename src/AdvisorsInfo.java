import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvisorsInfo extends JFrame {
    private JTextArea availableInstructoresTextArea;
    private JTextField expertise;
    private JButton searchButton;
    private JButton allAdvisorsButton;
    private JButton backButton;
    private JPanel panel;

    public AdvisorsInfo() {

        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(expertise, "Expertise ...");
        PlaceholderUtil.addPlaceholder(availableInstructoresTextArea, "\t\t\tInstructors Information");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database database = new Database();
                String expertiseText = expertise.getText();
                String availableTeachers = database.findInstructor(expertiseText);
                availableInstructoresTextArea.setText(availableTeachers);
            }
        });
        allAdvisorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database database = new Database();
                String availableTeachers = database.showAllInstructor();
                availableInstructoresTextArea.setText(availableTeachers);
            }
        });
        backButton.addActionListener(new ActionListener() {
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
