public class Cliente extends Thread {
    private final String prodotto;
    private final Magazzino magazzino;

    public Cliente(String prodotto, Magazzino magazzino) {
        this.prodotto = prodotto;
        this.magazzino = magazzino;
    }

    @Override
    public void run() {
        synchronized (magazzino) {
            do {
                try {
                    magazzino.wait();
                } catch (InterruptedException e) {
                    System.err.println(getName() + " Interrotto");
                }
            } while (magazzino.scrivibile);
            do {
                if (magazzino.isEmpty())
                    try {
                        magazzino.wait();
                    } catch (InterruptedException e) {
                        System.err.println(getName() + " Interrotto");
                    }
            } while (!magazzino.scrivibile);
            //noinspection DataFlowIssue
            magazzino.scrivibile = true;
            try {
                magazzino.wait();
            } catch (InterruptedException e) {
                System.err.println(getName() + " Interrotto");
            }
        }
    }
}
