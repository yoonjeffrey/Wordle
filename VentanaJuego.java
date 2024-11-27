import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaJuego extends JFrame {
    private Juego juego;
    private Tablero tablero;
    private JTextField entradaPalabra;
    private JButton botonEnviar;
    private JLabel mensaje;

    public VentanaJuego(Diccionario diccionario) {
        juego = new Juego(diccionario);
        tablero = new Tablero();
        entradaPalabra = new JTextField(10);
        mensaje = new JLabel("Adivina la palabra de 5 letras", SwingConstants.CENTER);

        setLayout(new BorderLayout());
        add(tablero, BorderLayout.CENTER);
        add(mensaje, BorderLayout.NORTH);
        
        JPanel panelEntrada = new JPanel();
        panelEntrada.add(entradaPalabra);
        
        botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palabra = entradaPalabra.getText().trim();
                if (palabra.length() != 5) {
                    JOptionPane.showMessageDialog(null, "La palabra debe tener 5 letras.");
                    return;
                }
                EstadoLetra[] estados = juego.hacerIntento(palabra);
                if (estados == null) {
                    JOptionPane.showMessageDialog(null, "Palabra no válida");
                } else {
                    int intentosActuales = juego.getIntentos().size();
                    tablero.mostrarIntento(intentosActuales - 1, palabra, estados);
                    if (juego.juegoTerminado()) {
                        mensaje.setText("¡Juego terminado! La palabra era: " + juego.getPalabraSecreta());
                        reiniciarJuego();
                    } else {
                        mensaje.setText("Sigue intentando...");
                    }
                }
                entradaPalabra.setText("");
            }
        });

        panelEntrada.add(botonEnviar);
        add(panelEntrada, BorderLayout.SOUTH);
        
        setTitle("Juego Wordle");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void reiniciarJuego() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Quieres jugar de nuevo?", "Reiniciar", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            juego = new Juego(new Diccionario("words.txt"));
            tablero.reiniciar();
            mensaje.setText("Adivina la palabra de 5 letras");
        }
    }
}

