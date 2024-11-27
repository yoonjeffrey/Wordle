import javax.swing.*;
import java.util.*;
import java.util.List;

    public class Juego {
        private String palabraSecreta;
        private List<Intento> intentos;
        private Diccionario diccionario;
        private int intentosRestantes;

        public Juego(Diccionario diccionario) {
            this.diccionario = diccionario;
            this.intentos = new ArrayList<>();
            this.intentosRestantes = 6; 
            iniciarJuego();
        }

        private void iniciarJuego() {     //mezclas las palabrs
            List<String> palabrasValidas = new ArrayList<>(diccionario.getPalabras());
            Collections.shuffle(palabrasValidas);
            this.palabraSecreta = palabrasValidas.get(0);
        }

        public EstadoLetra[] hacerIntento(String palabra) {
            //if (!diccionario.esPalabraValida(palabra)) {
               // return null; // palabr no esta
           //}

            EstadoLetra[] estados = new EstadoLetra[5];
            boolean[] letraUsada = new boolean[5];

            // Verifica letras correctas
            for (int i = 0; i < 5; i++) {
                if (palabra.charAt(i) == palabraSecreta.charAt(i)) {
                    estados[i] = EstadoLetra.CORRECTA;
                    letraUsada[i] = true;
                } else {
                    estados[i] = EstadoLetra.AUSENTE;
                }
            }

            // Verifica letras presentes
            for (int i = 0; i < 5; i++) {
                if (estados[i] == EstadoLetra.AUSENTE) {
                    for (int j = 0; j < 5; j++) {
                        if (!letraUsada[j] && palabra.charAt(i) == palabraSecreta.charAt(j)) {
                            estados[i] = EstadoLetra.PRESENTE;
                            letraUsada[j] = true;
                            break;
                        }
                    }
                }
            }

            intentos.add(new Intento(palabra, estados));
            intentosRestantes--;

            // ganado o perdido
            if (palabra.equals(palabraSecreta)) {
                mostrarMensaje("¡Muchas felicidades! Eres el ganador. ¿Quieres volver a jugar?");
                return estados;
            } else if (intentosRestantes == 0) {
                mostrarMensaje("Gracias por jugar. La palabra era: " + palabraSecreta + ". ¿Quieres jugar de nuevo?");
                return estados;
            }

            return estados;
        }

        public boolean juegoTerminado() {
            return intentosRestantes == 0 || intentos.stream().anyMatch(intent -> intent.getPalabra().equals(palabraSecreta));
        }

        public List<Intento> getIntentos() {
            return intentos;
        }

        public String getPalabraSecreta() {
            return palabraSecreta;
        }

        private void mostrarMensaje(String mensaje) {
            JOptionPane.showMessageDialog(null, mensaje);
        }

        public void reiniciar() {
            intentos.clear();
            intentosRestantes = 6;
            iniciarJuego();
        }
    }

