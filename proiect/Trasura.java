
public class Trasura extends Vehicul {

    public Trasura(String marca, String model, int anFabricatie, TipDotare dotari, double pretInitial) {
        super(marca, model, anFabricatie, dotari, pretInitial);
        this.indiceRisc = 200;
    }

    @Override
    public double calculeazaRisc() {
        return indiceRisc;
    }
}
