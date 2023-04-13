import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class ClienteTest {
    Cliente cliente;

    @BeforeEach
    public void prepare(){
        cliente = new Cliente("");
    }
}
