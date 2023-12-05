public class Magazziniere extends Thread {
    private final String prodotto;
    private final Magazzino magazzino;

    public Magazziniere(String prodotto, Magazzino magazzino) {
        this.prodotto = prodotto;
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        try {
            magazzino.aggiungiProdotto(prodotto);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
