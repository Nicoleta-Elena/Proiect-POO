
import java.util.Date;

public class Plata {

    private static int numarUnicCurent = 1;

    private Date data;
    private double sumaPlatita;
    private int numarPlata;

    public Plata(double sumaPlatita) {
        this.sumaPlatita = sumaPlatita;

        this.data = new Date();
        this.numarPlata = numarUnicCurent;
        numarUnicCurent++;

        salveaza();
    }

    public void salveaza() {

    }
}
