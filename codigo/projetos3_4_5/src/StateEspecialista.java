public class StateEspecialista extends State {

    // Métodos obrigatórios herdados da classe State, implementando restrições para um cliente ser especialista. 
     
    //Retorna o estado do cliente como especialista
    @Override
    public State tornarEspecialista() {
        return this;
    }

    /**
    * Cliente especialista pode tornar-se profissional. 
    * Muda o seu estado para profissional
    */
    @Override
    public State tornarProfissional() {
        return new StateProfissional();
    }

    //Cliente especialista pode tornar-se standart(normal)
    @Override
    public State tornarStandart() {
        return new StateStandart();
    }

    /**
    * Método que verifica se o cliente pode comentar. 
    * Como o cliente é especialista, ele pode adicionar comentário em uma avaliação
    * @return true
    */
    @Override
    public boolean podeComentar() {
        return true;
    }

    /**
    * Método que verifica se o cliente pode assistir uma midia de lançamento.
    * Como o cliente é especialista, ele não pode assistir.
    * @return false
    */
    @Override
    public boolean podeAssistirLancamento() {
        return false;
    }
}
