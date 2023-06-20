public enum EnumGeneros {
    ACAO("Acao"),
    ANIME("Anime"),
    AVENTURA("Aventura"),
    COMEDIA("Comedia"),
    DOCUMENTARIO("Documentario"),
    DRAMA("Drama"),
    POLICIAL("Policial"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense");

    private String descricao;

    EnumGeneros(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}