package com.mueeee.clinormal;

/**
 * Application
 */
public class CliNormalApp {

    public static void main(String[] args) {
        System.out.println("Hello GraalVM CLI Application");
        for (String arg : args) {
            System.out.println(arg);
        }
    }

}
