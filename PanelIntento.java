import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

public  class PanelIntento extends JPanel {
    private JButton[] botones;
    private int index;

    public PanelIntento() {
        setLayout(new GridLayout(1, 5));
        botones = new JButton[5];
        for (int i = 0; i < 5; i++) {
            botones[i] = new JButton();
            botones[i].setFont(new Font("Arial", Font.BOLD, 40));
            botones[i].setEnabled(false);
            botones[i].setOpaque(true);
            botones[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            add(botones[i]);
        }
        index = 0;
    }

    public void agregarLetra(char letra, EstadoLetra estado) {
        botones[index].setText(String.valueOf(letra));
        switch (estado) {
            case CORRECTA:
                botones[index].setBackground(Color.GREEN);
                break;
            case PRESENTE:
                botones[index].setBackground(Color.YELLOW);
                break;
            case AUSENTE:
                botones[index].setBackground(Color.RED); 
                break;
        }
        index++;
    }

    public void reiniciar() {
        for (JButton boton : botones) {
            boton.setText("");
            boton.setBackground(null);
        }
        index = 0;
    }
}

