public class Main {
    public static void main(String[] args) {
        Magazzino magazzino = new Magazzino();
        new Magazziniere("pane", magazzino).start();

        new Magazziniere("pane", magazzino).start();

        new Cliente("pane", magazzino).start();
        new Cliente("pane", magazzino).start();
        new Cliente("pane", magazzino).start();
    }
}