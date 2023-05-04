import java.io.IOException;
import java.util.LinkedList;

public class Serie extends Catalogo {

    private int quantidadeEpisodios;

    // Construtor
    public Serie(int id, String nome, String dataLancamento, String genero, String idioma, int quantidadeEpisodios) {
        super(id, nome, dataLancamento, genero, idioma);
        this.quantidadeEpisodios = quantidadeEpisodios < 2 ? 0 : quantidadeEpisodios;
    }

    public Serie(int idSerie, String nome, String dataLancamento) {
        super(idSerie, nome, dataLancamento);
    }

    /**
     * Metodo que registra a quantidade de pessoas que assistiram a série
     */
    public void registrarAudiencia() {
        super.registrarAudiencia();
    }

    /**
     * @return quantidade de episodios da serie
     */
    public int getEpisodios() {
        return this.quantidadeEpisodios;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + this.quantidadeEpisodios + " episódios";
    }

    /// ahn????
    public static void salvar(String nomeArquivo, LinkedList<Catalogo> listaDeNovasSeries) {
        try {
            Armazenagem.gravar(nomeArquivo, listaDeNovasSeries);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // @Override
    // public boolean equals(Object o){
    // Serie outraSerie = (Serie)o;
    // return this.nome.equals(outraSerie.nome
    // }
}
