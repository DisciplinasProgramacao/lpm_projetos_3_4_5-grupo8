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
        this.quantidadeEpisodios = validarQuantidadeEpisodios(quantidadeEpisodios);;
        this.audiencia = 0;
    }

    public int validarQuantidadeEpisodios(int quantidadeEpisodios2){

        if(this.quantidadeEpisodios < 3){
           // System.out.println("Quantidade inválida de episódios");
            return 3;
        }
        return this.quantidadeEpisodios;

    }

    public void registrarAudiencia(){
        
        this.audiencia += 1;

    }

    public int getAudiencia(){
        return this.audiencia;
    }
}
