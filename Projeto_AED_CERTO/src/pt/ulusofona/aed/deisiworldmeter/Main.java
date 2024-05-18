package pt.ulusofona.aed.deisiworldmeter;


import javax.xml.transform.Result;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


import static pt.ulusofona.aed.deisiworldmeter.TipoEntidade.*;

public class Main {
    public static ArrayList<Pais> paises = new ArrayList<>();
    public static ArrayList<Populacao> Populacao = new ArrayList<>();
    public static ArrayList<Cidades> Cidades = new ArrayList<>();


    public static ArrayList<Object> getObjects(TipoEntidade tipo) {
        ArrayList<Object> result = new ArrayList<>();
        // escreve da forma como quisermos
        switch (tipo) {
            case PAIS:
                for (Pais pais : paises) {
                    result.add(pais.toString());
                }
                break;
            case CIDADE:
                for (Cidades cidade : Cidades) {
                    result.add(cidade.toString());
                }
                break;
            case INPUT_INVALIDO:

                result.add("paises.csv | " + linhasOkPaises + " | " + linhasNaoOkPaises + " | " + primeiraLinhaIncorretaPaises);

                result.add("cidades.csv | " + linhasOkCidades + " | " + linhasNaoOkCidades + " | " + primeiraLinhaIncorretaCidades);

                result.add("populacao.csv | " + linhasOkPopulacao + " | " + linhasNaoOkPopulacao + " | " + primeiraLinhaIncorretaPopulacao);

                break;

        }

        return result;
    }

    static int linhasOkPaises;
    static int linhasNaoOkPaises;
    static int primeiraLinhaIncorretaPaises;
    static int linhasOkPopulacao;
    static int linhasNaoOkPopulacao;
    static int primeiraLinhaIncorretaPopulacao;
    static int linhasOkCidades;
    static int linhasNaoOkCidades;
    static int primeiraLinhaIncorretaCidades;
    static HashMap<String, Integer> paisesComCidade = new HashMap<>();


    public static boolean parseFiles(File folder) {
        paises.clear();
        Populacao.clear();
        Cidades.clear();
        linhasOkPaises = 0;
        linhasNaoOkPaises = 0;
        primeiraLinhaIncorretaPaises = -1;
        linhasOkPopulacao = 0;
        linhasNaoOkPopulacao = 0;
        primeiraLinhaIncorretaPopulacao = -1;
        linhasOkCidades = 0;
        linhasNaoOkCidades = 0;
        primeiraLinhaIncorretaCidades = -1;



        boolean leituraCorreta = false;
        boolean paisesok = parsePaises(new File(folder, "paises.csv"));
        boolean populacaoOk = parsePopulacao(new File(folder, "populacao.csv"));
        boolean cidadesOk = parseCidades(new File(folder, "cidades.csv"));

        if (paisesok && populacaoOk && cidadesOk) {
            leituraCorreta = true;
        }
        Iterator<Map.Entry<String, Integer>> iterator = paisesComCidade.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String chave = entry.getKey();
            Integer valor = entry.getValue();
            if (valor == 0){
                iterator.remove() ;
                linhasOkPaises--;
                linhasNaoOkPaises++;
                if (primeiraLinhaIncorretaPaises == -1) {
                    primeiraLinhaIncorretaPaises = linhasOkPaises + linhasNaoOkPaises ;
                }
            }
        }


