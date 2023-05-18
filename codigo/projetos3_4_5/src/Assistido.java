import java.time.LocalDate;

public class Assistido {
    public LocalDate data;
    private Catalogo catalogo;

    public Assistido(Catalogo catalogo, LocalDate data) {
        this.catalogo = catalogo;
        this.data = data;
    }

    public LocalDate getData() {
        return this.data;
    }

    public Catalogo getCatalogo() {
        return this.catalogo;
    }
}
