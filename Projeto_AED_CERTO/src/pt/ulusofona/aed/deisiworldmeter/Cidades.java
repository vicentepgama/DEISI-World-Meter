package pt.ulusofona.aed.deisiworldmeter;

 public class Cidades {
    String alfa2C;
    String cidade ;
    String regiao ;
    int populacao ;
    double latitude ;
    double longitude;

    public Cidades(String alfa2C, String cidade, String regiao, int populacao, double latitude, double longitude) {
        this.alfa2C = alfa2C;
        this.cidade = cidade;
        this.regiao = regiao;
        this.populacao = populacao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Cidades() {
    }

    public String getAlfa2C() {
        return alfa2C;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRegiao() {
        return regiao;
    }

    public double getPopulacao() {
        return (int) populacao;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

     @Override
     public String toString() {
         return cidade + " | " + alfa2C.toUpperCase() + " | " + regiao + " | " + populacao + " | (" + latitude + "," + longitude + ")";
     }


 }


