import java.util.Date;


public class Assistido {
    public Date data;
    private Catalogo catalogo;

    public Assistido(Catalogo catalogo, Date data) {
        this.catalogo = catalogo;
        this.data = data;
    }
    public Date getData(){
        return this.data;
    }
    public Catalogo getCatalogo(){
        return this.catalogo;
    }
}
