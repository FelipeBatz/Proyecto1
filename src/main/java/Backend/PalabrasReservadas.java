/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author felip
 */
public class PalabrasReservadas {

    private Map<String, String> reservadas;
    

    public void crearTablaPalabrasReservadas() {
        reservadas = new HashMap<>();

        reservadas.put("@modelo", "DIRECTIVA");
        reservadas.put("@rol", "DIRECTIVA");
        reservadas.put("@formato", "DIRECTIVA");

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

    public boolean comprobarLexema(String lexema){
        return reservadas.containsKey(lexema);
    }
    
    public String obtenerTipo(String lexema){
        return reservadas.get(lexema);
    }

}
