import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ReinoireContractDialog extends JDialog {
    public ReinoireContractDialog(JFrame parent, Angajat angajat) {
        super(parent, "Reinoire contract", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        ColectieClienti colectieClienti = new ColectieClienti();
        ArrayList<Client> clienti = colectieClienti.getClienti();
        String[] clientStrings = clienti.stream()
                .map(c -> c.getNume() + " " + c.getPrenume() + " (" + c.getCnp() + ")").toArray(String[]::new);
        JComboBox<String> clientBox = new JComboBox<>(clientStrings);

        JPanel p1 = new JPanel(new GridLayout(1, 2));
        p1.add(new JLabel("Client:"));
        p1.add(clientBox);
        int result1 = JOptionPane.showConfirmDialog(this, p1, "Selecteaza client", JOptionPane.OK_CANCEL_OPTION);
        if (result1 != JOptionPane.OK_OPTION) {
            dispose();
            return;
        }
        int clientIdx = clientBox.getSelectedIndex();
        if (clientIdx < 0) {
            dispose();
            return;
        }
        Client client = clienti.get(clientIdx);

        ColectieContracteAsigurare colectieContracte = new ColectieContracteAsigurare();
        ArrayList<ContractAsigurare> contracteActive = new ArrayList<>();
        for (ContractAsigurare c : colectieContracte.getToateContractele()) {
            if (c.getClient().getCnp().trim().equals(client.getCnp().trim()) && c.getStatus() == StatusContract.Activ) {
                contracteActive.add(c);
            }
        }
        if (contracteActive.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Clientul nu are contracte active!", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            return;
        }
        String[] contractStrings = contracteActive.stream()
                .map(c -> "#" + c.getNumarUnic() + " - " + c.getMasina().getMarca() + " " + c.getMasina().getModel()
                        + " (" + c.getMasina().getAnFabricatie() + ")")
                .toArray(String[]::new);
        JComboBox<String> contractBox = new JComboBox<>(contractStrings);
        JPanel p2 = new JPanel(new GridLayout(1, 2));
        p2.add(new JLabel("Contract activ:"));
        p2.add(contractBox);
        int result2 = JOptionPane.showConfirmDialog(this, p2, "Selecteaza contract", JOptionPane.OK_CANCEL_OPTION);
        if (result2 != JOptionPane.OK_OPTION) {
            dispose();
            return;
        }
        int contractIdx = contractBox.getSelectedIndex();
        if (contractIdx < 0) {
            dispose();
            return;
        }
        ContractAsigurare contractVechi = contracteActive.get(contractIdx);

        ColectieMasini colectieMasini = new ColectieMasini();
        ArrayList<Masina> masini = getAllMasini(colectieMasini);
        String[] masinaStrings = masini.stream()
                .map(m -> m.getMarca() + " " + m.getModel() + " (" + m.getAnFabricatie() + ")").toArray(String[]::new);
        JComboBox<String> masinaBox = new JComboBox<>(masinaStrings);
        JPanel p3 = new JPanel(new GridLayout(1, 2));
        p3.add(new JLabel("Masina noua:"));
        p3.add(masinaBox);
        int result3 = JOptionPane.showConfirmDialog(this, p3, "Selecteaza masina noua", JOptionPane.OK_CANCEL_OPTION);
        if (result3 != JOptionPane.OK_OPTION) {
            dispose();
            return;
        }
        int masinaIdx = masinaBox.getSelectedIndex();
        if (masinaIdx < 0) {
            dispose();
            return;
        }
        Masina masinaNoua = masini.get(masinaIdx);

        ContractAsigurare contractNou = angajat.reinoireContractAsigurare(contractVechi, masinaNoua);
        colectieContracte.getToateContractele().add(contractNou);
        colectieContracte.salveaza();
        JOptionPane.showMessageDialog(this, "Contractul vechi a fost anulat si unul nou a fost creat!", "Succes",
                JOptionPane.INFORMATION_MESSAGE);
        dispose();
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