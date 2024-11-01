public class TabelaHash {
    private No[] tabela;
    private int tamanho;
    private int tipo;
    private int colisoes;
    private int comparacoes;

    public TabelaHash(int tamanho, int tipoHash) {
        this.tamanho = tamanho;
        this.tipo = tipoHash;
        this.tabela = new No[tamanho];
    }

    public int hash(int codigo) {
        if (tipo==1) {
            return codigo % tamanho; // ultimos digitos
        }
        if (tipo==2) {
            return (codigo / (1000000000/tamanho));// primeiros digitos
        }
        if (tipo==3) { // cada digito é a unidade da soma das partes a cada 3 digitos
            int resposta = 0;
            int parte = 0;
            for (int i = 1; i < 10; i++) {
                parte += codigo % 10;
                codigo = codigo/10;
                if (i%3==0){
                    resposta+=(parte%10);
                    if  (i%9 == 0){
                        return resposta;
                    } else {
                        resposta*=10;
                    }

                }

            }


        }
        return codigo;
    }
    public void inserir(No novoNo) {
        int chave = novoNo.getChave();
        int indice = hash(chave);

        if (tabela[indice] == null) {
            tabela[indice] = novoNo;
        } else {
            colisoes++;
            inserirNoFinal(tabela[indice], novoNo);
        }
    }

    private void inserirNoFinal(No cabeca, No novoNo) {
        No atual = cabeca;

        // Percorre a lista até o último nó
        while (atual.getProximo() != null) {
            colisoes++;
            atual = atual.getProximo();
        }

        // Insere o novo nó no final
        atual.setProximo(novoNo);
    }

    public Registro buscar(int chave) {
        int indice = hash(chave);
        No atual = tabela[indice];
        comparacoes = 0;

        while (atual != null) {
            comparacoes++;
            if (atual.getChave() == chave) {
                return atual.getRegistro();
            }
            atual = atual.getProximo();
        }
        return null;
    }

    public int getColisoes() {
        return colisoes;
    }

    public int getComparacoes() {
        return comparacoes;
    }
}