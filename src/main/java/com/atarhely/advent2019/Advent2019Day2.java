package com.atarhely.advent2019;

import java.util.ArrayList;
import java.util.List;

public class Advent2019Day2 {
    public static void main(String[] args) {
        Advent2019Day2 advent2019Day2 = new Advent2019Day2();
        List<Integer> integers = List.of(1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,6,19,23,2,23,6,27,1,5,27,31,1,31,9,35,2,10,35,39,1,5,39,43,2,43,10,47,1,47,6,51,2,51,6,55,2,55,13,59,2,6,59,63,1,63,5,67,1,6,67,71,2,71,9,75,1,6,75,79,2,13,79,83,1,9,83,87,1,87,13,91,2,91,10,95,1,6,95,99,1,99,13,103,1,13,103,107,2,107,10,111,1,9,111,115,1,115,10,119,1,5,119,123,1,6,123,127,1,10,127,131,1,2,131,135,1,135,10,0,99,2,14,0,0);
//        List<Integer> integers = new ArrayList<>();
//        String raw = "1,12,2,3,1,1,2,3,1,3,4,3,1,5,0,3,2,10,1,19,1,6,19,23,2,23,6,27,1,5,27,31,1,31,9,35,2,10,35,39,1,5,39,43,2,43,10,47,1,47,6,51,2,51,6,55,2,55,13,59,2,6,59,63,1,63,5,67,1,6,67,71,2,71,9,75,1,6,75,79,2,13,79,83,1,9,83,87,1,87,13,91,2,91,10,95,1,6,95,99,1,99,13,103,1,13,103,107,2,107,10,111,1,9,111,115,1,115,10,119,1,5,119,123,1,6,123,127,1,10,127,131,1,2,131,135,1,135,10,0,99,2,14,0,0";
//        Arrays.stream(raw.split(","))
//                .forEach(s -> integers.add(Integer.parseInt(s)));

        System.out.println(advent2019Day2.calculate(integers));

        for (int i=0; i<100;i++) {
            for (int j=0;j<100;j++) {
                List<Integer> tempIntegers = new ArrayList<>(integers);
                tempIntegers.set(1, i);
                tempIntegers.set(2, j);
                Integer out = advent2019Day2.calculate(tempIntegers).get(0);
                if (out == 19690720) System.out.println("i: " + i + " j: " + j + advent2019Day2.calculate(tempIntegers));
            }
        }
    }

    public List<Integer> calculate(List<Integer> originalIntegers) {
        List<Integer> integers = new ArrayList<>(originalIntegers);
        for (int i=0; i<integers.size();i=i+4) {
            if (integers.get(i) == 99) return integers;
            Integer pos1 = integers.get(i+1);
            Integer pos2 = integers.get(i+2);
            Integer pos3 = integers.get(i+3);
            if (integers.get(i) == 1 ) {
                integers.set(pos3, integers.get(pos1) + integers.get(pos2));
            } else if (integers.get(i) == 2 ) {
                integers.set(pos3, integers.get(pos1) * integers.get(pos2));
            }
        }
        return integers;
    }
}
