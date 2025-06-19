
import java.io.FileWriter;
import java.util.Date;

public class Plata {

    private static int numarUnicCurent = 1;

    private Date data;
    private double sumaPlatita;
    private int numarPlata;
    private int numarContract;

    public Plata(double sumaPlatita, int numarContract) {
        this.sumaPlatita = sumaPlatita;
        this.numarContract = numarContract;
        this.data = new Date();
        this.numarPlata = numarUnicCurent;
        numarUnicCurent++;
        salveaza();
    }

    public Date getData() {
        return data;
    }

    public double getSumaPlatita() {
        return sumaPlatita;
    }

    public int getNumarPlata() {
        return numarPlata;
    }

    public int getNumarContract() {
        return numarContract;
    }

    public void salveaza() {
        try (FileWriter fw = new FileWriter("plati.csv", true)) {
            fw.append(numarPlata + "," + numarContract + "," + sumaPlatita + "," + data.getTime() + "\n");
        } catch (Exception e) {
        }
    }
}
