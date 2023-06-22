public enum EnumGeneros {
    //Valores dos generos
    ACAO("Acao"),
    ANIME("Anime"),
    AVENTURA("Aventura"),
    COMEDIA("Comedia"),
    DOCUMENTARIO("Documentario"),
    DRAMA("Drama"),
    POLICIAL("Policial"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense");

    //Atributos
    private String descricao;

    //Construtor
    EnumGeneros(String descricao){
        this.descricao = descricao;
    }

    /**
     * Método que retorna a descrição de um genero
     * @return descricao (string)
     */
    public String getDescricao() {
        return descricao;
    }
}