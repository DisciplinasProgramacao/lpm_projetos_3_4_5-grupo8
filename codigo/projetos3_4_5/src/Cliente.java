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
        Lista<Serie> listaGenero = new Lista<Serie>();
        series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);
        for(int i = 0; i < series.length; i++){
            if(series[i].getGenero().equals(genero)){
                listaGenero.add(series[i]);
            }
        }
        return listaGenero;
    }

    public Lista<Serie> filtrarPorIdioma(String idioma){
        Lista<Serie> listaIdioma = new Lista<Serie>();
        series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);
        for(int i = 0; i < series.length; i++){
            if(series[i].getIdioma().equals(idioma)){
                listaIdioma.add(series[i]);
            }
        }
        return listaIdioma;
    }

    public Lista<Serie> filtrarPorQtdEpisodios(int qtdEpisodios){
        Lista<Serie> listaqtdEpisodios = new Lista<Serie>();
        series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);
        for(int i = 0; i < series.length; i++){
            if(series[i].getEpisodios() == qtdEpisodios){
                listaqtdEpisodios.add(series[i]);
            }
        }
        return listaqtdEpisodios;
    }

    public void registrarAudiencia(Serie serie){
        series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);
        for(int i = 0; i < series.length; i++){
            if(!(serie == series[i])){
                serie.registrarAudiencia();
                listaJaVistas.add(serie);
            }
        }
    }



}
