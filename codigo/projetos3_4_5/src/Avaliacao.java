public class Avaliacao {
    private String loginCliente;
    private int nota;
    private String comentario;

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

    public int getNota(){
        return this.nota;
    }

    public void adicionarComentario(String comentario){
        this.comentario = comentario;
    }

    @Override
    public String toString(){
        if(comentario.isEmpty())
            return "Nota: " + this.nota;

        return "Nota: " + this.nota + "\nComentario: " + this.comentario;
    }
}
