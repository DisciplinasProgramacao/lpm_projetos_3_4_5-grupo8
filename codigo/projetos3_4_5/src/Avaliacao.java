public class Avaliacao {
    //Atributos
    private String loginCliente;
    private int nota;
    private String comentario;

    //Construtor
    public Avaliacao(String loginCliente, int nota){
        init(loginCliente, nota, "");
    }

    public Avaliacao(String loginCliente, int nota, String comentario){
        init(loginCliente, nota, comentario);
    }

    public void init(String loginCliente, int nota, String comentario) {
        this.loginCliente = loginCliente;
        this.nota = nota;
        this.comentario = comentario;
    }

    //Metodo que retorna a nota
    public int getNota(){
        return this.nota;
    }

    /**
     * Metodo que adiciona comentario em uma avaliacao
     * @param comentario
     */
    public void adicionarComentario(String comentario){
        this.comentario = comentario;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\nPor: " + this.loginCliente + "\n");

        if(comentario.isEmpty()){
            stringBuilder.append("Nota:" + this.nota);
        } else {
            stringBuilder.append("Nota: " + this.nota + "\nComentario: " + this.comentario);
        }

        stringBuilder.append("\n--------------------");
        
        return stringBuilder.toString();
    }
}
