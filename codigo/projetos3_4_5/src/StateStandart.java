public class StateStandart extends State{

    @Override
    public State tornarEspecialista() {
        return new StateEspecialista();
    }

    @Override
    public State tornarProfissional() {
        return new StateProfissional();
    }

    @Override
    public State tornarStandart() {
        return this;
    }

    @Override
    public boolean podeComentar() {
        return false;
    }

    @Override
    public boolean podeAssistirLancamento() {
        return false;
    }

    
}
