import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleSubmisionForm extends JFrame {
    private JTextArea description;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JPanel panel;
    private JTextField textField1;

    public TitleSubmisionForm() {
        setSize(1600,900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(textField1, "Enter Your Title...");
        PlaceholderUtil.addPlaceholder(description, "Enter Your Title Description...");

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentHomePage studentHomePage = new StudentHomePage();
                dispose();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerActiveUser rg = new registerActiveUser();
                registerActiveUser gn = rg.getActiveUser();
                String j = gn.getActiveTeamId();

                String Pdescription = description.getText();
                String ptitle = textField1.getText();

                titleSubmision titleSubmision = new titleSubmision(j, ptitle,Pdescription);
                titleSubmision.submitProjectTitle();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerActiveUser rg = new registerActiveUser();
                registerActiveUser gn = rg.getActiveUser();
                String j = gn.getActiveTeamId();
                titleSubmision ts = new titleSubmision();
                description.append(ts.getStatus(j));
                String status = ts.getpStatus();
                if(status == null) {
                    JOptionPane.showMessageDialog(null,"You Don't have any submitted title!");
                } else if(status.equalsIgnoreCase("accepted")) {
                    JOptionPane.showMessageDialog(null,"Congratulation Your title has been approved\nStart to work project progress");
                } else if (status.equalsIgnoreCase("under review")) {
                    JOptionPane.showMessageDialog(null,"Your project is under review\nPlease wait patiently...");
                } else {
                    JOptionPane.showMessageDialog(null,"Your Title had been rejected");
                }

            }
        });
    }
}
