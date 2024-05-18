package pt.ulusofona.aed.deisiworldmeter;



public class Pais {

    int id;
    String alfa2;
    String alfa3;
    String nome;
    int linhaMaiorQue700;

    public Pais(int id, String alfa2, String alfa3, String nome) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
    }

    public Pais() {

    }

    public  int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public  String getAlfa2() {
        return alfa2;
    }

    public  String getAlfa3() {
        return alfa3;


    }

    @Override
    public String toString() {
        if(id> 700){
            return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase() + " | " + linhaMaiorQue700;
        }
        return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase();
    }




}
