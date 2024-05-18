package pt.ulusofona.aed.deisiworldmeter;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteCidades {

    @Test
    public void testarConversaoParaString() {

        Cidades cidade = new Cidades("PT", "Lisboa", "Lisboa", 505526, 38.736946, -9.142685);


        String stringEsperada = "Lisboa | PT | Lisboa | 505526 | (38.736946,-9.142685)";


        Assertions.assertEquals(stringEsperada, cidade.toString());
    }


}
