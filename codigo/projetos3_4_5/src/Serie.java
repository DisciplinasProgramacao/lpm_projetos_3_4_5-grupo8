public class Serie extends Catalogo {

    private int quantidadeEpisodios;

    // Construtor
    public Serie(int id, String nome, String dataLancamento,String genero, String idioma, int quantidadeEpisodios) {
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
    public int getEpisodios() {
        return this.quantidadeEpisodios;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + this.quantidadeEpisodios + " episódios";
    }

    // @Override
    // public boolean equals(Object o){
    //     Serie outraSerie = (Serie)o;
    //     return this.nome.equals(outraSerie.nome);
    // }
}
