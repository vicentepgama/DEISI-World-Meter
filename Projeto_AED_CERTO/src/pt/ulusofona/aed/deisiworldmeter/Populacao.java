package pt.ulusofona.aed.deisiworldmeter;

public class Populacao {
    int idPop;
    int ano;
    int popMasculina;
    int popFemenina;
    double densidade;

    public Populacao(int id, int ano, int popMasculina, int popFemenina, double densidade) {
        this.idPop = id;
        this.ano = ano;
        this.popMasculina = popMasculina;
        this.popFemenina = popFemenina;
        this.densidade = densidade;
    }

    @Override
    public String toString() {
        return "pt.ulusofona.aed.deisiworldmeter.Populacao{" +
                "id=" + idPop +
                ", ano=" + ano +
                ", popMasculina=" + popMasculina +
                ", popFemenina=" + popFemenina +
                ", densidade=" + densidade +
                '}';
    }


    public int getIdPop() {
        return idPop;
    }


}
