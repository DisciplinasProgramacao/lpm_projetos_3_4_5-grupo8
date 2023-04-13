public class Serie {
    
    static final String [] generos = new String[8];
    private String nome;
    private String genero;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;

    //Construtor
    public Serie(String nome, String genero, String idioma, int quantidadeEpisodios){
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.quantidadeEpisodios = quantidadeEpisodios;
        this.audiencia = 0;
    }

    public void registrarAudiencia(){
        
        this.audiencia = audiencia++;

    }

}
