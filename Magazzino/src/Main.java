public class Main {
    public static void main(String[] args) throws InterruptedException {
        Magazzino magazzino = new Magazzino();
        new Magazziniere("pane", magazzino).start();

        new Magazziniere("pane", magazzino).start();

        new Cliente("pane", magazzino).start();
        new Cliente("pane", magazzino).start();
        new Cliente("pane", magazzino).start();

        Thread.sleep(5000);


        new Magazziniere("pane", magazzino).start();
    }
}