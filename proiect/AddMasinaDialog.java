
import java.awt.*;
import javax.swing.*;

public class AddMasinaDialog extends JDialog {

    public AddMasinaDialog(JFrame parent) {
        super(parent, "Adauga masina", true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        JTextField marcaField = new JTextField(10);
        JTextField modelField = new JTextField(10);
        JTextField anField = new JTextField(4);
        JComboBox<TipDotare> dotareBox = new JComboBox<>(TipDotare.values());
        JTextField pretField = new JTextField(10);
        JTextField putereField = new JTextField(5);
        JTextField euroField = new JTextField(10);
        JComboBox<TipCombustibil> combustibilBox = new JComboBox<>(TipCombustibil.values());
        JPanel p = new JPanel(new GridLayout(8, 2));
        p.add(new JLabel("Marca:"));
        p.add(marcaField);
        p.add(new JLabel("Model:"));
        p.add(modelField);
        p.add(new JLabel("An fabricatie:"));
        p.add(anField);
        p.add(new JLabel("Dotare:"));
        p.add(dotareBox);
        p.add(new JLabel("Pret initial:"));
        p.add(pretField);
        p.add(new JLabel("Putere motor:"));
        p.add(putereField);
        p.add(new JLabel("Euro motor:"));
        p.add(euroField);
        p.add(new JLabel("Combustibil:"));
        p.add(combustibilBox);
        int result = JOptionPane.showConfirmDialog(this, p, "Adauga masina", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String marca = marcaField.getText().trim();
                String model = modelField.getText().trim();
                int an = Integer.parseInt(anField.getText().trim());
                TipDotare dotare = (TipDotare) dotareBox.getSelectedItem();
                double pret = Double.parseDouble(pretField.getText().trim());
                int putere = Integer.parseInt(putereField.getText().trim());
                String euro = euroField.getText().trim();
                TipCombustibil combustibil = (TipCombustibil) combustibilBox.getSelectedItem();
                Motor motor = new Motor(putere, euro, combustibil);
                Masina masina = new Masina(marca, model, an, dotare, pret, motor);
                try (java.io.FileWriter writer = new java.io.FileWriter("vehicule.csv", true)) {
                    writer.append(marca).append(",");
                    writer.append(model).append(",");
                    writer.append(String.valueOf(an)).append(",");
                    writer.append(dotare.toString()).append(",");
                    writer.append(String.valueOf(pret)).append(",");
                    writer.append(String.valueOf(putere)).append(",");
                    writer.append(euro).append(",");
                    writer.append(combustibil.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(this, "Masina adaugata cu succes!", "Succes",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Date invalide!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
        dispose();
    }
}
