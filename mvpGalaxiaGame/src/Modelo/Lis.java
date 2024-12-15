/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Lis {
    public static void main(String[] args) {
        List<Integer> numeros=new ArrayList<>();
        numeros.add(5);
        numeros.add(10);
        
        numeros.remove(0);
        System.out.println(numeros.get(0));
    }
}
