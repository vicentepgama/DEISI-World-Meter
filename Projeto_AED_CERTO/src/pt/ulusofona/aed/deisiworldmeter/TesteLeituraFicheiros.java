package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.File;
import java.util.ArrayList;

public class TesteLeituraFicheiros {
    @Test
    public void TesteLeituraFicheirosSemErros() {

        if (Main.parseFiles(new File("."))) {

            ArrayList<String> arrayEsperado = new ArrayList<>();
            arrayEsperado.add("Burundi | 108 | BD | BRD");
            arrayEsperado.add("Andorra | 10 | AD | ADR");


            Assertions.assertEquals(arrayEsperado, Main.getObjects(TipoEntidade.PAIS));
        }
    }
    @Test
    public void TesteLeituraFicheirosInvalidos(){
        if (Main.parseFiles(new File("."))){

            ArrayList<String> arrayEsperado = new ArrayList<>();
            arrayEsperado.add("paises.csv | 1 | 1 | 2");
            arrayEsperado.add("cidades.csv | 6 | 4 | 8");
            arrayEsperado.add("populacao.csv | 0 | 10 | 2");
            Assertions.assertEquals(arrayEsperado, Main.getObjects(TipoEntidade.INPUT_INVALIDO));
        }
    }
}
