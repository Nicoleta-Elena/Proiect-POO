
import java.io.*;
import javax.swing.*;

public class LoginDialog extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;

    public LoginDialog() {
        setTitle("Autentificare");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        userField = new JTextField(15);
        passField = new JPasswordField(15);

        panel.add(new JLabel("Utilizator:"));
        panel.add(userField);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Parola:"));
        panel.add(passField);
        panel.add(Box.createVerticalStrut(15));

        loginBtn = new JButton("Autentificare");
        panel.add(loginBtn);

        add(panel);
        setVisible(true);

        loginBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword());
            Angajat loggedIn = null;
            try (BufferedReader br = new BufferedReader(new FileReader("angajati.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] t = line.split(",");
                    if (t.length >= 5) {
                        if (t[0].equals(username) && t[1].equals(password)) {
                            loggedIn = new Angajat(t[2], t[3], t[4]);
                            break;
                        }
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Eroare la citirea fisierului!", "Eroare",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (loggedIn != null) {
                JOptionPane.showMessageDialog(this, "Autentificare reusita!", "Succes",
                        JOptionPane.INFORMATION_MESSAGE);
                new MainDialog(loggedIn);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Utilizator sau parola incorecta.", "Eroare",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
