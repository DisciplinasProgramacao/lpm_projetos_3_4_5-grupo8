import java.util.LinkedList;

public interface ICliente {
    public void registrarAudiencia(Catalogo midia);
    public String getSenha();
    public String getNomeUsuario();
    public String getLogin();
    public LinkedList<Assistido> getListaJaVistas();
    public LinkedList<Avaliacao> getListaDeAvaliacoes();
    public int quantidadeDeAvaliacoes();
    public Serie[] allElements(Serie[] array);
    public void adicionarComentarioAvaliacaoExistente(Avaliacao avaliacao, String comentario);
    public Avaliacao adicionarAvaliacao(int nota, String comentario, Catalogo catalogo);
    public String listarMidiasAssistidas();
    public String listarMidiasParaSeremAssistidas();
    public Catalogo buscarMidiaNaListaParaVer(String nomeMidia);
    public Catalogo escolherCatalogo(int posicao);
    public void adicionarNaLista(Catalogo midia);
    public void retirarDaLista(String nomeMidia);

    public void tornarStandart();
    public void tornarEspecialista();
    public void tornarProfissional();

}
