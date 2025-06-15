
import java.util.Date;

public class Accident {

    private Date dataAccident;
    private double pagubeInEuro;

    public Accident(Date dataAccident, double pagubeInEuro) {
        this.dataAccident = dataAccident;
        this.pagubeInEuro = pagubeInEuro;
    }

    public int getAnAccident() {
        return dataAccident.getYear();
    }
}
