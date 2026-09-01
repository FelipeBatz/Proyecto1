package Proyecto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author felip
 */

import Backend.Token;


public class main {

    public static void main(String[] args) {
        Token token = new Token();
        token.crearTablaPalabrasReservadas();
        
        token.analizarTexto("@modelo (AGENTE) asdf");

       

    }
}
