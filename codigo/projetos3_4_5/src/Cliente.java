public class Cliente {
    private String nomeDeUsuario;
    private String senha;
    private Lista<Serie> listaParaVer;
    private Lista<Serie> listaJaVistas;
    private Serie[] series;

   
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