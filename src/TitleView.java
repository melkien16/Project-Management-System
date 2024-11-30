import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleView extends JFrame {
    private JPanel panel1;
    public JTextArea textArea1;
    private JTextField reportIDfield;
    private JButton commiteButton;
    private JButton assignCommiteButton;
    private JButton backButton;
    private JTextField commiteNOField;

    public TitleView() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(textArea1, "There Is No assigned Titles...");
        PlaceholderUtil.addPlaceholder(reportIDfield, "Report Number...");
        PlaceholderUtil.addPlaceholder(commiteNOField, "Commite Number...");


        commiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCommiteMembers createCommiteMembers = new CreateCommiteMembers();
                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinatore coordinatore = new Coordinatore();
                dispose();
            }
        });
        assignCommiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reportID = reportIDfield.getText();
                String commiteID = commiteNOField.getText();
                titleSubmision ts = new titleSubmision();
                JOptionPane.showMessageDialog(null, ts.assignTitleCommite(commiteID,reportID));
            }
        });
    }
}
