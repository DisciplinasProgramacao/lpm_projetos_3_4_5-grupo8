public class StateProfissional extends State {
    // Métodos obrigatórios herdados da classe State, implementando restrições para um cliente ser profissional.

    //Cliente profissional pode tornar-se especialista
    @Override
    public State tornarEspecialista() {
        return new StateEspecialista();
    }

    //Retorna o estado do cliente como profissional
    @Override
    public State tornarProfissional() {
        return this;
    }

     /**
    * Cliente profissional pode tornar-se normal. 
    * Muda o seu estado para standart (normal)
    */
    @Override
    public State tornarStandart() {
        return new StateStandart();
    }

    /**
    * Método que verifica se o cliente pode comentar. 
    * Como o cliente é profissional, ele pode adicionar comentário em uma avaliação
    * @return true
    */
    @Override
    public boolean podeComentar() {
        return true;
    }

    /**
    * Método que verifica se o cliente pode assistir uma midia de lançamento.
    * Como o cliente é profissional, ele pode assistir.
    * @return true
    */
    @Override
    public boolean podeAssistirLancamento() {
        return true;
    }
}
