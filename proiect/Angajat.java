
import java.util.ArrayList;

public class Angajat extends Persoana {

    public Angajat(String nume, String prenume, String cnp) {
        super(nume, prenume, cnp);
    }

    public ContractAsigurare creeazaContract(Client client, Masina masina, ArrayList<TipRisc> riscuri,
            FrecventaPlata frecventaPlata) {
        ContractAsigurare contractAsigurare = new ContractAsigurare(client, masina, riscuri, frecventaPlata);
        client.adaugaContract(contractAsigurare);
        return contractAsigurare;
    }

    public void anuleazaContract(ContractAsigurare contractAsigurare) {
        contractAsigurare.setStatus(StatusContract.Inactiv);
    }

    public ContractAsigurare reinoireContractAsigurare(ContractAsigurare contractVechi, Masina masinaNoua) {
        ContractAsigurare contractNou = contractVechi.renoieste(masinaNoua);
        Client client = contractVechi.getClient();
        client.adaugaContract(contractNou);
        return contractNou;
    }

    public Plata creeazaPlata(ContractAsigurare contractAsigurare) {
        double rataFinala = contractAsigurare.getRataFinala();
        return new Plata(rataFinala, contractAsigurare.getNumarUnic());
    }
}
