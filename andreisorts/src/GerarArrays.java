import java.util.Random;

public class GerarArrays {
    public int[] gerar(int tamanho, int seed) {
        int codigo;
        Random random = new Random(seed);

        int[] array = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            codigo = random.nextInt();
            if (codigo>0) {
                array[i] = codigo;
            } else {
                array[i] = -codigo;
            }
        }return array;
    }

}
