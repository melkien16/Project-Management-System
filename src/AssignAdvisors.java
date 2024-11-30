import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignAdvisors extends JFrame {
    public JTextArea textArea1;
    private JTextField reportIDfield;
    private JTextField commiteNOField;
    private JButton advisorsButton;
    private JButton assignAdvisorButton;
    private JButton backButton;
    private JPanel panel;

    public AssignAdvisors() {

        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(textArea1, "\t\t\tNo project information yet!");
        PlaceholderUtil.addPlaceholder(reportIDfield, "Report NO");
        PlaceholderUtil.addPlaceholder(commiteNOField, "Commite NO");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinatore cd = new Coordinatore();
                dispose();
            }
        });
        advisorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdvisorsInfo advisorsInfo = new AdvisorsInfo();
                dispose();
            }
        });
        assignAdvisorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCommite createCommite = new CreateCommite();
                String reportID = reportIDfield.getText();
                String advisor = commiteNOField.getText();
                JOptionPane.showMessageDialog(null,createCommite.assignprojectAdvisors(advisor,reportID));
            }
        });
    }
}
