package pt.ulusofona.aed.deisiworldmeter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestePaises {

    @Test
    void TestePaisesIdMair700() {


        Pais paises = new Pais(704, "am", "amc", "Alo");
        Main.contarIndicadorEstatisticos();
        String stringEsperada = "Alo | 704 | AM | AMC | 0";
        Assertions.assertEquals(stringEsperada, paises.toString());

    }

    @Test
    void TestePaisesIdMenor700() {


        Pais paises = new Pais(14, "am", "amc", "Alo");


        String stringEsperada = "Alo | 14 | AM | AMC";
        Assertions.assertEquals(stringEsperada, paises.toString());
    }
}