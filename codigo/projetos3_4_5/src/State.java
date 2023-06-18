
public abstract class State {
    public abstract State tornarEspecialista(Cliente cliente);
    public abstract State tornarProfissional();
    public abstract State tornarStandart();
    public abstract boolean podeComentar();
    public abstract boolean podeAssistirLancamento();
}
