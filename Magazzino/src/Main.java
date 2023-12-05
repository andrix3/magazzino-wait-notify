public class Main {
    public static void main(String[] args) {
        Magazzino magazzino = new Magazzino();
        new Magazziniere("pane", 5, magazzino).start();

        new Magazziniere("pane", 5, magazzino).start();

        new Cliente("pane", 4, magazzino).start();
        new Cliente("pane", 4, magazzino).start();
        new Cliente("pane", 4, magazzino).start();
    }
}