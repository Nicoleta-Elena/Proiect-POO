import java.util.ArrayList;

public class ContractAsigurare {

    private static int numarUnicCurent = 1;

    private int numarUnic;
    private Client client;
    private Masina masina;
    private ArrayList<TipRisc> riscuri;
    private StatusContract status;
    private double sumaAsigurata;
    private FrecventaPlata frecventaPlata;
    private double rataFinala;

    public ContractAsigurare(Client client, Masina masina, ArrayList<TipRisc> riscuri, FrecventaPlata frecventaPlata) {
        this.client = client;
        this.masina = masina;
        this.riscuri = riscuri;
        this.sumaAsigurata = calculeazaSumaAsigurata();
        this.frecventaPlata = frecventaPlata;
        this.rataFinala = calculeazaRata();

        this.numarUnic = numarUnicCurent;
        numarUnicCurent++;
        this.status = StatusContract.Activ;

        salveaza();
    }

    public int getNumarUnic() {
        return numarUnic;
    }

    public Client getClient() {
        return client;
    }

    public double getRataFinala() {
        return rataFinala;
    }

    public Masina getMasina() {
        return masina;
    }

    public FrecventaPlata getFrecventaPlata() {
        return frecventaPlata;
    }

    public StatusContract getStatus() {
        return status;
    }

    public void setStatus(StatusContract statusNou) {
        this.status = statusNou;
    }

    private void salveaza() {

    }

    public double calculeazaSumaAsigurata() {
        double sumaAsigurata = masina.getPretInitial();
        int varstaMasina = masina.calculeazaVarsta();

        if (varstaMasina < 5) {
            sumaAsigurata = sumaAsigurata * 0.9;
        } else if (varstaMasina < 10) {
            sumaAsigurata = sumaAsigurata * 0.75;
        } else if (varstaMasina < 15) {
            sumaAsigurata = sumaAsigurata * 0.5;
        } else if (varstaMasina < 20) {
            sumaAsigurata = sumaAsigurata * 0.3;
        }

        sumaAsigurata = switch (masina.getDotari()) {
            case TipDotare.Basic ->
                sumaAsigurata * 0.7;
            case TipDotare.Comfort ->
                sumaAsigurata * 0.8;
            case TipDotare.Standard ->
                sumaAsigurata * 0.9;
            case TipDotare.Premium ->
                sumaAsigurata * 0.95;
            default ->
                sumaAsigurata * 0.7;
        };

        return sumaAsigurata;
    }

    public double calculeazaRata() {
        double sumaAsigurata = calculeazaSumaAsigurata();

        double coeficientRisc = 0;
        for (int i = 0; i < riscuri.size(); i++) {
            coeficientRisc = switch (riscuri.get(i)) {
                case TipRisc.Accident ->
                    coeficientRisc + 0.1;
                case TipRisc.Cutremur ->
                    coeficientRisc + 0.01;
                case TipRisc.Incendiu ->
                    coeficientRisc + 0.05;
                default ->
                    coeficientRisc + 0.05;
            };
        }

        double rata = sumaAsigurata * coeficientRisc;

        int nrAccidenteUltimii5Ani = client.getNrAccidenteUltimii5Ani();
        if (nrAccidenteUltimii5Ani > 5) {
            rata = rata * 1.5;
        }

        double reducere = getReducere();
        rata = rata - rata * reducere;

        if (frecventaPlata == FrecventaPlata.Lunar) {
            rata = rata / 12;
        } else if (frecventaPlata == FrecventaPlata.Semestrial) {
            rata = rata / 2;
        }

        return rata;
    }

    public double getReducere() {
        double reducere = 0;

        if (frecventaPlata == FrecventaPlata.Anual) {
            reducere = 0.1;
        } else if (frecventaPlata == FrecventaPlata.Semestrial) {
            reducere = 0.05;
        }

        return reducere;
    }

    public ContractAsigurare renoieste(Masina masinaNoua) {
        this.status = StatusContract.Inactiv;
        return new ContractAsigurare(this.client, masinaNoua, this.riscuri, this.frecventaPlata);
    }

    public ArrayList<TipRisc> getRiscuri() {
        return new ArrayList<>(riscuri);
    }
}
