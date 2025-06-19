import java.awt.print.*;

public class Imprimanta {

    public void printeaza(ContractAsigurare contractAsigurare) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Contract Asigurare #" + contractAsigurare.getNumarUnic());
        job.setPrintable(new Printable() {
            @Override
            public int print(java.awt.Graphics g, PageFormat pf, int pageIndex) {
                if (pageIndex > 0)
                    return NO_SUCH_PAGE;
                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
                g2d.translate(pf.getImageableX(), pf.getImageableY());
                int y = 20;
                int lineHeight = 16;
                g2d.drawString("CONTRACT DE ASIGURARE #" + contractAsigurare.getNumarUnic(), 10, y);
                y += lineHeight;
                g2d.drawString("Status: " + contractAsigurare.getStatus(), 10, y);
                y += lineHeight * 2;
                g2d.drawString("--- Client ---", 10, y);
                y += lineHeight;
                Client client = contractAsigurare.getClient();
                g2d.drawString("Nume: " + client.getNume(), 10, y);
                y += lineHeight;
                g2d.drawString("Prenume: " + client.getPrenume(), 10, y);
                y += lineHeight;
                g2d.drawString("CNP: " + client.getCnp(), 10, y);
                y += lineHeight * 2;
                g2d.drawString("--- Masina ---", 10, y);
                y += lineHeight;
                for (String line : contractAsigurare.getMasina().getCaracteristici().split("\\n")) {
                    g2d.drawString(line, 10, y);
                    y += lineHeight;
                }
                y += lineHeight;
                g2d.drawString("--- Riscuri asigurate ---", 10, y);
                y += lineHeight;
                for (TipRisc risc : contractAsigurare.getRiscuri()) {
                    g2d.drawString("- " + risc, 10, y);
                    y += lineHeight;
                }
                y += lineHeight;
                g2d.drawString("Frecventa plata: " + contractAsigurare.getFrecventaPlata(), 10, y);
                y += lineHeight;
                g2d.drawString("Suma asigurata: " + contractAsigurare.calculeazaSumaAsigurata(), 10, y);
                y += lineHeight;
                g2d.drawString("Rata finala: " + contractAsigurare.getRataFinala(), 10, y);
                y += lineHeight;
                return PAGE_EXISTS;
            }
        });
        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
}
