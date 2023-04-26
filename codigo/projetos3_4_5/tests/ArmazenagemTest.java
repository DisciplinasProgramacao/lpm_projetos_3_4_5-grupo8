import java.io.IOException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ArmazenagemTest {

    @Test
    void testarSave() throws IOException {
        LinkedList<Serie> a = new LinkedList<>();
        LinkedList<Serie> b = new LinkedList<>();
        Serie serie = new Serie(12, "mulher maravilha", "12-2-2022");
        String nomeArq = "testeSave";
        a.add(serie);
        Armazenagem.gravar(nomeArq, a);
        b = Armazenagem.lerSerie(nomeArq);
        assertEquals(a.toString(), b.toString());
    }
}
