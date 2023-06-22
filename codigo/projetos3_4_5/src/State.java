
public abstract class State {
   
    /*Métodos abstratos que determinam: 
    *    - implementações do estado do cliente nas classes filhas (normal, especialista ou profissional)
    *    - restrições para poder comentar ou assistir uma midia de lançamento.
    */
    public abstract State tornarEspecialista();
    public abstract State tornarProfissional();
    public abstract State tornarStandart();
    public abstract boolean podeComentar();
    public abstract boolean podeAssistirLancamento();
}
