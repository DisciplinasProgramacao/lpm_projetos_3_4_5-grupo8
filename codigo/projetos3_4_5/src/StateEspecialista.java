
public class StateEspecialista extends State {
    @Override
    public State tornarEspecialista() {
        return this;
    }

    @Override
    public State tornarProfissional() {
        return new StateProfissional();
    }

    @Override
    public State tornarStandart() {
        return new StateStandart();
    }

    @Override
    public boolean podeComentar() {
        return true;
    }

    @Override
    public boolean podeAssistirLancamento() {
        return false;
    }
}
