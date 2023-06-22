public class StateStandart extends State{
    // Métodos obrigatórios herdados da classe State, implementando restrições para um cliente normal.

    //Cliente normal pode tornar-se especialista
    @Override
    public State tornarEspecialista() {
        return new StateEspecialista();
    }

    /*
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

    /*
    * Método que verifica se o cliente pode comentar. 
    * Como o cliente é normal, ele não pode adicionar comentário em uma avaliação
    * @return false
    */
    @Override
    public boolean podeComentar() {
        return false;
    }

    /*
    * Método que verifica se o cliente pode assistir uma midia de lançamento.
    * Como o cliente é normal, ele não pode assistir.
    * @return false
    */
    @Override
    public boolean podeAssistirLancamento() {
        return false;
    }

    
}
