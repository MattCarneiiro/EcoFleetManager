package org.example.ecofleet;

import org.example.ecofleet.view.MenuPrincipal;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando EcoFleet Manager...");


        MenuPrincipal menu = new MenuPrincipal();

        menu.executar();


        System.out.println("Sistema finalizado. Até logo!");
    }
}