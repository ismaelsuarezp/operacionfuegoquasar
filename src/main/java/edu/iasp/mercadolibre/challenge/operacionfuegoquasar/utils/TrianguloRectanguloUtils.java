package edu.iasp.mercadolibre.challenge.operacionfuegoquasar.utils;

public class TrianguloRectanguloUtils {

    private TrianguloRectanguloUtils (){

    }

    public static double calcularHipotenusa(double a, double b){
        return Math.sqrt((a*a) + (b*b));
    }

    public static double calcularCateto(double hipotenusa, double cateto){
        return Math.sqrt((hipotenusa* hipotenusa) - (cateto*cateto));
    }
}
