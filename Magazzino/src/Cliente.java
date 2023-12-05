public class Cliente extends Thread {
    private final String prodotto;
    private final Magazzino magazzino;

    public Cliente(String prodotto, Magazzino magazzino) {
        this.prodotto = prodotto;
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        magazzino.rimuoviProdotto(prodotto);
    }
}
