/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Frontend.JFrameTablaTokens;

/**
 *
 * @author felip
 */
public class Token {

    private StringBuilder lexema;
    private String palabra;
    private int noToken;
    private JFrameTablaTokens tablaTokens;
    private int columna;
    private int fila;
    private PalabrasReservadas reservadas;
    private boolean esCadena;
    private boolean esComentario;
    private boolean esDelimi;
    private boolean esOpera;
    private int inicioColumna;

    public Token(JFrameTablaTokens tablaTokens, PalabrasReservadas reservadas) {
        this.reservadas = reservadas;
        this.tablaTokens = tablaTokens;
        fila++;
        columna++;
    }

    public void reconcerLexema(StringBuilder lexema) {
        palabra = lexema.toString();
        if (reservadas.comprobarLexema(String.valueOf(palabra))) {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, reservadas.obtenerTipo(palabra), inicioColumna, fila);
        }

        if (esCadena) {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, "Literal", inicioColumna, fila);
        }

        if (esComentario) {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, "Comentario", inicioColumna, fila);
        }

        if (esDelimi) {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, "Delimitador", inicioColumna, fila);
        }

        if (esOpera) {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, "Operador", inicioColumna, fila);
        }

    }

    public void analizarDirectiva(String texto) {

        for (int i = 0; i < texto.length(); i++) {

            if (texto.charAt(i) == '\n') {
                fila++;
            }

            if (texto.charAt(i) == '@') {
                inicioColumna = columna;
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
                inicioColumna = columna;
                lexema = new StringBuilder();
                while (i < texto.length() && esLetra(texto.charAt(i))) {
                    lexema.append(texto.charAt(i));
                    i++;
                }
                reconcerLexema(lexema);
            }

            if (i < texto.length() && texto.charAt(i) == '"') {
                inicioColumna = columna;
                esCadena = true;
                lexema = new StringBuilder();
                lexema.append(texto.charAt(i));
                reconcerLexema(lexema);
                lexema.setLength(0);
                i++;
                while (i < texto.length() && texto.charAt(i) != '"') {
                    lexema.append(texto.charAt(i));
                    i++;
                }
                reconcerLexema(lexema);
                esCadena = false;
            }

            if (i < texto.length() - 1 && texto.charAt(i) == '/' && texto.charAt(i + 1) == '*') {
                inicioColumna = columna;
                esComentario = true;
                lexema = new StringBuilder();
                i += 2;
                while (i < texto.length() - 1 && !(texto.charAt(i) == '*' && texto.charAt(i + 1) == '/')) {
                    lexema.append(texto.charAt(i));
                    i++;
                }
                reconcerLexema(lexema);
                esComentario = false;
            }

            if (i < texto.length() - 1 && texto.charAt(i) == '/' && texto.charAt(i + 1) == '/') {
                inicioColumna = columna;
                esComentario = true;
                lexema = new StringBuilder();
                i += 2;
                while (i < texto.length() - 1 && texto.charAt(i) != '\n') {
                    lexema.append(texto.charAt(i));
                    i++;
                }
                reconcerLexema(lexema);
                esComentario = false;
            }

            if (i < texto.length() && esDelimitador(texto.charAt(i))) {
                inicioColumna = columna;
                esDelimi = true;
                lexema = new StringBuilder();
                lexema.append(texto.charAt(i));
                reconcerLexema(lexema);
                esDelimi = false;
            }

            if (i < texto.length() && esOperador(texto.charAt(i))) {
                inicioColumna = columna;
                esOpera = true;
                lexema = new StringBuilder();
                lexema.append(texto.charAt(i));
                reconcerLexema(lexema);
                esOpera = false;
            }
            columna++;
        }
    }

    public boolean esOperador(char caracter) {
        char[] delimitadores = {'+', '='};
        for (int i = 0; i < delimitadores.length; i++) {
            if (caracter == delimitadores[i]) {
                return true;
            }
        }
        return false;
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
