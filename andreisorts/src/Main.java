import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] tamanhos = {10000, 100000, 1000000};
        int[] tiposHash = {1, 2, 3};
        int[] tamanhosConjuntos = {100000, 500000, 2000000};

        GerarArrays gerador = new GerarArrays();
        Random random = new Random();

        for (int tamanho : tamanhos) {
            for (int tipoHash : tiposHash) {
                for (int tamanhoConjunto : tamanhosConjuntos) {
                    System.out.println("Tabela Hash - Tamanho: " + tamanho + ", Tipo Hash: " + tipoHash + ", Conjunto: " + tamanhoConjunto);

                    // Criar tabela hash
                    TabelaHash tabela = new TabelaHash(tamanho, tipoHash);

                    // Gerar conjunto de dados
                    int[] codigos = gerador.gerar(tamanhoConjunto, 12345);
                    No[] nos = new No[tamanhoConjunto];
                    for (int i = 0; i < tamanhoConjunto; i++) {
                        if (i % (tamanhoConjunto/100) == 0) {
                            System.out.print(".");
                        }
                        int codigo = codigos[i] % 1000000000; // 9 dígitos
                        Registro registro = new Registro(codigo);
                        nos[i] = new No(codigo, registro);
                    }
                    System.out.print(".");
                    System.out.println();

                    // Inserção
                    long inicioInsercao = System.nanoTime();

                    for (int i = 0; i < tamanhoConjunto; i++) {
                        if (i % (tamanhoConjunto/100) == 0) {
                            System.out.print("-");
                        }
                        tabela.inserir(nos[i]);
                    }
                    System.out.print("!");
                    System.out.println();
                    long tempoInsercao = System.nanoTime() - inicioInsercao;
                    System.out.println("Tempo total de inserção: " + (tempoInsercao) + "ns");
                    System.out.println("Numero total de colisões: " + tabela.getColisoes());

                    // Busca
                    long inicioBusca = System.nanoTime();
                    int comparacoesTotal = 0;
                    int numBuscas = 5;

                    for (int i = 0; i < numBuscas; i++) {
                        int indiceBusca = random.nextInt(tamanhoConjunto);
                        int codigoBusca = nos[indiceBusca].getChave();
                        Registro resultado = tabela.buscar(codigoBusca);

                        comparacoesTotal += tabela.getComparacoes();
                    }
                    long tempoBusca = System.nanoTime() - inicioBusca;
                    System.out.println("Tempo total de busca: " + (tempoBusca) + "ns");
                    System.out.println("Número total de comparações: " + (comparacoesTotal));
                    System.out.println();
                }
            }
        }
    }
}