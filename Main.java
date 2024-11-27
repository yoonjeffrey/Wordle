import javax.swing.SwingUtilities;

public class Main{
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Diccionario diccionario = new Diccionario("words.txt");
            VentanaJuego ventana = new VentanaJuego(diccionario);
            ventana.setVisible(true);
        });
    }
}