import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RelatorioTest {
    private PlataformaStreaming plataforma1;
    private Cliente cliente1, clienteLogado, cliente2;
    private Serie serie1, serie2;
    private Filme filme1, filme2, filme3;
    private Relatorio relatorio;

    @BeforeEach
    public void setUp() throws IllegalArgumentException, IOException {
        plataforma1 = new PlataformaStreaming("Netflix");
        cliente1 = new Cliente("Ana Souza", "aninha12", "123");
        clienteLogado = new Cliente("To logado", "logado", "login");
        cliente2 = new Cliente("Ana Beatriz", "ana.beatriz", "123");

        serie1 = new Serie("The Blacklist", "02/02/2017", "Suspense", "EN", 10);
        serie2 = new Serie("Black mirror", "05/05/2018", "Policial", "PT", 10);

        filme1 = new Filme("O Poderoso Chefão 1", "01/01/1972", "Drama", "EN", 120);
        filme2 = new Filme("O Poderoso Chefão 2", "01/01/1974", "Drama", "EN", 120);
        filme3 = new Filme("Minions", "30/06/2022", "Comedia", "EN", 180);
        relatorio = new Relatorio(plataforma1.getClientes(), plataforma1.getCatalogos());

        plataforma1.adicionarCliente(clienteLogado);
        plataforma1.adicionarCliente(cliente1);
        plataforma1.adicionarCliente(cliente2);

        plataforma1.adicionarCatalogo(serie1);
        plataforma1.adicionarCatalogo(serie2);
        plataforma1.adicionarCatalogo(filme1);
        plataforma1.adicionarCatalogo(filme2);
        plataforma1.adicionarCatalogo(filme3);
    }

        @Test
    public void deveRetornarRelatorioGeneroAudiencia(){

        plataforma1.login("logado", "login");

        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");
        plataforma1.assistirMidia("O Poderoso Chefão 2");

        plataforma1.adicionarAvaliacao(5, "", filme2);
        plataforma1.logoff();

        plataforma1.login("aninha12", "123");
        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");
        plataforma1.assistirMidia("O Poderoso Chefão 2");
        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 1");
        plataforma1.assistirMidia("O Poderoso Chefão 1");
        plataforma1.adicionarMidiaNaListaParaVerFuturamente("Minions");
        plataforma1.assistirMidia("Minions");
        plataforma1.logoff();

        plataforma1.login("ana.beatriz", "123");
        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 2");
        plataforma1.assistirMidia("O Poderoso Chefão 2");
        plataforma1.adicionarMidiaNaListaParaVerFuturamente("O Poderoso Chefão 1");
        plataforma1.assistirMidia("O Poderoso Chefão 1");
        plataforma1.adicionarMidiaNaListaParaVerFuturamente("Minions");
        plataforma1.assistirMidia("Minions");
        plataforma1.adicionarMidiaNaListaParaVerFuturamente("Black mirror");
        plataforma1.assistirMidia("Black mirror");
        plataforma1.logoff();   


        assertEquals("Relatório por gênero: Drama\n\n[1] O Poderoso Chefão 1\n[2] O Poderoso Chefão 2", relatorio.relatorioPorGeneroAudiencia("Drama"));
        

    }
}
