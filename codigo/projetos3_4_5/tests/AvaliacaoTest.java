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
        assertEquals("\nPor: login\nNota: 5\n--------------------", avaliacaoSemComentario.toString());
    }

    @Test
    public void deveRetornarStringComNotaEComentarioCasoPossuaComentario(){
        assertEquals("\nPor: login\nNota: 5\nComentario: comentario\n--------------------", avaliacaoComComentario.toString());
    }

    @Test
    public void deveAdicionarComentarioAUmaAvaliacao(){
        avaliacaoSemComentario.adicionarComentario("comentario adicionado");
        assertEquals("\nPor: login\nNota: 5\nComentario: comentario adicionado\n--------------------", avaliacaoSemComentario.toString());
    }

}
