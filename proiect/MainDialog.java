
import java.awt.*;
import javax.swing.*;

public class MainDialog extends JFrame {

    private Angajat angajat;

    public MainDialog(Angajat angajat) {
        this.angajat = angajat;
        setTitle("Aplicatie - Bine ai venit");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel welcome = new JLabel("Bine ai venit, " + angajat.nume + " " + angajat.prenume + "!",
                SwingConstants.CENTER);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(welcome);
        panel.add(Box.createVerticalStrut(20));

        JButton adaugaClientBtn = new JButton("Adauga client");
        adaugaClientBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(adaugaClientBtn);
        panel.add(Box.createVerticalStrut(10));

        JButton adaugaMasinaBtn = new JButton("Adauga masina");
        adaugaMasinaBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(adaugaMasinaBtn);
        panel.add(Box.createVerticalStrut(10));

        JButton adaugaAccidentClientBtn = new JButton("Adauga accident client");
        adaugaAccidentClientBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(adaugaAccidentClientBtn);
        panel.add(Box.createVerticalStrut(10));

        JButton creareContractBtn = new JButton("Creare contract asigurare");
        creareContractBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(creareContractBtn);
        panel.add(Box.createVerticalStrut(10));

        JButton reinoireContractBtn = new JButton("Reinoire contract");
        reinoireContractBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(reinoireContractBtn);
        panel.add(Box.createVerticalStrut(10));

        adaugaClientBtn.addActionListener(e -> showAddClientDialog());
        adaugaMasinaBtn.addActionListener(e -> showAddMasinaDialog());
        adaugaAccidentClientBtn.addActionListener(e -> showAddAccidentClientDialog());
        reinoireContractBtn.addActionListener(e -> showReinoireContractDialog());

        creareContractBtn.addActionListener(e -> {
            setVisible(false);
            new CreareContractDialog(this, angajat);
            setVisible(true);
        });

        add(panel);
        setVisible(true);
    }

    private void showAddClientDialog() {
        new AddClientDialog(this);
    }

    private void showAddMasinaDialog() {
        new AddMasinaDialog(this);
    }

    private void showAddAccidentClientDialog() {
        new AddAccidentDialog(this);
    }

    private void showReinoireContractDialog() {
        new ReinoireContractDialog(this, angajat);
    }
}
