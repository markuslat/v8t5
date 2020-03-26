package com.example.v8t5;

import android.widget.TextView;
import java.util.ArrayList;

public class BottleDispenser {

    private String name;
    private int bottles;
    private double money;

    String [] names = {"PepsiMax", "Pepsi", "Coca-ColaZero",
            "Coca-ColaZero", "FantaZero"};
    double [] prices = {2, 2, 2, 3, 2};
    String [] sizes = {"0.5", "0.5", "0.5", "1.5", "0.5"};

    ArrayList<Bottle> bottles_array = new ArrayList<>();

    private static BottleDispenser bd = new BottleDispenser();

    private BottleDispenser() {
        bottles = 5;
        money = 0.0;

        for(int i = 0;i<bottles;i++) {
            Bottle bottle = new Bottle(names[i], sizes[i], prices[i]);
            bottles_array.add(i, bottle);
        }
    }

    public static BottleDispenser getInstance() {
        return bd;
    }

    public void addMoney(TextView text) {
        text.setText("Klink! Added more money!");
    }

    public double buyBottle(String name, String size, double money) {

        int a;
        int i = 0;
        int k = 0;
        double hinta;

        for (a = 0; a <= (bottles_array.size() - 1); a++) {

            if (name.equals(bottles_array.get(a).getName()) && size.equals(bottles_array.get(a).getSize())) {

                hinta = bottles_array.get(a).getPrice();

                if ((money - hinta) >= 0) {
                    money = (money - hinta);
                    i = 2;
                    k = a;
                    break;
                } else {
                    i = -1;
                    break;
                }
            } else {
                i = 0;
            }
        }

        if (i == 2) {
            /*kuittiOsoittaja.add(new Receipt(pulloOsoittaja.get(k).getNimi(), pulloOsoittaja.get(k).getTekija(), pulloOsoittaja.get(k).getEnergia(), pulloOsoittaja.get(k).getKoko(), pulloOsoittaja.get(k).getHinta()));*/

            bottles_array.remove(k);
            System.out.println("Rahaa: " + money);
            return (money);
        } else if (i == -1) {
            //jos ei tarpeeksi rahaa
            return (-1);
        } else {
            //jos loppunut
            return (-2);
        }
    }


    public void returnMoney(TextView text, double coins) {

        text.setText("Klink klink. Money came out! "
                + "You got " + coins + "€ back\n");
        money = 0;
    }
/*
    public void list() {
        for (int s = 0;s < bottles_array.size(); s++) {
            System.out.print(s+1 + ". Name: " + bottles_array.get(s).getName()
                    + "\n\tSize: " + bottles_array.get(s).getSize() +
                    "\tPrice: " + bottles_array.get(s).getPrice() + "\n");
        }
    }*/
}
