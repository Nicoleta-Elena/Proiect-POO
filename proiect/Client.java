
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client extends Persoana {

    private List<Accident> accidente;
    private List<ContractAsigurare> contracte;

    public Client(String nume, String prenume, String cnp) {
        super(nume, prenume, cnp);

        this.accidente = new ArrayList<Accident>();
        this.contracte = new ArrayList<ContractAsigurare>();
    }

    public void adaugaAccident(Accident accident) {
        accidente.add(accident);
    }

    public int getNrAccidenteUltimii5Ani() {
        Date dataCurenta = new Date();
        int anCurent = dataCurenta.getYear();

        int numarAccidente = 0;
        for (int i = 0; i < accidente.size(); i++) {
            Accident accident = accidente.get(i);
            if (anCurent - accident.getAnAccident() > 5) {
                numarAccidente++;
            }
        }
        return numarAccidente;
    }

    public void adaugaContract(ContractAsigurare contractAsigurare) {
        contracte.add(contractAsigurare);
    }
}
