import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class projectInfo extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JButton searchButton;
    private JTextArea textArea1;
    private JButton BackBtn;

    projectInfo() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(textField1, "Enter Project Title...");
        PlaceholderUtil.addPlaceholder(textArea1, "All Available Project Information...");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RetrieveProjects retrieveProjects = new RetrieveProjects();
                textArea1.setText(retrieveProjects.searchProjects(textField1.getText()));
            }
        });
        BackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentHomePage studentHomePage = new StudentHomePage();
                dispose();
            }
        });
    }
}
