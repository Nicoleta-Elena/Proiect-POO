import java.io.FileWriter;
import java.io.IOException;

public class Masina extends Vehicul {

    private Motor motor;

    public Masina(String marca, String model, int anFabricatie, TipDotare dotari, double pretInitial, Motor motor) {
        super(marca, model, anFabricatie, dotari, pretInitial);
        this.motor = motor;
    }

    @Override
    protected void salveaza() {
        try (FileWriter writer = new FileWriter("vehicule.csv", true)) {
            writer.append(marca).append(",");
            writer.append(model).append(",");
            writer.append(String.valueOf(anFabricatie)).append(",");
            writer.append(dotari.toString()).append(",");
            writer.append(String.valueOf(pretInitial)).append(",");
            writer.append(String.valueOf(motor.getPutere())).append(",");
            writer.append(motor.getEuro()).append(",");
            writer.append(motor.getCombustibil().toString()).append("\n");
        } catch (IOException e) {
        }
    }

    @Override
    public double calculeazaRisc() {
        double multiplicatorRiscPutereMotor = motor.getMultiplicatorRisc();
        return indiceRisc * multiplicatorRiscPutereMotor;
    }

    @Override
    public String getCaracteristici() {
        String caracteristici = super.getCaracteristici();
        caracteristici = caracteristici + "\nIndice poluare: " + calculeazaPoluarea();
        caracteristici = caracteristici + "\nMotor\n" + motor.getCaracteristici();
        return caracteristici;
    }

    public double calculeazaPoluarea() {
        double multiplicatorPoluareVarsta = 1;
        if (anFabricatie > 2020) {
            multiplicatorPoluareVarsta = 1.1;
        } else if (anFabricatie > 2015) {
            multiplicatorPoluareVarsta = 1.2;
        } else if (anFabricatie > 2010) {
            multiplicatorPoluareVarsta = 1.3;
        } else if (anFabricatie > 2000) {
            multiplicatorPoluareVarsta = 1.4;
        } else {
            multiplicatorPoluareVarsta = 1.5;
        }
        return motor.calculeazaPoluarea() * multiplicatorPoluareVarsta;
    }

    public Motor getMotor() {
        return motor;
    }
}
