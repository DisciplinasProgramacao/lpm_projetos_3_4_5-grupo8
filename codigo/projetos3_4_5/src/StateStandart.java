import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StateStandart extends State{

    @Override
    public State tornarEspecialista(Cliente cliente) {
        LocalDate hoje = LocalDate.now();
        if(cliente.getListaJaVistas().stream().filter(x -> x.getData().until(hoje, ChronoUnit.DAYS) <= 30).count() >= 5)
            return new StateEspecialista();
        else 
            return this;
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
