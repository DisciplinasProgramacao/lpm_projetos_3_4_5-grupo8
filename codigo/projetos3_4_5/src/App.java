import java.util.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        LinkedList<Serie> a = new LinkedList<>();
        Serie serie = new Serie("12", "mulher maravilha", "12-2-2022");
        a.add(serie);
        Leitura.gravar("testeSave", a);
    }
}
