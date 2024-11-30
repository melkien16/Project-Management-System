import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCommiteMembers extends JFrame {
    private JPanel panel1;
    private JButton searchButton;
    private JTextArea availableInstructoresTextArea;
    private JTextField commite_id;
    private JTextField commite_Name;
    private JTextField member1;
    private JTextField expertise;
    private JButton createCommiteButton;
    private JButton backButton;
    private JTextField member2;
    private JTextField member3;

    CreateCommiteMembers() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(expertise, "Enter Expertise...");
        PlaceholderUtil.addPlaceholder(availableInstructoresTextArea, "Available Instructors For Specific Expertise...");
        PlaceholderUtil.addPlaceholder(commite_id, "Enter Commite Number...");
        PlaceholderUtil.addPlaceholder(commite_Name, "What The Commite Created For?");
        PlaceholderUtil.addPlaceholder(member1, "Enter First Instructor...");
        PlaceholderUtil.addPlaceholder(member2, "Enter Second Instructor...");
        PlaceholderUtil.addPlaceholder(member3, "Enter Third Instructor...");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TitleView titleView = new TitleView();
                titleSubmision ts = new titleSubmision();
                String values = ts.retrieveProjecTitle();
                titleView.textArea1.append(values);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database database = new Database();
                String expertiseText = expertise.getText();
                String availableTeachers = database.findInstructor(expertiseText);
                availableInstructoresTextArea.setText(availableTeachers);
            }
        });
        createCommiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String commiteId = commite_id.getText();
                String commiteName = commite_Name.getText();
                String ins1 = member1.getText();
                String ins2 = member2.getText();
                String ins3 = member3.getText();
                CreateCommite createCommite = new CreateCommite(commiteId, commiteName);
                createCommite.createCommite();
                createCommite.asignInstructors(ins1);
                createCommite.asignInstructors(ins2);
                createCommite.asignInstructors(ins3);
                JOptionPane.showMessageDialog(null,"Commite Created successfully");
            }
        });
    }
}
