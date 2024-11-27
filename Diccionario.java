import java.io.*;
import java.util.*;

    public class Diccionario {
        private Set<String> palabras;

        public Diccionario(String archivo) {
            palabras = new HashSet<>();
            cargarPalabras(archivo);
        }

        private void cargarPalabras(String archivo) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String palabra;
                while ((palabra = br.readLine()) != null) {
                    palabras.add(palabra.trim().toLowerCase());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public boolean esPalabraValida(String palabra) {
            return palabras.contains(palabra.toLowerCase());
        }
        
        public Set<String> getPalabras() {
            return palabras;
        }
    }
