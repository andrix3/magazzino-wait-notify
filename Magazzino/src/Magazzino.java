public class Magazzino {
    public enum scortaOggetto{
        FINITO,
        PRESENTE,
        NON_PRESENTE,
        PIENO
    };
    private String[] scorta;
    public boolean scrivibile = true;

    public Magazzino() {
        this.scorta = new String[10];
    }

    public scortaOggetto aggiungiProdotto(String prodotto) throws InterruptedException {
        synchronized (scorta) {
            if(findEmpty() == -1){
                System.err.println("Il Magazzino è pieno");
                return scortaOggetto.PIENO;
            }
            scorta[findEmpty()] = prodotto;
            if(findEmpty() == -1){
                System.err.println("Il magazzino adesso è pieno");
                return scortaOggetto.PIENO;
            }
        }
        return scortaOggetto.PRESENTE;
    }

    public scortaOggetto rimuoviProdotto(String prodotto){
        synchronized (scorta) {
            if(isEmpty()){
                System.err.println("I prodotti sono finiti");
                return scortaOggetto.NON_PRESENTE;
            }
            if(contains(prodotto) == -1){
                System.err.println("Prodotto non esistente");
                return scortaOggetto.NON_PRESENTE;
            }
            scorta[contains(prodotto)] = null;
            if(contains(prodotto) == -1){
                System.err.println("Scorta di " + prodotto + " finita");
                return scortaOggetto.FINITO;
            }
        }
        return scortaOggetto.PRESENTE;
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

    private int findEmpty(){
        int i = 0;
        for(String s: scorta){
            if(s == null) return i;
            i++;
        }
        return -1;
    }
}