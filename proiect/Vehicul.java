import java.io.FileWriter;
import java.io.IOException;
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

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public int getAnFabricatie() {
        return anFabricatie;
    }

    public double getPretInitial() {
        return pretInitial;
    }

    public TipDotare getDotari() {
        return dotari;
    }

    protected void salveaza() {
        try (FileWriter writer = new FileWriter("vehicule.csv", true)) {
            writer.append(marca).append(",");
            writer.append(model).append(",");
            writer.append(String.valueOf(anFabricatie)).append(",");
            writer.append(dotari.toString()).append(",");
            writer.append(String.valueOf(pretInitial)).append("\n");
        } catch (IOException e) {
        }
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

    public int calculeazaVarsta() {
        Date dataCurenta = new Date();
        return dataCurenta.getYear() - anFabricatie;
    }
}
