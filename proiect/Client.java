import java.util.ArrayList;
import java.util.Date;

public class Client extends Persoana {

    private ArrayList<Accident> accidente;
    private ArrayList<ContractAsigurare> contracte;

    public Client(String nume, String prenume, String cnp) {
        super(nume, prenume, cnp);

        this.accidente = new ArrayList<Accident>();
        this.contracte = new ArrayList<ContractAsigurare>();
    }

    public void salveaza() {
        ColectieClienti colectie = new ColectieClienti();
        colectie.adaugaClient(this);
    }

    public void adaugaAccident(Accident accident) {
        ColectieClienti colectie = new ColectieClienti();
        for (Client c : colectie.getClienti()) {
            if (c.getCnp().equals(this.getCnp())) {
                c.accidente.add(accident);
                colectie.actualizeazaClient(c);
                break;
            }
        }
    }

    public void adaugaContract(ContractAsigurare contractAsigurare) {
        ColectieClienti colectie = new ColectieClienti();
        for (Client c : colectie.getClienti()) {
            if (c.getCnp().equals(this.getCnp())) {
                c.contracte.add(contractAsigurare);
                colectie.actualizeazaClient(c);
                break;
            }
        }
    }

    public int getNrAccidenteUltimii5Ani() {
        Date dataCurenta = new Date();
        int anCurent = dataCurenta.getYear();

        int numarAccidente = 0;
        for (int i = 0; i < accidente.size(); i++) {
            Accident accident = accidente.get(i);
            if (anCurent - accident.getAnAccident() <= 5) {
                numarAccidente++;
            }
        }
        return numarAccidente;
    }

    public ArrayList<Accident> getAccidente() {
        return new ArrayList<>(accidente);
    }

    public ArrayList<ContractAsigurare> getContracte() {
        return new ArrayList<>(contracte);
    }
}
