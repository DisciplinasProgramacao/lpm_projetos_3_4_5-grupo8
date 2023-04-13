public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private Lista<Serie> listaParaVer;
    private Lista<Serie> listaJaVistas;
    private Serie[] series;
    private Serie serie;

     //Construtor
     public Cliente(String nomeDeUsuario, String senha){
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
    }

    public Serie getSerie(){
        return this.serie;
    }

    public void adicionarNaLista(Serie serie){
        listaParaVer.add(serie);
    }

    public void retirarDaLista(String nomeSerie){
        
        listaParaVer.remove();
    }

    public Lista<Serie> filtrarPorGenero(String genero){
        series = new Serie[listaParaVer.size()];
    }

    public Lista<Serie> filtrarPorIdioma(String idioma){
        
    }

    public Lista<Serie> filtrarPorQtdEpisodios(int qtdEpisodios){
        
    }

    public void registrarAudiencia(Serie serie){

    }



}
