import java.io.*;
import java.util.*;

public class ColectieContracteAsigurare {
    private ArrayList<ContractAsigurare> contracteAsigurare = new ArrayList<>();
    private static final String FILE = "contracte_asigurare.csv";

    public ColectieContracteAsigurare() {
        contracteAsigurare = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] t = linie.split(",", -1);
                int idx = 0;
                int numarUnic = Integer.parseInt(t[idx++]);
                String clientNume = t[idx++];
                String clientPrenume = t[idx++];
                String clientCnp = t[idx++];
                String masinaMarca = t[idx++];
                String masinaModel = t[idx++];
                int masinaAn = Integer.parseInt(t[idx++]);
                TipDotare masinaDotare = TipDotare.valueOf(t[idx++]);
                double masinaPret = Double.parseDouble(t[idx++]);
                int motorPutere = Integer.parseInt(t[idx++]);
                String motorEuro = t[idx++];
                TipCombustibil motorCombustibil = TipCombustibil.valueOf(t[idx++]);
                String[] riscuriArr = t[idx++].split(";");
                ArrayList<TipRisc> riscuri = new ArrayList<>();
                for (String r : riscuriArr)
                    if (!r.isEmpty())
                        riscuri.add(TipRisc.valueOf(r));
                FrecventaPlata frecventaPlata = FrecventaPlata.valueOf(t[idx++]);
                double rataFinala = Double.parseDouble(t[idx++]);
                StatusContract status = StatusContract.valueOf(t[idx++]);

                Client client = new Client(clientNume, clientPrenume, clientCnp);
                Motor motor = new Motor(motorPutere, motorEuro, motorCombustibil);
                Masina masina = new Masina(masinaMarca, masinaModel, masinaAn, masinaDotare, masinaPret, motor);
                ContractAsigurare contract = new ContractAsigurare(client, masina, riscuri, frecventaPlata);
                contract.setStatus(status);
                contracteAsigurare.add(contract);
            }
        } catch (IOException e) {
        }
    }

    public void salveaza() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (ContractAsigurare c : contracteAsigurare) {
                Masina m = c.getMasina();
                Motor mot = m.getMotor();
                Client cl = c.getClient();
                StringBuilder riscuriStr = new StringBuilder();
                for (TipRisc r : c.getRiscuri()) {
                    if (riscuriStr.length() > 0)
                        riscuriStr.append(";");
                    riscuriStr.append(r.name());
                }
                bw.write(
                        c.getNumarUnic() + "," +
                                cl.getNume() + "," + cl.getPrenume() + "," + cl.getCnp() + "," +
                                m.getMarca() + "," + m.getModel() + "," + m.getAnFabricatie() + "," +
                                m.getDotari().name() + "," + m.getPretInitial() + "," +
                                mot.getPutere() + "," + mot.getEuro() + "," + mot.getCombustibil().name() + "," +
                                riscuriStr + "," +
                                c.getFrecventaPlata().name() + "," +
                                c.getRataFinala() + "," +
                                c.getStatus().name());
                bw.newLine();
            }
        } catch (IOException e) {
        }
    }

    public ContractAsigurare cautaContract(int numarUnic) {
        for (ContractAsigurare contractAsigurare : contracteAsigurare) {
            if (contractAsigurare.getNumarUnic() == numarUnic) {
                return contractAsigurare;
            }
        }
        return null;
    }

    public ArrayList<ContractAsigurare> getToateContractele() {
        return contracteAsigurare;
    }

    public boolean inregistreazaPlata(int numarContract, double suma) {
        ContractAsigurare contract = cautaContract(numarContract);
        if (contract != null) {
            new Plata(suma, numarContract);
            return true;
        }
        return false;
    }
}
