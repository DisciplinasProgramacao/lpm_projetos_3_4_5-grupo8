public interface ICliente {

    public void registrarAudiencia(Catalogo midia);

    public Avaliacao adicionarAvaliacao(int nota, String comentario, Catalogo catalogo);

    public String listarMidiasAssistidas();

    public String listarMidiasParaSeremAssistidas();

    public Catalogo buscarMidiaNaListaParaVer(String nomeMidia);

    public String getSenha();

    public String getNomeUsuario();

    public String getLogin();

    public Catalogo escolherCatalogo(int posicao);

    public void adicionarNaLista(Catalogo midia);

    public void retirarDaLista(String nomeMidia);
}
