public class Cliente extends Thread {
    private final String prodotto;
    private final Magazzino magazzino;

    public Cliente(String prodotto, Magazzino magazzino) {
        this.prodotto = prodotto;
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        do{
            try {
                magazzino.wait();
            } catch (InterruptedException e) {
                System.err.println(getName() + " Interrotto");
            }
        }while(magazzino.scrivibile);
        do{
            if(magazzino.rimuoviProdotto(prodotto) == Magazzino.scortaOggetto.FINITO || magazzino.rimuoviProdotto(prodotto) == Magazzino.scortaOggetto.NON_PRESENTE)
                try{
                    magazzino.wait();
                }catch(InterruptedException e){
                    System.err.println(getName() + " Interrotto");
                }
        }while(!magazzino.scrivibile);
        magazzino.scrivibile = true;
        try {
            magazzino.wait();
        } catch (InterruptedException e) {
            System.err.println(getName() + " Interrotto");
        }
    }
}
