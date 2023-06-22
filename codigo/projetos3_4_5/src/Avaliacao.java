public class Avaliacao {

    //#region ATRIBUTOS
    private String loginCliente;
    private int nota;
    private String comentario;
    //#endregion

    //#region CONSTRUTOR

    /**
     * Construtor para clientes sem cadastro especial, recebe apenas login do cliente e nota dada a midia
     * 
     * @param loginCliente
     * @param nota
     * 
    */
    public Avaliacao(String loginCliente, int nota){
        init(loginCliente, nota, "");
    }

    /**
     * Construtor para clientes especialistas, recebe login do cliente, nota dada e comentário dados a midia
     * 
     * @param loginCliente
     * @param nota
     * @param comentario
     * 
     */
    public Avaliacao(String loginCliente, int nota, String comentario){
        init(loginCliente, nota, comentario);
    }

    /**
     * Método inicializador para criar os construtores
     * 
     * @param loginCliente
     * @param nota
     * @param comentario
     * 
     */
    public void init(String loginCliente, int nota, String comentario) {
        this.loginCliente = loginCliente;
        this.nota = nota;
        this.comentario = comentario;
    }
    //#endregion
    
    /**
     * Método que retorna a nota que o usuário deu para uma mídia
     * 
     * @return Nota da avaliação (int)
     */
    public int getNota(){
        return this.nota;
    }
    
    /**
     * Método que retorna o login do usuário que realizou a avaliação
     * 
     * @return Login do cliente (String)
     */
    public String getLoginCliente(){
        return this.loginCliente;
    }

    /**
     * Metodo que adiciona comentario em uma avaliacao
     * 
     * @param comentario (String)
     * 
     */
    public void adicionarComentario(String comentario){
        this.comentario = comentario;
    }

    /**
     * Método que formata os dados da avaliação, convertendo para uma string
     * 
     * @return String formatada (String)
     * 
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nPor: " + this.loginCliente + "\n");

        if(comentario.isEmpty()){
            stringBuilder.append("Nota: " + this.nota);
        } else {
            stringBuilder.append("Nota: " + this.nota + "\nComentario: " + this.comentario);
        }

        stringBuilder.append("\n--------------------");
        
        return stringBuilder.toString();
    }
}
