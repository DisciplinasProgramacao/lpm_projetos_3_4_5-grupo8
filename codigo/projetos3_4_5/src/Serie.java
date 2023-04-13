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
        this.quantidadeEpisodios = validarQuantidadeEpisodios();;
        this.audiencia = 0;
    }

    public int validarQuantidadeEpisodios(){

        if(this.quantidadeEpisodios < 3){
            System.out.println("Quantidade inválida de episódios");
        }
        return this.quantidadeEpisodios;

    }

    public void registrarAudiencia(){
        
        this.audiencia = audiencia++;

    }

    public int getAudiencia(){
        return this.audiencia;
    }
}
