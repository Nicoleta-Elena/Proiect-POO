
public class Masina extends Vehicul {

    private Motor motor;

    public Masina(String marca, String model, int anFabricatie, TipDotare dotari, double pretInitial, Motor motor) {
        super(marca, model, anFabricatie, dotari, pretInitial);
        this.motor = motor;
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
}
