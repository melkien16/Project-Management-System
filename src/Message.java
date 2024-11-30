import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;

class PlaceholderUtil {
    public static void addPlaceholder(JTextComponent textComponent, String placeholder) {
        textComponent.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textComponent.getText().equals(placeholder)) {
                    textComponent.setText("");
                    textComponent.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textComponent.getText().isEmpty()) {
                    textComponent.setForeground(Color.GRAY);
                    textComponent.setText(placeholder);
                }
            }
        });
        textComponent.setText(placeholder);
        textComponent.setForeground(Color.GRAY);
    }
}

public class Message extends JFrame {
    private JPanel panel;
    private JTextArea wreadMessageField;
    private JButton showMessagesButton;
    private JTextArea writeMessageField;
    private JTextField recieverUsername;
    private JButton sendButton;
    private JPanel inerpanel;
    private JButton homeBtn;
    private JButton clearButton;
    private JButton backButton;
    private HomeDesign homeDesign;
    private registerActiveUser rg = new registerActiveUser();

    Message() {
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setVisible(true);

        PlaceholderUtil.addPlaceholder(writeMessageField, "Write your message...");
        PlaceholderUtil.addPlaceholder(recieverUsername, "Receiver's username...");

        homeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeDesign hd = new HomeDesign();
                hd.setVisible(true);
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receiver = recieverUsername.getText();
                String messageField = writeMessageField.getText();
                LocalDateTime currentDateTime = LocalDateTime.now();
                registerActiveUser active = rg.getActiveUser();
                String Role = active.getActiveRole();
                String Sender = active.getActiveUsername();
                MessageSend messageSend = new MessageSend(receiver, messageField, Sender, Timestamp.valueOf(currentDateTime));
                AuthenticateReciever authenticateReciever = new AuthenticateReciever();
                boolean isLeader = authenticateReciever.MemberRole(Sender);
                boolean isStudentAvailable = authenticateReciever.availableStudent(receiver);
                boolean isCoordinatorAvailable = authenticateReciever.availableCoordinator(receiver);
                boolean isInstructorAvailable = authenticateReciever.availableInstructor(receiver);
                if (Role.equalsIgnoreCase("student")) {
                    if (isLeader) {
                        if (isStudentAvailable || isCoordinatorAvailable || isInstructorAvailable) {
                            JOptionPane.showMessageDialog(null, messageSend.send_message() + receiver);
                        }
                    } else if (isStudentAvailable) {
                        JOptionPane.showMessageDialog(null, messageSend.send_message() + receiver);
                    } else {
                        JOptionPane.showMessageDialog(null, "Message failed! Invalid receiver!");
                    }
                } else {
                    if (isStudentAvailable || isCoordinatorAvailable || isInstructorAvailable) {
                        JOptionPane.showMessageDialog(null, messageSend.send_message() + receiver);
                    } else {
                        JOptionPane.showMessageDialog(null, "Message failed! Invalid receiver!");
                    }
                }
            }
        });

        showMessagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wreadMessageField.setLineWrap(true);
                wreadMessageField.setWrapStyleWord(true);

                // Add the JScrollPane to the frame
                MessageSend messageSend = new MessageSend();
                registerActiveUser active = rg.getActiveUser();
                String Activeuser = active.getActiveUsername();
                String text = messageSend.received_message(Activeuser);
                if (text == null) {
                    JOptionPane.showMessageDialog(null, "No Message available yet");
                }
                wreadMessageField.append(text);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageSend messageSend = new MessageSend();
                registerActiveUser active = rg.getActiveUser();
                String ActivePerl = active.getActiveUsername();
                JOptionPane.showMessageDialog(null, messageSend.deleteMessage(ActivePerl));
            }
        });
    }
}
