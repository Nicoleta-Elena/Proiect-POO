public class Motor {

    private int putere;
    private String euro;
    private TipCombustibil combustibil;

    public Motor(int putere, String euro, TipCombustibil combustibil) {
        this.putere = putere;
        this.euro = euro;
        this.combustibil = combustibil;
    }

    public TipCombustibil getCombustibil() {
        return combustibil;
    }

    public int getPutere() {
        return putere;
    }

    public String getEuro() {
        return euro;
    }

    public double getMultiplicatorRisc() {
        if (this.putere > 200) {
            return 1.5;
        }
        if (this.putere > 150) {
            return 1.25;
        }
        return 1;
    }

    public double calculeazaPoluarea() {
        double indicePoluare = 0;

        if (combustibil == TipCombustibil.Diesel) {
            indicePoluare = 120;
        } else if (combustibil == TipCombustibil.Petrol) {
            indicePoluare = 100;
        }

        switch (euro) {
            case "Euro 1":
                indicePoluare = indicePoluare * 1.6;
                break;
            case "Euro 2":
                indicePoluare = indicePoluare * 1.5;
                break;
            case "Euro 3":
                indicePoluare = indicePoluare * 1.4;
                break;
            case "Euro 4":
                indicePoluare = indicePoluare * 1.3;
                break;
            case "Euro 5":
                indicePoluare = indicePoluare * 1.2;
                break;
            case "Euro 6":
                indicePoluare = indicePoluare * 1.1;
                break;
            default:
                indicePoluare = indicePoluare * 1.6;
        }
        return indicePoluare;
    }

    public String getCaracteristici() {
        String caracteristici = new String();
        caracteristici = caracteristici + "Putere: " + putere;
        caracteristici = caracteristici + "\nEuro: " + euro;
        caracteristici = caracteristici + "\nCombustibil: " + combustibil;
        return caracteristici;
    }
}
