import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class ColectieMasini {

    private static ArrayList<Masina> masini;

    public ColectieMasini() {
        masini = new ArrayList<Masina>();

        String linie;
        String[] t;
        try {
            var br = new BufferedReader(new FileReader("vehicule.csv"));
            while (br.ready() == true) {
                linie = br.readLine();
                t = linie.split(",");
                masini.add(new Masina(t[0], t[1], Integer.parseInt(t[2]), TipDotare.valueOf(t[3]),
                        Double.parseDouble(t[4]),
                        new Motor(Integer.parseInt(t[5]), t[6], TipCombustibil.valueOf(t[7]))));
            }
            br.close();
        } catch (IOException e) {
        }
    }

    public ArrayList<String> getModeleMasina(String marca, int anFabricatie) {
        ArrayList<String> modele = new ArrayList<String>();

        for (int i = 0; i < masini.size(); i++) {
            Masina m = masini.get(i);

            if (m.getMarca().equals(marca) && m.getAnFabricatie() == anFabricatie) {
                modele.add(m.getModel());
            }
        }
        return modele;
    }

    public ArrayList<String> getMarci() {
        HashSet<String> marci = new HashSet<>();
        for (Masina m : masini) {
            marci.add(m.getMarca());
        }
        return new ArrayList<>(marci);
    }

    public ArrayList<Integer> getAni() {
        HashSet<Integer> ani = new HashSet<>();
        for (Masina m : masini) {
            ani.add(m.getAnFabricatie());
        }
        return new ArrayList<>(ani);
    }
}
