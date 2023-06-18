import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class StateProfissional extends State {

    @Override
    public State tornarEspecialista() {
        return new StateEspecialista();
    }

    @Override
    public State tornarProfissional() {
        return this;
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
        return true;
    }
}
