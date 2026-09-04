package Proyecto;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author felip
 */


import Backend.PalabrasReservadas;
import Frontend.InterfazPrincipal;



public class main {
    

    public static void main(String[] args) {
        
        PalabrasReservadas reservadas = new PalabrasReservadas();
        reservadas.crearTablaPalabrasReservadas();
        
        InterfazPrincipal interfaz = new InterfazPrincipal(reservadas);
        interfaz.setVisible(true);
        
    
    }
}
