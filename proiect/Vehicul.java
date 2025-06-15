
import java.util.Date;

public abstract class Vehicul {

    protected String marca;
    protected String model;
    protected int anFabricatie;
    protected TipDotare dotari;
    protected double pretInitial;

    protected double indiceRisc = 100;

    public Vehicul(String marca, String model, int anFabricatie, TipDotare dotari, double pretInitial) {
        this.marca = marca;
        this.model = model;
        this.anFabricatie = anFabricatie;
        this.dotari = dotari;
        this.pretInitial = pretInitial;
    }

    public abstract double calculeazaRisc();

    public String getCaracteristici() {
        String caracteristici = new String();
        caracteristici = caracteristici + "Marca: " + marca;
        caracteristici = caracteristici + "\nModel: " + model;
        caracteristici = caracteristici + "\nAn de fabricatie: " + anFabricatie;
        caracteristici = caracteristici + "\nDotari: " + dotari;
        caracteristici = caracteristici + "\nPret initial: " + pretInitial;
        caracteristici = caracteristici + "\nIndice risc: " + calculeazaRisc();
        return caracteristici;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public int getAnFabricatie() {
        return anFabricatie;
    }

    public int calculeazaVarsta() {
        Date dataCurenta = new Date();
        return dataCurenta.getYear() - anFabricatie;
    }

    public double getPretInitial() {
        return pretInitial;
    }

    public TipDotare getDotari() {
        return dotari;
    }
}
