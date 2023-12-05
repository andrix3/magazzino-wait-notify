public class Cliente extends Thread {
    private final String prodotto;
    private final int quantita;
    private final Magazzino magazzino;

    public Cliente(String prodotto, int quantita, Magazzino magazzino) {
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        try {
            magazzino.rimuoviProdotto(prodotto, quantita);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
