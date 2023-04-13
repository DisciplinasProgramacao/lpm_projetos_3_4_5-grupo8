public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private Lista<Serie> listaParaVer;
    private Lista<Serie> listaJaVistas;
    private Serie[] series;
    private Serie serie;

     //Construtor
     public Cliente(String nomeDeUsuario, String senha, Lista<Serie> listaParaVer, Lista<Serie> listaJaVistas){
        this.nomeDeUsuario = nomeDeUsuario;
        this.senha = senha;
        this.listaParaVer = listaParaVer;
        this.listaJaVistas = listaJaVistas;
    }

    public Serie getSerie(){
        return this.serie;
    }

    public void adicionarNaLista(Serie serie){
        serie.Lista.add(serie);
    }

    public void retirarDaLista(String nomeSerie){
        nomeSerie.Lista.remove();
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