        contarIndicadorEstatisticos();
        return leituraCorreta;
    }


    private static boolean parsePaises(File file) {


        //salta primeira linha
        try (Scanner scanner = new Scanner(file)) {
            boolean primeiraLinha = true;
            //corre todas as linhas
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                //divide a linha nas partes que queremos
                String[] partes = linha.split(",");
                if (partes.length < 4) {
                    linhasNaoOkPaises++;
                    //verifica se a linha é incorreta
                    if (primeiraLinhaIncorretaPaises == -1) {
                        primeiraLinhaIncorretaPaises = linhasOkPaises + linhasNaoOkPaises + 1;
                    }
                    continue;
                }


                int id = Integer.parseInt(partes[0]);

                boolean idDuplicado = false;
                //verifica se o id esta duplicado no ficheiro
                for (Pais pais : paises) {
                    if (pais.getId() == id) {
                        idDuplicado = true;
                        break;
                    }
                }


                String alfa2 = partes[1];
                String alfa3 = partes[2];
                String nome = partes[3];


                if (!(idDuplicado) && alfa2.length() == 2 && alfa3.length() == 3 && !nome.isEmpty()) {
                    Pais pais = new Pais(id, alfa2, alfa3, nome);
                    paisesComCidade.put(alfa2, 0);
                    paises.add(pais);
                    linhasOkPaises++;

                } else {
                    linhasNaoOkPaises++;
                    if (primeiraLinhaIncorretaPaises == -1) {
                        primeiraLinhaIncorretaPaises = linhasOkPaises + linhasNaoOkPaises + 1;
                    }
                }
            }

            INPUT_INVALIDO.add("paises.csv | " + linhasOkPaises + " | " + linhasNaoOkPaises + " | " + primeiraLinhaIncorretaPaises);
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de países não encontrado: " + e.getMessage());
            return false;
        }

    }


    private static boolean parsePopulacao(File file) {
        //salta a primeira linha
        try (Scanner scanner = new Scanner(file)) {
            boolean primeiraLinha = true;
            //corre todas as linhas
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] partes = linha.split(",");
                    if (partes.length < 5) {
                        linhasNaoOkPopulacao++;
                        if (primeiraLinhaIncorretaPopulacao == -1) {
                            primeiraLinhaIncorretaPopulacao = linhasOkPopulacao + linhasNaoOkPopulacao + 1;
                        }
                    } else {

                        int idPop = Integer.parseInt(partes[0]);

                        boolean idValido = false;
                        for (Pais pais : paises) {
                            if (pais.getId() == idPop) {
                                idValido = true;
                                break;
                            }
                        }

                        if (!isNumber(partes[1]) || !isNumber(partes[2]) || !isNumber(partes[3]) || !isNumber(partes[0]) || !isNumber(partes[4])) {
                            linhasNaoOkPopulacao++;
                            if (primeiraLinhaIncorretaPopulacao == -1) {
                                primeiraLinhaIncorretaPopulacao = linhasOkPopulacao + linhasNaoOkPopulacao + 1;
                            }
                            continue;
                        }

                        int ano = Integer.parseInt(partes[1]);
                        int popMasculina = Integer.parseInt(partes[2]);
                        int popFemenina = Integer.parseInt(partes[3]);
                        double densidade = Double.parseDouble(partes[4]);


                        if ((idValido)) {
                            Populacao populacao = new Populacao(idPop, ano, popMasculina, popFemenina, densidade);
                            Populacao.add(populacao);
                            linhasOkPopulacao++;
                        } else {
                            linhasNaoOkPopulacao++;
                            if (primeiraLinhaIncorretaPopulacao == -1) {
                                primeiraLinhaIncorretaPopulacao = linhasOkPopulacao + linhasNaoOkPopulacao + 1;
                            }
                        }

                    }
                }

            }

            INPUT_INVALIDO.add("populacao.csv | " + linhasOkPopulacao + " | " + linhasNaoOkPopulacao + " | " + primeiraLinhaIncorretaPopulacao);
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de população não encontrado: " + e.getMessage());
            return false;
        }
    }


    private static boolean parseCidades(File file) {
        try (Scanner scanner = new Scanner(file)) {
            boolean primeiraLinha = true;

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (primeiraLinha) {
                    primeiraLinha = false;
                } else {
                    String[] partes = linha.split(",");
                    if (partes.length < 6) {
                        linhasNaoOkCidades++;
                        if (primeiraLinhaIncorretaCidades == -1) {
                            primeiraLinhaIncorretaCidades = linhasOkCidades + linhasNaoOkCidades + 1;
                        }
                    } else {

                        String alfa2C = partes[0];
                        String cidade = partes[1];
                        String regiao = partes[2];

                        boolean paisExiste = false;
                        for (Pais pais : paises) {
                            if (pais.getAlfa2().equals(alfa2C)) {
                                paisExiste = true;
                                break;
                            }
                        }
                        String populacaoStr = partes[3];
                        double latitude = Double.parseDouble(partes[4]);
                        double longitude = Double.parseDouble(partes[5]);


                        if (!populacaoStr.isEmpty()) {

                            try {
                                double populacaoDouble = Double.parseDouble(populacaoStr);

                                int populacao = (int) populacaoDouble;
                                if ((populacao != 0 && paisExiste && alfa2C.length() == 2)) {

                                    Cidades cidadeAtual = new Cidades(alfa2C, cidade, regiao, populacao, latitude, longitude);


                                    if (paisesComCidade.containsKey(alfa2C)) {
                                        paisesComCidade.put(alfa2C, paisesComCidade.get(alfa2C) + 1);
                                    }



                                    Cidades.add(cidadeAtual);
                                    linhasOkCidades++;

                                } else {
                                    linhasNaoOkCidades++;
                                    if (primeiraLinhaIncorretaCidades == -1) {
                                        primeiraLinhaIncorretaCidades = linhasOkCidades + linhasNaoOkCidades + 1;
                                    }
                                }
                            } catch (NumberFormatException e) {
                                linhasNaoOkCidades++;
                                if (primeiraLinhaIncorretaCidades == -1) {
                                    primeiraLinhaIncorretaCidades = linhasOkCidades + linhasNaoOkCidades + 1;
                                }

                            }
                        } else {
                            linhasNaoOkCidades++;
                            if (primeiraLinhaIncorretaCidades == -1) {
                                primeiraLinhaIncorretaCidades = linhasOkCidades + linhasNaoOkCidades + 1;
                            }

                        }


                    }
                }
            }

            INPUT_INVALIDO.add("cidades.csv | " + linhasOkCidades + " | " + linhasNaoOkCidades + " | " + primeiraLinhaIncorretaCidades);
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo de cidades não encontrado: " + e.getMessage());
            return false;
        }
    }

    public static void contarIndicadorEstatisticos() {


        for (Pais pais : paises) {
            int count = 0;
            if (pais.getId() > 700) {
                for (Populacao populacao : Populacao) {
                    if (populacao.getIdPop() == pais.getId()) {
                        count++;
                    }
                }
                pais.linhaMaiorQue700 = count;
            }
        }
    }


    public static boolean isNumber(String number) {

        try {
            Double.parseDouble(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


    public static Result execute(String command) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Bem vindo ao DEISI World Meter");


        boolean parseOk = parseFiles(new File("."));
        if (!parseOk) {
            System.out.println("Erro na leitura dos ficheiros");
            return;
        }


        ArrayList<Object> testePais = getObjects(TipoEntidade.PAIS);
        for (Object pais : testePais) {
            System.out.println(pais);
        }
        ArrayList<Object> testeCidade = getObjects(TipoEntidade.CIDADE);
        for (Object cidade : testeCidade) {
            System.out.println(cidade);
        }
        ArrayList<Object> testeIinvalido = getObjects(TipoEntidade.INPUT_INVALIDO);
        for (Object invalido : testeIinvalido) {
            System.out.println(invalido);
        }
    }
}