public enum EnumGeneros {
    ACAO("Ação"),
    ANIME("Anime"),
    AVENTURA("Aventura"),
    COMEDIA("Comédia"),
    DOCUMENTARIO("Documentário"),
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