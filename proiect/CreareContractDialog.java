
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class CreareContractDialog extends JDialog {

    public CreareContractDialog(JFrame parent, Angajat angajat) {
        super(parent, "Creare contract", true);
        setSize(500, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 10, 10));

        ColectieClienti colectieClienti = new ColectieClienti();
        ArrayList<Client> clienti = colectieClienti.getClienti();
        String[] clientStrings = clienti.stream()
                .map(c -> c.getNume() + " " + c.getPrenume() + " (" + c.getCnp() + ")").toArray(String[]::new);
        JComboBox<String> clientBox = new JComboBox<>(clientStrings);

        ColectieMasini colectieMasini = new ColectieMasini();
        ArrayList<String> marci = colectieMasini.getMarci();
        JComboBox<String> marcaBox = new JComboBox<>(marci.toArray(new String[0]));
        JComboBox<Integer> anBox = new JComboBox<>();
        JComboBox<String> modelBox = new JComboBox<>();

        JLabel clientLabel = new JLabel("Client:");
        JLabel marcaLabel = new JLabel("Marca:");
        JLabel anLabel = new JLabel("An fabricatie:");
        JLabel modelLabel = new JLabel("Model:");

        Runnable updateAni = () -> {
            String marca = (String) marcaBox.getSelectedItem();
            anBox.removeAllItems();
            if (marca != null) {
                Set<Integer> aniSet = new TreeSet<>();
                for (Masina m : getAllMasini(colectieMasini)) {
                    if (marca.equals(m.getMarca())) {
                        aniSet.add(m.getAnFabricatie());
                    }
                }
                for (Integer an : aniSet) {
                    anBox.addItem(an);
                }
            }
        };

        Runnable updateModels = () -> {
            String marca = (String) marcaBox.getSelectedItem();
            Integer an = (Integer) anBox.getSelectedItem();
            modelBox.removeAllItems();
            if (marca != null && an != null) {
                ArrayList<String> modele = colectieMasini.getModeleMasina(marca, an);
                for (String m : modele) {
                    modelBox.addItem(m);
                }
            }
        };

        marcaBox.addActionListener(e2 -> {
            updateAni.run();
            updateModels.run();
        });
        anBox.addActionListener(e2 -> updateModels.run());

        if (!marci.isEmpty()) {
            updateAni.run();
            updateModels.run();
        }

        add(clientLabel);
        add(clientBox);
        add(marcaLabel);
        add(marcaBox);
        add(anLabel);
        add(anBox);
        add(modelLabel);
        add(modelBox);

        JButton okBtn = new JButton("Alege modelul");
        add(new JLabel());
        add(okBtn);

        okBtn.addActionListener(ev -> {
            int clientIdx = clientBox.getSelectedIndex();
            String marca = (String) marcaBox.getSelectedItem();
            Integer an = (Integer) anBox.getSelectedItem();
            String model = (String) modelBox.getSelectedItem();
            if (clientIdx >= 0 && marca != null && an != null && model != null) {
                Masina masinaSelectata = null;
                for (Masina m : getAllMasini(colectieMasini)) {
                    if (m.getMarca().equals(marca) && m.getAnFabricatie() == an && m.getModel().equals(model)) {
                        masinaSelectata = m;
                        break;
                    }
                }
                if (masinaSelectata != null) {
                    new ContractDetaliiDialog(this, angajat, clienti.get(clientIdx), masinaSelectata);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Masina nu a fost gasita!", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selectati clientul, marca, anul si modelul!", "Eroare",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        setVisible(true);
    }

    private ArrayList<Masina> getAllMasini(ColectieMasini colectieMasini) {
        try {
            java.lang.reflect.Field f = colectieMasini.getClass().getDeclaredField("masini");
            f.setAccessible(true);
            return (ArrayList<Masina>) f.get(colectieMasini);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
