/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author felip
 */
public class Archivo {

    public class LeerArchivo {

        private String textoLeido;

        public String leerTextoConScanner(String pathArchivo) {

            textoLeido = "";

            File miArchivo = new File(pathArchivo);
            try (InputStream inputStream = new FileInputStream(miArchivo)) {
                Scanner scanner = new Scanner(inputStream);
                String linea = scanner.nextLine();
                while (true) {

                    textoLeido = textoLeido + linea + "\n";
                    linea = scanner.nextLine();
                }
            } catch (NoSuchElementException | IOException e) {
            }

            return textoLeido;
        }

    }

}
