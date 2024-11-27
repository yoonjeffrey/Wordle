import javax.swing.JPanel;
import java.awt.*;


public class Tablero extends JPanel {
    private PanelIntento[] intentos;

    public Tablero() {
        setLayout(new GridLayout(6, 1));
        intentos = new PanelIntento[6];
        for (int i = 0; i < 6; i++) {
            intentos[i] = new PanelIntento();
            add(intentos[i]);
        }
    }

    public void mostrarIntento(int fila, String palabra, EstadoLetra[] estados) {
        for (int i = 0; i < 5; i++) {
            intentos[fila].agregarLetra(palabra.charAt(i), estados[i]);
        }
    }

    public void reiniciar() {
        for (PanelIntento intento : intentos) {
            intento.reiniciar();
        }
    }
}
