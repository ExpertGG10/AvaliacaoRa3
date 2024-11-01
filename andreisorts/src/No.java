public class No {
    private int chave;
    private Registro registro;
    private No proximo;

    public No(int chave, Registro registro) {
        this.chave = chave;
        this.registro = registro;
        this.proximo = null;
    }

    public int getChave() {
        return chave;
    }

    public Registro getRegistro() {
        return registro;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}