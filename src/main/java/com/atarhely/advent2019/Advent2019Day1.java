package com.atarhely.advent2019;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Advent2019Day1 {
    private static final String inputString = "95423\n" +
            "142796\n" +
            "88137\n" +
            "105610\n" +
            "79299\n" +
            "110633\n" +
            "136792\n" +
            "112578\n" +
            "75168\n" +
            "115615\n" +
            "147584\n" +
            "72145\n" +
            "108822\n" +
            "57753\n" +
            "96827\n" +
            "69117\n" +
            "131220\n" +
            "111193\n" +
            "120295\n" +
            "56240\n" +
            "111190\n" +
            "80740\n" +
            "137267\n" +
            "113183\n" +
            "126821\n" +
            "58966\n" +
            "63556\n" +
            "110977\n" +
            "100328\n" +
            "75367\n" +
            "57371\n" +
            "88235\n" +
            "134475\n" +
            "109071\n" +
            "92653\n" +
            "73347\n" +
            "135186\n" +
            "64534\n" +
            "81198\n" +
            "55423\n" +
            "100060\n" +
            "149555\n" +
            "110905\n" +
            "102826\n" +
            "129023\n" +
            "112618\n" +
            "146542\n" +
            "102579\n" +
            "67193\n" +
            "84258\n" +
            "60679\n" +
            "86674\n" +
            "124720\n" +
            "68719\n" +
            "55259\n" +
            "76421\n" +
            "70397\n" +
            "67998\n" +
            "73366\n" +
            "106401\n" +
            "59402\n" +
            "112481\n" +
            "131113\n" +
            "142606\n" +
            "107732\n" +
            "69291\n" +
            "61575\n" +
            "131019\n" +
            "51510\n" +
            "101215\n" +
            "116973\n" +
            "63530\n" +
            "146179\n" +
            "132427\n" +
            "127777\n" +
            "127040\n" +
            "143964\n" +
            "120340\n" +
            "144404\n" +
            "72156\n" +
            "96412\n" +
            "140554\n" +
            "60228\n" +
            "52590\n" +
            "128157\n" +
            "120444\n" +
            "125649\n" +
            "111641\n" +
            "117476\n" +
            "139326\n" +
            "149188\n" +
            "133599\n" +
            "55273\n" +
            "83773\n" +
            "50458\n" +
            "105166\n" +
            "76469\n" +
            "66681\n" +
            "84288\n" +
            "103708";
    List<Double> inputs = Arrays.stream(inputString.split("\n"))
            .map(s -> Double.parseDouble(s))
            .collect(Collectors.toList());

    public static void main(String[] args) {
        Advent2019Day1 advent2019Day1 = new Advent2019Day1();
        System.out.println(advent2019Day1.inputs.stream()
                .map(i -> advent2019Day1.getFullFuelRequirements(i))
                .reduce(0, Integer::sum));
    }

    public int getFuelRequirements(double mass) {
        return (int) Math.floor(mass / 3.0) - 2;
    }

    public int getFullFuelRequirements(double mass) {
        if (mass <= 0) return 0;

        int fuelRequirement = (int) Math.floor(mass / 3.0) - 2;
        if (fuelRequirement <= 0) return 0;
        return fuelRequirement + getFullFuelRequirements(fuelRequirement);
    }
}
