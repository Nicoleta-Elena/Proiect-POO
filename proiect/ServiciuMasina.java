
import java.util.ArrayList;
import java.util.List;

public class ServiciuMasina {

    private List<Masina> masini;

    public ServiciuMasina() {
        masini = new ArrayList<Masina>();
        masini.add(new Masina("BMW", "X5", 2020, TipDotare.Premium, 45000.0, new Motor(300, "Euro 6", TipCombustibil.Petrol)));
        masini.add(new Masina("Toyota", "Corolla", 2018, TipDotare.Standard, 22000.0, new Motor(120, "Euro 6", TipCombustibil.Diesel)));
        masini.add(new Masina("Volkswagen", "Golf", 2008, TipDotare.Basic, 8500.0, new Motor(105, "Euro 4", TipCombustibil.Petrol)));
        masini.add(new Masina("Renault", "Clio", 2015, TipDotare.Comfort, 14000.0, new Motor(90, "Euro 5", TipCombustibil.Diesel)));
        masini.add(new Masina("Ford", "Focus", 2012, TipDotare.Standard, 12000.0, new Motor(125, "Euro 5", TipCombustibil.Petrol)));
        masini.add(new Masina("Hyundai", "Tucson", 2021, TipDotare.Premium, 28000.0, new Motor(136, "Euro 6", TipCombustibil.Diesel)));
        masini.add(new Masina("Fiat", "500", 2019, TipDotare.Basic, 16000.0, new Motor(69, "Euro 6", TipCombustibil.Petrol)));
        masini.add(new Masina("Skoda", "Octavia", 2017, TipDotare.Premium, 19000.0, new Motor(150, "Euro 6", TipCombustibil.Diesel)));
        masini.add(new Masina("Opel", "Astra", 2010, TipDotare.Basic, 9000.0, new Motor(115, "Euro 4", TipCombustibil.Diesel)));
        masini.add(new Masina("Peugeot", "308", 2016, TipDotare.Comfort, 17500.0, new Motor(130, "Euro 6", TipCombustibil.Petrol)));
    }

    public List<String> getModeleMasina(String marca, int anFabricatie) {
        List<String> modele = new ArrayList<String>();

        for (int i = 0; i < masini.size(); i++) {
            Masina m = masini.get(i);

            if (m.getMarca().equals(marca) && m.getAnFabricatie() == anFabricatie) {
                modele.add(m.getModel());
            }
        }

        return modele;
    }
}
