import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

public class StateStandart extends State{
    // Métodos obrigatórios herdados da classe State, implementando restrições para um cliente normal.

    //Cliente normal pode tornar-se especialista
    @Override
    public State tornarEspecialista(LinkedList<Assistido> listaJaVistas) {
        LocalDate hoje = LocalDate.now();
        if(listaJaVistas.stream().filter(x -> x.getData().until(hoje, ChronoUnit.DAYS) <= 30).count() >= 5)
            return new StateEspecialista();
        else return this;
    }

    /**
    * Cliente normal pode tornar-se profissional. 
    * Muda o seu estado para profissional
    */
    @Override
    public State tornarProfissional() {
        return new StateProfissional();
    }

    //Método que retorna o estado do cliente como standart(normal)
    @Override
    public State tornarStandart() {
        return this;
    }

    /**
    * Método que verifica se o cliente pode comentar. 
    * Como o cliente é normal, ele não pode adicionar comentário em uma avaliação
    * @return false
    */
    @Override
    public boolean podeComentar() {
        return false;
    }

    /**
    * Método que verifica se o cliente pode assistir uma midia de lançamento.
    * Como o cliente é normal, ele não pode assistir.
    * @return false
    */
    @Override
    public boolean podeAssistirLancamento() {
        return false;
    }

    
}
