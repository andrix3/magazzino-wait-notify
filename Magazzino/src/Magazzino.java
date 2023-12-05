import java.util.Objects;

public class Magazzino {
    private final String[] scorta;
    public Magazzino() {
        this.scorta = new String[10];
    }

    public void aggiungiProdotto(String prodotto) {
        synchronized (scorta) {
            try {
                while (findEmpty() == -1) {
                    System.err.println("Il Magazzino è pieno");
                    scorta.wait();
                }
                    scorta[findEmpty()] = prodotto;
                    System.out.println("aggiunto " + prodotto);
                    scorta.notifyAll();
            } catch (InterruptedException ignore) {}
        }
    }

    public void rimuoviProdotto(String prodotto) {
        synchronized (scorta) {
            try {
                while (isEmpty() || contains(prodotto) == -1) {
                    System.err.println("I prodotti sono finiti");
                    scorta.wait();
                }
                    scorta[contains(prodotto)] = null;
                    System.out.println("rimosso " + prodotto);
                    scorta.notifyAll();
            } catch (InterruptedException /*| ArrayIndexOutOfBoundsException*/ ignore) {}
        }
    }

    private boolean isEmpty() {
        for (String s : scorta) {
            if (s != null) return false;
        }
        return true;
    }

    private int contains(String nomeProdotto) {
        int i = 0;
        for (String s : scorta) {
            if (Objects.equals(s, nomeProdotto)) return i;
            i++;
        }
        return -1;
    }

    private int findEmpty() {
        int i = 0;
        for (String s : scorta) {
            if (s == null) return i;
            i++;
        }
        return -1;
    }
}