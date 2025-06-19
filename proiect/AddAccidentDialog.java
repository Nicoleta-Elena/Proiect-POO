import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddAccidentDialog extends JDialog {
    public AddAccidentDialog(JFrame parent) {
        super(parent, "Adauga accident client", true);
        setSize(350, 200);
        setLocationRelativeTo(parent);
        ColectieClienti colectie = new ColectieClienti();
        ArrayList<Client> clienti = colectie.getClienti();
        String[] clientStrings = clienti.stream().map(c -> c.getNume() + " " + c.getPrenume() + " (" + c.getCnp() + ")")
                .toArray(String[]::new);
        JComboBox<String> clientBox = new JComboBox<>(clientStrings);
        JTextField dataField = new JTextField(10);
        JTextField pagubaField = new JTextField(10);
        JPanel p = new JPanel(new GridLayout(3, 2));
        p.add(new JLabel("Client:"));
        p.add(clientBox);
        p.add(new JLabel("Data (yyyy-mm-dd):"));
        p.add(dataField);
        p.add(new JLabel("Paguba (EUR):"));
        p.add(pagubaField);
        int result = JOptionPane.showConfirmDialog(this, p, "Adauga accident client", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int clientIdx = clientBox.getSelectedIndex();
                if (clientIdx < 0) {
                    throw new Exception();
                }
                String cnp = clienti.get(clientIdx).getCnp();
                ColectieClienti colectieActuala = new ColectieClienti();
                Client clientActual = colectieActuala.cautaClient(cnp);
                if (clientActual == null) {
                    throw new Exception();
                }
                java.sql.Date data = java.sql.Date.valueOf(dataField.getText().trim());
                double paguba = Double.parseDouble(pagubaField.getText().trim());
                Accident accident = new Accident(data, paguba);
                clientActual.adaugaAccident(accident);
                JOptionPane.showMessageDialog(this, "Accident adaugat la client cu succes!", "Succes",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Date invalide sau client neselectat!", "Eroare",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        dispose();
    }
}