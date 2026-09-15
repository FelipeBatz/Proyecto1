/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author felip
 */
public class ReporteTokens {

    private static final String TITULO_LOG = "REPORTE DE TOKENS";
    private static final String TITULO_COLUMNA1 = "No.";
    private static final String TITULO_COLUMNA2 = "Lexema";
    private static final String TITULO_COLUMNA3 = "Tipo";
    private static final String TITULO_COLUMNA4 = "Fila";
    private static final String TITULO_COLUMNA5 = "Columna";

    private FileWriter escribir;

    private String errores = "";

    public void crearReporte() {
        try {
            File pathArchivo = new File("ReporteTokens.html");

            escribir = new FileWriter(pathArchivo);
            escribir.write("<!DOCTYPE html>");
            escribir.write("<html lang=\"es\">");
            escribir.write("<style>");

            escribir.write("* {"
                    + "margin: 0;"
                    + "padding: 0;"
                    + "box-sizing: border-box;"
                    + "}");

            escribir.write("body {"
                    + "font-family: Arial, sans-serif;"
                    + "background-color: white;"
                    + "color: white;"
                    + "padding: 40px;"
                    + "}");

            escribir.write(".contenedor {"
                    + "width: 90%;"
                    + "max-width: 1000px;"
                    + "margin: auto;"
                    + "}");

            escribir.write("table {"
                    + "width: 100%;"
                    + "border-collapse: collapse;"
                    + "background-color: #1e293b;"
                    + "border: 3px solid #2563eb;"
                    + "border-radius: 10px;"
                    + "overflow: hidden;"
                    + "}");

            escribir.write("th {"
                    + "padding: 15px;"
                    + "text-align: center;"
                    + "font-size: 16px;"
                    + "border: 1px solid #60a5fa;"
                    + "}");

            escribir.write("td {"
                    + "padding: 14px;"
                    + "text-align: center;"
                    + "border: 1px solid #334155;"
                    + "}");

            escribir.write("thead {"
                    + "background-color: #2563eb;"
                    + "}");

            escribir.write("th {"
                    + "padding: 15px;"
                    + "text-align: center;"
                    + "font-size: 16px;"
                    + "}");

            escribir.write("td {"
                    + "padding: 14px;"
                    + "text-align: center;"
                    + "border-bottom: 1px solid #334155;"
                    + "}");

            escribir.write("tbody tr {"
                    + "transition: 0.3s;"
                    + "}");

            escribir.write("tbody tr:hover {"
                    + "background-color: #334155;"
                    + "}");

            escribir.write("tbody tr:last-child td {"
                    + "border-bottom: none;"
                    + "}");

            escribir.write("h1 {"
                    + "text-align: center;"
                    + "font-size: 38px;"
                    + "font-weight: 700;"
                    + "letter-spacing: 2px;"
                    + "margin-bottom: 35px;"
                    + "color: #ffffff;"
                    + "text-shadow: 0 0 5px #ee0b0b,"
                    + "0 0 15px #fa6060,"
                    + "0 0 25px #fa6060;"
                    + "}");

            escribir.write("h1 span {"
                    + "color: white;"
                    + "}");

            escribir.write("h1::after {"
                    + "content: \"\";"
                    + "display: block;"
                    + "width: 150px;"
                    + "height: 3px;"
                    + "margin: 12px auto 0;"
                    + "background: linear-gradient(90deg,"
                    + "transparent,"
                    + "#df763a,"
                    + "transparent);"
                    + "}");

            escribir.write("</style>");

            escribir.write("<head>");
            escribir.write("<meta charset=\"UTF-8\">");
            escribir.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            escribir.write("<title>" + TITULO_LOG + "</title>");
            escribir.write("</head>");

            escribir.write("<body>");
            escribir.write("<div class=\"contenedor\">");
            escribir.write("<h1>");
            escribir.write("<span>Listado de</span> Tokens");
            escribir.write("</h1>");

            escribir.write("<table>");
            escribir.write("<thead>");
            escribir.write("<tr>");

            escribir.write("<th>" + TITULO_COLUMNA1 + "</th>");
            escribir.write("<th>" + TITULO_COLUMNA2 + "</th>");
            escribir.write("<th>" + TITULO_COLUMNA3 + "</th>");
            escribir.write("<th>" + TITULO_COLUMNA4 + "</th>");
            escribir.write("<th>" + TITULO_COLUMNA5 + "</th>");
            escribir.write("</tr>");
            escribir.write("</thead>");

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void agregarDato(int numero, String lexema, String tipo, int fila, int columna) {

        try {
            escribir.write("<tr>");
            escribir.write("<td>" + numero + "</td>");
            escribir.write("<td>" + lexema + "</td>");
            escribir.write("<td>" + tipo + "</td>");
            escribir.write("<td>" + fila + "</td>");
            escribir.write("<td>" + columna + "</td>");
            escribir.write("</tr>");

        } catch (IOException e) {

            System.out.println("Error al agregar token");

        }
    }

    public void cerrarReporte() {

        try {
            escribir.write("</tbody>");
            escribir.write("</table>");
            escribir.write("</div>");
            escribir.write("</body>");
            escribir.write("</html>");
            escribir.close();
        } catch (IOException e) {
            System.out.println("error al cerrar html");

        }
    }
}
