import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ContractDetaliiDialog extends JDialog {
    public ContractDetaliiDialog(JDialog parent, Angajat angajat, Client client, Masina masina) {
        super(parent, "Detalii contract", true);
        setSize(450, 500);
        setLocationRelativeTo(parent);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextArea carDetails = new JTextArea(masina.getCaracteristici());
        carDetails.setEditable(false);
        carDetails.setBorder(BorderFactory.createTitledBorder("Caracteristici masina"));
        add(carDetails);

        JPanel riscuriPanel = new JPanel();
        riscuriPanel.setBorder(BorderFactory.createTitledBorder("Riscuri asigurate"));
        riscuriPanel.setLayout(new FlowLayout());
        ArrayList<JCheckBox> riscBoxes = new ArrayList<>();
        for (TipRisc risc : TipRisc.values()) {
            JCheckBox box = new JCheckBox(risc.name());
            box.setSelected(true);
            riscBoxes.add(box);
            riscuriPanel.add(box);
        }
        add(riscuriPanel);

        JPanel freqPanel = new JPanel();
        freqPanel.setBorder(BorderFactory.createTitledBorder("Frecventa plata"));
        JComboBox<FrecventaPlata> freqBox = new JComboBox<>(FrecventaPlata.values());
        freqPanel.add(freqBox);
        add(freqPanel);

        JButton calcBtn = new JButton("Calculeaza contract");
        add(calcBtn);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        JTextArea summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryPanel.add(summaryArea);
        JButton createBtn = new JButton("Creeaza contract");
        createBtn.setEnabled(false);
        summaryPanel.add(createBtn);
        add(summaryPanel);

        final ContractAsigurare[] contractHolder = new ContractAsigurare[1];

        calcBtn.addActionListener(ev -> {
            ArrayList<TipRisc> riscuriSelectate = new ArrayList<>();
            for (int i = 0; i < riscBoxes.size(); i++) {
                if (riscBoxes.get(i).isSelected()) {
                    riscuriSelectate.add(TipRisc.values()[i]);
                }
            }
            FrecventaPlata freq = (FrecventaPlata) freqBox.getSelectedItem();
            ContractAsigurare contract = angajat.creeazaContract(client, masina, riscuriSelectate, freq);
            ColectieContracteAsigurare colectieContracte = new ColectieContracteAsigurare();
            colectieContracte.getToateContractele().add(contract);
            colectieContracte.salveaza();
            contractHolder[0] = contract;
            double sumaAsigurata = contract.calculeazaSumaAsigurata();
            double rataFinala = contract.calculeazaRata();
            StringBuilder rez = new StringBuilder();
            rez.append("Suma asigurata: ").append(sumaAsigurata).append("\n");
            rez.append("Rata finala: ").append(rataFinala).append("\n");
            rez.append("Riscuri: ");
            for (TipRisc r : riscuriSelectate) {
                rez.append(r.name()).append(" ");
            }
            rez.append("\nFrecventa plata: ").append(freq.name());
            if (freq == FrecventaPlata.Semestrial) {
                rez.append(" (reducere 5%)");
            }
            if (freq == FrecventaPlata.Anual) {
                rez.append(" (reducere 10%)");
            }
            summaryArea.setText(rez.toString());
            createBtn.setEnabled(true);
        });

        createBtn.addActionListener(ev -> {
            if (contractHolder[0] != null) {
                String cnp = client.getCnp();
                ColectieClienti colectieActuala = new ColectieClienti();
                Client clientActual = colectieActuala.cautaClient(cnp);
                if (clientActual != null) {
                    clientActual.adaugaContract(contractHolder[0]);
                    JOptionPane.showMessageDialog(this, "Contractul a fost creat si salvat!", "Succes",
                            JOptionPane.INFORMATION_MESSAGE);
                    Imprimanta imp = new Imprimanta();
                    imp.printeaza(contractHolder[0]);
                    ContractAsigurare contract = contractHolder[0];
                    double suma = contract.getRataFinala();
                    String msg = "Introdu suma platita pentru contractul nr. " + contract.getNumarUnic()
                            + " (valoare rata: " + suma + ")";
                    String input = JOptionPane.showInputDialog(this, msg, String.valueOf(suma));
                    if (input != null) {
                        try {
                            double sumaPlatita = Double.parseDouble(input);
                            Plata plata = angajat.creeazaPlata(contract);
                            if (Math.abs(sumaPlatita - plata.getSumaPlatita()) > 0.01) {
                                JOptionPane.showMessageDialog(this,
                                        "Atentie: suma introdusa difera de rata calculata! S-a inregistrat suma calculata automat.",
                                        "Atentie", JOptionPane.WARNING_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "Plata inregistrata cu succes!", "Succes",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Suma invalida!", "Eroare", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Eroare la salvarea contractului!", "Eroare",
                            JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });
        setVisible(true);
    }
}