/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Frontend.TablaErrores;
import Frontend.TablaTokens;

/// Clase token
public class Token {

    private String palabra;
    private int noToken;
    private TablaTokens tablaTokens;
    private TablaErrores tablaErrores;
    private int columna;
    private int fila;
    private PalabrasReservadas reservadas;
    private boolean esLiteral;
    private boolean esComentario;
    private boolean esPalabra;
    private int noError;

    public Token(TablaTokens tablaTokens, TablaErrores tablaErrores, PalabrasReservadas reservadas) {
        this.reservadas = reservadas;
        this.tablaTokens = tablaTokens;
        this.tablaErrores = tablaErrores;
        fila = 1;
    }

    public void reconcerLexema(StringBuilder lexema, ReporteTokens nuevoReporteTokens, int inicioColumna) {
        palabra = lexema.toString();
        if (esPalabra) {
            if (reservadas.comprobarLexema(String.valueOf(palabra))) {
                noToken++;
                tablaTokens.agregarToken(noToken, palabra, reservadas.obtenerTipo(palabra), inicioColumna, fila);
                nuevoReporteTokens.agregarDato(noToken, palabra, reservadas.obtenerTipo(palabra), fila, inicioColumna);
            } else {
                noToken++;
                tablaTokens.agregarToken(noToken, palabra, "IDENTIFICADOR", inicioColumna, fila);
                nuevoReporteTokens.agregarDato(noToken, palabra, "IDENTIFICADOR", fila, inicioColumna);
            }
        } else if (esLiteral) {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, "Literal", inicioColumna, fila);
            nuevoReporteTokens.agregarDato(noToken, palabra, "Literal", fila, inicioColumna);
        } else if (esComentario) {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, "Comentario", inicioColumna, fila);
            nuevoReporteTokens.agregarDato(noToken, palabra, "Comentario", fila, inicioColumna);
        } else if (esDelimitador(lexema.charAt(0))) {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, "Delimitador", inicioColumna, fila);
            nuevoReporteTokens.agregarDato(noToken, palabra, "Delimitador", fila, inicioColumna);
        } else if (esOperador(lexema.charAt(0))) {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, "Operador", inicioColumna, fila);
            nuevoReporteTokens.agregarDato(noToken, palabra, "Operador", fila, inicioColumna);
        } else {
            noToken++;
            tablaTokens.agregarToken(noToken, palabra, "error", inicioColumna, fila);
            nuevoReporteTokens.agregarDato(noToken, palabra, "error", fila, inicioColumna);

        }

    }

    public void reportarError(ReporteErrores reporteErrores, String lexema, String tipoError, int fila, int columna) {
        noError++;
        System.out.println(noError + " " + lexema + " "+ tipoError + " Fila: " + fila + " Columna: " + columna);
        tablaErrores.agregarError(noError, lexema, tipoError, columna, fila);
        reporteErrores.agregarError(noError ,lexema, tipoError, fila, columna);
    }

    public void analizarArchivo(String texto, ReporteTokens nuevoReporteTokens, ReporteErrores reporteErrores) {

        for (int i = 0; i < texto.length(); i++) {

            char caracterActual = texto.charAt(i);
            columna++;

            // q0 -> q1, esto nos permite reconcer la estructura de las palabras como palabras reservadas, conectores , comandos IA o identificadores
            if (esLetra(caracterActual) || caracterActual == '_' || caracterActual == '@') {
                esPalabra = true;
                int colInicio = columna;
                StringBuilder lexema = new StringBuilder();
                lexema.append(texto.charAt(i));
                i++;
                columna++;
                while (i < texto.length() && (esLetra(texto.charAt(i)) || esDigito(texto.charAt(i)) || texto.charAt(i) == '_')) {
                    lexema.append(texto.charAt(i));
                    i++;
                    columna++;
                }
                reconcerLexema(lexema, nuevoReporteTokens, colInicio);
                esPalabra = false;
            } //q0 -> q2 esto nos permite detectar los delimitadores y los operadores
            else if (i < texto.length()
                    && (esDelimitador(texto.charAt(i)) || esOperador(caracterActual))) {
                int colInicio = columna;
                StringBuilder lexema = new StringBuilder();
                lexema.append(texto.charAt(i));
                reconcerLexema(lexema, nuevoReporteTokens, colInicio);
            } //q0 -> q3 esto nos permite reconer la estructura de las cadenas de texto
            else if (i < texto.length() && caracterActual == '"') {
                esLiteral = true;
                int colInicio = columna;
                StringBuilder lexema = new StringBuilder();
                i++;
                while (i < texto.length() && texto.charAt(i) != '"' && texto.charAt(i) != '\n') {
                    lexema.append(texto.charAt(i));
                    i++;
                    columna++;
                }
                if (i < texto.length() && texto.charAt(i) == '"') {
                    reconcerLexema(lexema, nuevoReporteTokens, colInicio + 1);
                } else {
                    reportarError(reporteErrores ,lexema.toString(), "no se cerro la cadena", fila, colInicio);
                }

                esLiteral = false;
            } //q0 -> q5 esto nos permite reconer la estructura de los comentarios
            else if (i < texto.length() - 1 && texto.charAt(i) == '/' && texto.charAt(i + 1) == '*') {
                esComentario = true;
                int colInicio = columna;
                StringBuilder lexema = new StringBuilder();
                i += 2;
                while (i < texto.length() - 1 && !(texto.charAt(i) == '*' && texto.charAt(i + 1) == '/')) {
                    if (texto.charAt(i) == '\n') {
                        fila++;
                    }
                    lexema.append(texto.charAt(i));
                    i++;
                }

                reconcerLexema(lexema, nuevoReporteTokens, colInicio);
                esComentario = false;
            } //q5 -> q9 esto nos permite reconer la estructura de los comentarios
            else if (i < texto.length() - 1 && texto.charAt(i) == '/' && texto.charAt(i + 1) == '/') {
                esComentario = true;
                int colInicio = columna;
                StringBuilder lexema = new StringBuilder();
                i += 2;
                while (i < texto.length() - 1 && texto.charAt(i) != '\n') {
                    lexema.append(texto.charAt(i));
                    i++;
                }
                reconcerLexema(lexema, nuevoReporteTokens, colInicio);
                esComentario = false;
            } //q0 -> q10 esto nos permite detectar el conector "->"
            else if (i < texto.length() - 1 && caracterActual == '-' && texto.charAt(i + 1) == '>') {
                esPalabra = true;
                int colInicio = columna;
                StringBuilder lexema = new StringBuilder();
                lexema.append(texto.charAt(i));
                lexema.append(texto.charAt(i + 1));
                i++;
                reconcerLexema(lexema, nuevoReporteTokens, colInicio);
                esPalabra = false;

            } //q0 -> q12 nos permite detectar los numeros enteros y decimales
            else if (i < texto.length() && esDigito(caracterActual)) {
                esLiteral = true;
                int colInicio = columna;
                StringBuilder lexema = new StringBuilder();

                while (i < texto.length() && esDigito(texto.charAt(i))) {
                    lexema.append(texto.charAt(i));
                    i++;
                    columna++;
                }

                if (i < texto.length() && texto.charAt(i) == '.') {
                    lexema.append('.');
                    i++;
                    columna++;
                    while (i < texto.length() && esDigito(texto.charAt(i))) {
                        lexema.append(texto.charAt(i));
                        i++;
                        columna++;
                    }

                    reconcerLexema(lexema, nuevoReporteTokens, colInicio);
                } else {

                    reconcerLexema(lexema, nuevoReporteTokens, colInicio);
                }
                esLiteral = false;
            }

            // reporte de errores 
            if (i < texto.length() && texto.charAt(i) != '\n'
                    && texto.charAt(i) != '\s'
                    && texto.charAt(i) != '>'
                    && texto.charAt(i) != '"'
                    && texto.charAt(i) != '/'
                    && texto.charAt(i) != '*'
                    && !esOperador(texto.charAt(i))
                    && !esLetra(texto.charAt(i))
                    && !esDelimitador(texto.charAt(i))
                    && !esDigito(texto.charAt(i))) {
                reportarError(reporteErrores, String.valueOf(texto.charAt(i)), "Caracter no reconocido", fila, columna);

            }

            // Nos permite contabilizar las lines y reciniciar las columnas cuando estas acaben
            if (i < texto.length() && texto.charAt(i) == '\n') {
                columna = 0;
                fila++;
            }

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
        char[] delimitadores = {'(', ')', '{', '}'};
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
