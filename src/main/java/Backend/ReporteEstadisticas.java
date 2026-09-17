/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author felip
 */
public class ReporteEstadisticas {

    private String html;

    public void obtenerEstadisticas(JTable tablaTokens) {

        DefaultTableModel modelo = (DefaultTableModel) tablaTokens.getModel();

        int directiva = 0;
        int palabraReservada = 0;
        int comandosIA = 0;
        int conectores = 0;
        int identificadores = 0;
        int literal = 0;
        int operador = 0;
        int delimitador = 0;
        int comentario = 0;
        int funciones = 0;

        int totalTokens = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {

            Object valor = modelo.getValueAt(i, 2);

            if (valor == null) {
                continue;
            }

            String estado = valor.toString();
            
            switch (estado) {

                case "DIRECTIVA":
                    directiva++;
                    break;

                case "PALABRA_RESERVADA_DE_ESTRUCTURA":
                    palabraReservada++;
                    break;

                case "COMANDOS_DE_IA":
                    comandosIA++;
                    break;

                case "CONECTORES":
                    conectores++;
                    break;

                case "IDENTIFICADOR":
                    identificadores++;
                    break;

                case "Literal":
                    literal++;
                    break;

                case "Operador":
                    operador++;
                    break;

                case "Delimitador":
                    delimitador++;
                    break;

                case "Comentario":
                    comentario++;
                    break;

                case "FUNCIONES":
                    funciones++;
                    break;
            }

            // Contar cada token
            totalTokens++;
        }

        html = """
    <!DOCTYPE html>
    <html lang="es">

    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Estadísticas</title>

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <style>

            body {
                margin: 0;
                font-family: Arial, sans-serif;
                background: #E5FCF5;
            }

            .contenedor {
                width: 90%%;
                max-width: 1100px;
                margin: 30px auto;
            }

          
            .total {
                text-align: center;
                font-size: 24px;
                font-weight: bold;
                color: #000080;
                margin-bottom: 30px;
            }

            .estadisticas {
                display: flex;
                justify-content: center;
                gap: 30px;
                flex-wrap: wrap;
                color: white;
            }

            .tarjeta {
                background: #FEFFFE;
                border-radius: 15px;
                padding: 25px;
                border: 3px solid #000080;
                width: 1300px;
                box-shadow: 0 4px 12px rgba(0,0,0,0.15);
            }

            .tarjeta h2 {
                text-align: center;
                color: #333;
                margin-top: 0;
            }

            .grafico {
                position: relative;
                height: 700px;
            }
               
            h1 span {
                background: linear-gradient(90deg, #38bdf8, #6366f1);
                -webkit-background-clip: text;
                background-clip: text;
                color: transparent;
                text-align: center;           
                margin-bottom: 30px;
            }

        </style>

    </head>

    <body>

        <div class="contenedor">

           
               
            <h1>
            <span>Estadisticas</span> tokens
            </h1>

            <div class="total">
                Total de tokens: %d
            </div>

            <div class="estadisticas">

                <div class="tarjeta">

                    <h2>Tipos de Tokens</h2>

                    <div class="grafico">
                        <canvas id="graficoTokens"></canvas>
                    </div>

                </div>

            </div>

        </div>


        <script>

            const datosTokens = {

                labels: [
                    "DIRECTIVA",
                    "PALABRA_RESERVADA",
                    "COMANDOS_IA",
                    "CONECTORES",
                    "IDENTIFICADOR",
                    "LITERAL",
                    "OPERADOR",
                    "DELIMITADOR",
                    "COMENTARIO",
                    "FUNCIONES"
                ],

                datasets: [{
                    label: "Tokens",

                    data: [
                        %d,
                        %d,
                        %d,
                        %d,
                        %d,
                        %d,
                        %d,
                        %d,
                        %d,
                        %d
                    ],

                    borderWidth: 3
                }]
            };


            new Chart(
                document.getElementById("graficoTokens"),
                {
                    type: "pie",

                    data: datosTokens,

                    options: {

                        responsive: true,

                        maintainAspectRatio: false,

                        plugins: {

                            legend: {
                                position: "bottom"
                            }

                        }

                    }
                }
            );

        </script>

    </body>

    </html>
    """.formatted(
                totalTokens,
                directiva,
                palabraReservada,
                comandosIA,
                conectores,
                identificadores,
                literal,
                operador,
                delimitador,
                comentario,
                funciones
        );
    }

    public void crearHtmlEstadisticas() {
        File archivo = new File("estadisticas.html");

        try (FileWriter escritor = new FileWriter(archivo)) {
            escritor.write(html);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
