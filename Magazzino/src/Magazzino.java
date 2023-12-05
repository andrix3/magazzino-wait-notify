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
            if(isEmpty()){
                System.err.println("I prodotti sono finiti");
                return;
            }
            if(contains(prodotto) == -1){
                System.err.println("Prodotto non esistente");
                return;
            }
            scorta[contains(prodotto)] = null;
        }
    }

    private boolean isEmpty(){
        for(String s: scorta){
            if(s != null) return false;
        }
        return true;
    }

    private int contains(String nomeProdotto){
        int i = 0;
        for(String s: scorta){
            if(s == nomeProdotto) return i;
            i++;
        }
        return -1;
    }
}