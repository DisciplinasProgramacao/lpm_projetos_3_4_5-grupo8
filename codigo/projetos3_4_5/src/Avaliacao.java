import java.util.HashMap;

public class Avaliacao {
    private int avaliacao = 0;
    private int num_avaliacoes = 0;
    private HashMap listaDeAvaliacoes = new HashMap();

    Avaliacao(int avaliacao, int num_avaliacoes) {
        init(avaliacao, num_avaliacoes);
    }

    Avaliacao() {
        init(0, 0);
    }

    public void init(int avaliacao, int num_avaliacoes) {
        this.avaliacao = avaliacao;
        this.num_avaliacoes = num_avaliacoes;
    }

    public void avaliar(int avaliacao, int hashCode) {
        listaDeAvaliacoes.put(hashCode, avaliacao);
    }

    public double mediaAvaliacao(){
        double media = 0;
        for (Object avaliacao : listaDeAvaliacoes.values()) {
            media += (int) avaliacao;
        }
        return media / listaDeAvaliacoes.size();
    }
}
