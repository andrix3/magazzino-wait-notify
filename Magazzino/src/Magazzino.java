import java.util.HashMap;

public class Magazzino {
    public final HashMap<String, Integer> prodotti;
    public boolean scrivibile = false;

    public Magazzino() {
        this.prodotti = new HashMap<>();
    }

    public void aggiungiProdotto(String prodotto, int quantita) throws InterruptedException {
        synchronized (prodotti) {
            try {
                while (scrivibile)
                    prodotti.wait();

            } finally {
                if (prodotti.containsKey(prodotto)) {
                    prodotti.replace(prodotto, prodotti.get(prodotto), prodotti.get(prodotto) + quantita);
                } else {
                    prodotti.put(prodotto, quantita);
                }

                System.out.println("aggiunti " + quantita + " " + prodotto + " al magazzino.");
            }

            scrivibile = true;
            prodotti.notifyAll();
        }
    }

    public void rimuoviProdotto(String prodotto, int quantita) throws InterruptedException {
        synchronized (prodotti) {
            try {
                while (!scrivibile)
                    prodotti.wait();
            } finally {
                if (prodotti.containsKey(prodotto)) {
                    if (prodotti.get(prodotto) >= quantita) {
                        prodotti.replace(prodotto, prodotti.get(prodotto), prodotti.get(prodotto) - quantita);

                    } else
                        System.out.println("non ci sono abbastanza " + prodotto);
                } else {
                    prodotti.put(prodotto, quantita);
                }
                System.out.println("venduti " + quantita + " " + prodotto);
            }

            scrivibile = false;
            prodotti.notifyAll();
        }
    }
}