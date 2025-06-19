import java.io.*;
import java.util.*;

public class ColectieClienti {
    private ArrayList<Client> clienti = new ArrayList<>();
    private static final String FILE = "clienti.csv";

    public ColectieClienti() {
        clienti = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] t = linie.split(",", -1);
                Client client = new Client(t[0], t[1], t[2]);

                // Accidente
                if (t.length > 3 && !t[3].isEmpty()) {
                    for (String acc : t[3].split(";")) {
                        String[] accParts = acc.split(":");
                        if (accParts.length >= 2) {
                            Date data = java.sql.Date.valueOf(accParts[0]);
                            double paguba = Double.parseDouble(accParts[1]);
                            client.getAccidente().add(new Accident(data, paguba));
                        }
                    }
                }
                clienti.add(client);
            }
        } catch (IOException e) {
        }
    }

    public void salveaza() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Client client : clienti) {
                StringBuilder accStr = new StringBuilder();
                for (Accident acc : client.getAccidente()) {
                    if (accStr.length() > 0)
                        accStr.append(";");
                    accStr.append(new java.sql.Date(acc.getDataAccident().getTime()).toString())
                            .append(":").append(acc.getPagubeInEuro());
                }

                bw.write(client.getNume() + "," + client.getPrenume() + "," + client.getCnp() + "," + accStr);
                bw.newLine();
            }
        } catch (IOException e) {
        }
    }

    public void adaugaClient(Client client) {
        if (cautaClient(client.getCnp()) == null) {
            clienti.add(client);
            salveaza();
        }
    }

    public Client cautaClient(String cnp) {
        for (Client c : clienti) {
            if (c.getCnp().equals(cnp))
                return c;
        }
        return null;
    }

    public void actualizeazaClient(Client client) {
        for (int i = 0; i < clienti.size(); i++) {
            if (clienti.get(i).getCnp().equals(client.getCnp())) {
                clienti.set(i, client);
                salveaza();
                return;
            }
        }
    }

    public ArrayList<Client> getClienti() {
        return clienti;
    }
}
