/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author felip
 */
public class Token {

    private Map<String, String> reservadas;
    private StringBuilder lexema;
    private String palabra;

    public Token() {
        crearTablaPalabrasReservadas();
    }

    public void crearTablaPalabrasReservadas() {
        reservadas = new HashMap<>();
        reservadas.put("@modelo", "DIRECTIVA");
        reservadas.put("@rol", "DIRECTIVA");
        reservadas.put("@Formato", "DIRECTIVA");

        reservadas.put("AGENTE", "PALABRA_RESERVADA_DE_ESTRUCTURA");
        reservadas.put("contexto", "PALABRA_RESERVADA_DE_ESTRUCTURA");
        reservadas.put("variable", "PALABRA_RESERVADA_DE_ESTRUCTURA");
        reservadas.put("EJECUTAR", "PALABRA_RESERVADA_DE_ESTRUCTURA");
        reservadas.put("EXPORTAR", "PALABRA_RESERVADA_DE_ESTRUCTURA");

        reservadas.put("PREGUNTAR", "COMANDOS_DE_IA");
        reservadas.put("GENERAR", "COMANDOS_DE_IA");
        reservadas.put("RESUMIR", "COMANDOS_DE_IA");
        reservadas.put("ANALIZAR", "COMANDOS_DE_IA");
        reservadas.put("TRADUCIR", "COMANDOS_DE_IA");
        reservadas.put("CLASIFICAR", "COMANDOS_DE_IA");
        reservadas.put("EXTRAER", "COMANDOS_DE_IA");

        reservadas.put("CARGAR", "FUNCIONES");

        reservadas.put("SOBRE", "CONECTORES");
        reservadas.put("DESDE", "CONECTORES");
        reservadas.put("EN", "CONECTORES");
        reservadas.put("COMO", "CONECTORES");
        reservadas.put("->", "CONECTORES");

    }

    public void reconcerLexema(StringBuilder lexema) {
        palabra = lexema.toString();
        if (reservadas.containsKey(String.valueOf(palabra))) {
            System.out.println("lexema: " + palabra + " / tipo: " + reservadas.get(palabra));
        }
    }

    public void analizarDirectiva(String texto) {
        for (int i = 0; i < texto.length(); i++) {

            if (texto.charAt(i) == '@') {
                lexema = new StringBuilder();
                lexema.append(texto.charAt(i));
                i++;
                while (i < texto.length() && esLetra(texto.charAt(i))) {
                    lexema.append(texto.charAt(i));
                    i++;
                }
                reconcerLexema(lexema);
            }
            if (i < texto.length() && esLetra(texto.charAt(i))) {

                lexema = new StringBuilder();
                while (i < texto.length() && esLetra(texto.charAt(i))) {
                    lexema.append(texto.charAt(i));
                    i++;
                }
                reconcerLexema(lexema);
            }
            if (i < texto.length() && texto.charAt(i) == '"') {
                lexema = new StringBuilder();
                i++;
                while (i < texto.length() && texto.charAt(i) != '"') {
                    lexema.append(texto.charAt(i));
                    i++;
                }
                System.out.println("\n" + lexema + "\n" + " / tipo: " + reservadas.get(palabra));
            }

        }
    }

    public void analizarTexto(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            //------q0 -> q2
            if (texto.charAt(i) == '@' || esLetra(texto.charAt(i))) {

                StringBuilder lexema = new StringBuilder();
                lexema.append(texto.charAt(i));
                i++;
                while (i < texto.length() && esLetra(texto.charAt(i))) {
                    lexema.append(texto.charAt(i));
                    i++;
                }
                String palabra = lexema.toString();
                if (reservadas.containsKey(palabra)) {
                    System.out.println(palabra + " / " + reservadas.get(palabra));
                } else {
                    System.out.println("Error lexico");
                }
            }
            //--------------------
            if (i < texto.length() && esDelimitador(texto.charAt(i))) {
                System.out.println(texto.charAt(i) + " DELIMITADOR");
            }
        }

    }

    public boolean esDelimitador(char caracter) {
        char[] delimitadores = {'(', ')', '{', '}', '"'};
        for (int i = 0; i < delimitadores.length; i++) {
            if (caracter == delimitadores[i]) {
                return true;
            }
        }
        return false;
    }

    private boolean esDigito(char caracter) {
        return Character.isDigit(caracter);
    }

    private boolean esLetra(char caracter) {
        return Character.isLetter(caracter);
    }

}
