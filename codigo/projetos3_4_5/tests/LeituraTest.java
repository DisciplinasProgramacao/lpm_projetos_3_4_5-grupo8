import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.*;

public class LeituraTest {

    @Test
    void testarSave() throws IOException {
        LinkedList<Serie> a = new LinkedList<>();
        Serie serie = new Serie("12", "mulher maravilha", "12-2-2022");
        a.add(serie);
        Leitura.gravar("testeSave", a);
    }
}
