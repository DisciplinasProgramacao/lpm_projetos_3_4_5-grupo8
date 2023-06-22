import java.util.LinkedList;

public abstract class State {
   
    /**Métodos abstratos que determinam: 
    *    - implementações do estado do cliente nas classes filhas (normal, especialista ou profissional)
    *    - restrições para poder comentar ou assistir uma midia de lançamento.
    */
    /**
    * Cliente pode tornar-se especialista, mas apenas se entrar na condicao pre estabelecida. 
    * Muda o seu estado para especialista
    */
    public abstract State tornarEspecialista(LinkedList<Assistido> listaJaVistas);
    /**
    * Cliente pode tornar-se profissional. 
    * Muda o seu estado para profissional
    */
    public abstract State tornarProfissional();
    /**
    * Cliente  pode tornar-se normal. 
    * Muda o seu estado para standart (normal)
    */
    public abstract State tornarStandart();
    /**
    * Método que verifica se o cliente pode comentar. 
    * @return true/false
    */
    public abstract boolean podeComentar();
    /**
    * Método que verifica se o cliente pode assistir uma midia de lançamento.
    * @return true/false
    */
    public abstract boolean podeAssistirLancamento();
}
