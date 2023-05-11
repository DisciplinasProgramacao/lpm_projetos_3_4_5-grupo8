import java.util.HashMap;
import java.util.HashSet;

public class Avaliacao {
    private int avaliacao = 0;
    private int num_avaliacoes = 0;
    private  HashSet listaDeAvalidores = new HashSet();
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

    public void avaliar(int avaliacao, Cliente cliente) {
        if(listaDeAvalidores.add(cliente)){
            listaDeAvaliacoes.put(cliente, avaliacao);
        }else{
            System.out.println("Cliente já avaliou");
        }
    }

    public double mediaAvaliacao(){
        double media = 0;
        for (Object avaliacao : listaDeAvaliacoes.values()) {
            media += (int) avaliacao;
        }
        return media / listaDeAvaliacoes.size();
    }
}
