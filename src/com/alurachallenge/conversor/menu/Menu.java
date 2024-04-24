package com.alurachallenge.conversor.menu;

import com.alurachallenge.conversor.calculo.Conversion;
import com.alurachallenge.conversor.constants.MessageConstants;

import javax.swing.*;


public class Menu {

    public void imprimirMenuPrincipal(){
        int option = 0;

        while (option != 2) {
            option = Integer.parseInt(JOptionPane.showInputDialog(null , MessageConstants.MENSAJE_MENU_PRINCIPAL));
            switch (option) {
                case 1:
                    menuMoneda();
                    break;
                case 2:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, MessageConstants.MENSAJE_OPCION_NO_VALIDA);
                    break;
            }
        }

    }

    private void menuMoneda(){
        String[] conversionOptions = {
                "COP - USD",
                "COP - EUR",
                "COP - GBP",
                "COP - JPY",
                "USD - COP",
                "USD - EUR",
                "USD - GBP",
                "USD - JPY"
        };

        String opcionSeleccionada = (String) JOptionPane.showInputDialog(
                null,
                MessageConstants.MENSAJE_PARA_PREGUNTAR,
                MessageConstants.TITULO_MENU,
                JOptionPane.QUESTION_MESSAGE,
                null,
                conversionOptions,
                conversionOptions[0]
        );

        double cantidadDinero =  preguntarPorCantidadDinero();

        elegirOpcion(opcionSeleccionada, cantidadDinero);

    }

    private double preguntarPorCantidadDinero() {
        double cantidadDinero = Double.parseDouble(JOptionPane
                .showInputDialog(null, MessageConstants.MENSAJE_PIDE_CANTIDAD));

        if (cantidadDinero < 0) {
            JOptionPane.showMessageDialog(null, MessageConstants.MENSAJE_CANTIDAD_POSITIVA);
            return preguntarPorCantidadDinero();
        }

        return cantidadDinero;
    }

    private void elegirOpcion(String selectedOption, double cantidadDinero) {
        String[] currencies = selectedOption.split(" - ");
        String fromCurrency = currencies[0];
        String toCurrency = currencies[1];
        hacerCambio(fromCurrency, toCurrency, cantidadDinero);
    }

    private void hacerCambio(String monedaActual, String monedaDeCambio, double cantidad){
        Conversion conversion = new Conversion();
        conversion.realizaConversion(monedaActual, monedaDeCambio, cantidad);
    }


}
