public class Prodotti {
    private final String tipo;
    private int quantita;

    public Prodotti(String tipo, int quantita) {
        this.tipo = tipo;
        this.quantita = quantita;
    }

    public int getQuantita() {
        return quantita;
    }

    public void aumentaQuantita() {
        quantita++;
    }

    public void diminuisciQuantita() {
        quantita--;
    }

    public String getTipo() {
        return tipo;
    }
}
