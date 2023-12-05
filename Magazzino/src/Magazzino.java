import java.util.Objects;

public class Magazzino {
    public enum Scorta_oggetto {
        FINITO, PRESENTE, NON_PRESENTE, PIENO
    }

    ;
    private final String[] scorta;
    public boolean scrivibile = true;

    public Magazzino() {
        this.scorta = new String[10];
    }

    public void aggiungiProdotto(String prodotto) throws InterruptedException {
        synchronized (scorta) {
            if (findEmpty() == -1) {
                System.err.println("Il Magazzino è pieno");
                return;
            }
            scorta[findEmpty()] = prodotto;
            if (findEmpty() == -1) {
                System.err.println("Il magazzino adesso è pieno");
                try {
                    scorta.wait();
                }catch (InterruptedException ignore) {

                }
            }
        }
    }

    public void rimuoviProdotto(String prodotto) {
        synchronized (scorta) {
            if (isEmpty()) {
                System.err.println("I prodotti sono finiti");
                try {
                    scorta.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
            if (contains(prodotto) == -1) {
                System.err.println("Prodotto non esistente");
                return;
            }
            scorta[contains(prodotto)] = null;
            if (contains(prodotto) == -1) {
                System.err.println("Scorta di " + prodotto + " finita");
                return;
            }
        }
        return;
    }

    public boolean isEmpty() {
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