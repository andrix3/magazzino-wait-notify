import java.util.HashMap;

public class Magazzino {
    public String[] scorta;
    public boolean scrivibile = false;

    public Magazzino() {
        this.scorta = new String[10];
    }

    public void aggiungiProdotto(String prodotto, int quantita) throws InterruptedException {
        synchronized (scorta) {
            
        }
    }

    public void rimuoviProdotto(String prodotto, int quantita) throws InterruptedException {
        synchronized (scorta) {
            
        }
    }
}