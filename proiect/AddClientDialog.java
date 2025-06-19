import javax.swing.*;
import java.awt.*;

public class AddClientDialog extends JDialog {
    public AddClientDialog(JFrame parent) {
        super(parent, "Adauga client", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        JTextField numeField = new JTextField(10);
        JTextField prenumeField = new JTextField(10);
        JTextField cnpField = new JTextField(13);
        JPanel p = new JPanel(new GridLayout(3, 2));
        p.add(new JLabel("Nume:"));
        p.add(numeField);
        p.add(new JLabel("Prenume:"));
        p.add(prenumeField);
        p.add(new JLabel("CNP:"));
        p.add(cnpField);
        int result = JOptionPane.showConfirmDialog(this, p, "Adauga client", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nume = numeField.getText().trim();
            String prenume = prenumeField.getText().trim();
            String cnp = cnpField.getText().trim();
            if (!nume.isEmpty() && !prenume.isEmpty() && !cnp.isEmpty()) {
                if (cnp.length() != 13 || !cnp.matches("\\d{13}")) {
                    JOptionPane.showMessageDialog(this, "CNP-ul nu este valid!", "Eroare", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ColectieClienti colectie = new ColectieClienti();
                colectie.adaugaClient(new Client(nume, prenume, cnp));
                JOptionPane.showMessageDialog(this, "Client adaugat cu succes!", "Succes",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Completati toate campurile!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
        dispose();
    }
}