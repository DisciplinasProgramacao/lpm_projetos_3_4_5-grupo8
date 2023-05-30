import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;

public class AvaliacaoTest {
    Avaliacao avaliacaoComComentario;
    Avaliacao avaliacaoSemComentario;

    @BeforeEach
    public void prepare() throws Exception {
        avaliacaoComComentario = new Avaliacao("login", 5, "comentario");
        avaliacaoSemComentario = new Avaliacao("login", 5);
    }

    @Test
    public void deveRetornarStringApenasComNotaDaAvaliacaoCasoNaoPossuaComentario(){
        assertEquals("Nota: 5", avaliacaoSemComentario.toString());
    }

    @Test
    public void deveRetornarStringComNotaEComentarioCasoPossuaComentario(){
        assertEquals("Nota: 5\nComentario: comentario", avaliacaoComComentario.toString());
    }

    @Test
    public void deveAdicionarComentarioAUmaAvaliacao(){
        avaliacaoSemComentario.adicionarComentario("comentario adicionado");
        assertEquals("Nota: 5\nComentario: comentario adicionado", avaliacaoSemComentario.toString());
    }

}
