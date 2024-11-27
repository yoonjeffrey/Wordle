public class Intento {
    private String palabra;
    private EstadoLetra[] estados;

    public Intento(String palabra, EstadoLetra[] estados) {
        this.palabra = palabra;
        this.estados = estados;
    }

    public String getPalabra() {
        return palabra;
    }

    public EstadoLetra[] getEstados() {
        return estados;
    }
}

