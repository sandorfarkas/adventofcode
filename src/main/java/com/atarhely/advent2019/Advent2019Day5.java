package com.atarhely.advent2019;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class Advent2019Day5 {
    public static void main(String[] args) {
        Advent2019Day5 advent2019Day5 = new Advent2019Day5();
        List<Integer> memory = List.of(3,225,1,225,6,6,1100,1,238,225,104,0,1101,91,67,225,1102,67,36,225,1102,21,90,225,2,13,48,224,101,-819,224,224,4,224,1002,223,8,223,101,7,224,224,1,223,224,223,1101,62,9,225,1,139,22,224,101,-166,224,224,4,224,1002,223,8,223,101,3,224,224,1,223,224,223,102,41,195,224,101,-2870,224,224,4,224,1002,223,8,223,101,1,224,224,1,224,223,223,1101,46,60,224,101,-106,224,224,4,224,1002,223,8,223,1001,224,2,224,1,224,223,223,1001,191,32,224,101,-87,224,224,4,224,102,8,223,223,1001,224,1,224,1,223,224,223,1101,76,90,225,1101,15,58,225,1102,45,42,224,101,-1890,224,224,4,224,1002,223,8,223,1001,224,5,224,1,224,223,223,101,62,143,224,101,-77,224,224,4,224,1002,223,8,223,1001,224,4,224,1,224,223,223,1101,55,54,225,1102,70,58,225,1002,17,80,224,101,-5360,224,224,4,224,102,8,223,223,1001,224,3,224,1,223,224,223,4,223,99,0,0,0,677,0,0,0,0,0,0,0,0,0,0,0,1105,0,99999,1105,227,247,1105,1,99999,1005,227,99999,1005,0,256,1105,1,99999,1106,227,99999,1106,0,265,1105,1,99999,1006,0,99999,1006,227,274,1105,1,99999,1105,1,280,1105,1,99999,1,225,225,225,1101,294,0,0,105,1,0,1105,1,99999,1106,0,300,1105,1,99999,1,225,225,225,1101,314,0,0,106,0,0,1105,1,99999,1008,677,677,224,102,2,223,223,1005,224,329,1001,223,1,223,1108,677,226,224,1002,223,2,223,1006,224,344,101,1,223,223,107,677,226,224,1002,223,2,223,1006,224,359,101,1,223,223,108,677,677,224,1002,223,2,223,1006,224,374,1001,223,1,223,108,226,677,224,1002,223,2,223,1006,224,389,101,1,223,223,7,226,677,224,102,2,223,223,1006,224,404,1001,223,1,223,1108,677,677,224,1002,223,2,223,1005,224,419,101,1,223,223,1008,226,677,224,102,2,223,223,1006,224,434,101,1,223,223,107,226,226,224,102,2,223,223,1005,224,449,1001,223,1,223,1007,677,677,224,1002,223,2,223,1006,224,464,1001,223,1,223,1007,226,226,224,1002,223,2,223,1005,224,479,101,1,223,223,1008,226,226,224,102,2,223,223,1006,224,494,1001,223,1,223,8,226,226,224,102,2,223,223,1006,224,509,101,1,223,223,1107,677,677,224,102,2,223,223,1005,224,524,1001,223,1,223,1108,226,677,224,1002,223,2,223,1006,224,539,101,1,223,223,1107,677,226,224,1002,223,2,223,1006,224,554,101,1,223,223,1007,677,226,224,1002,223,2,223,1005,224,569,101,1,223,223,7,677,226,224,1002,223,2,223,1006,224,584,101,1,223,223,107,677,677,224,1002,223,2,223,1005,224,599,1001,223,1,223,8,226,677,224,1002,223,2,223,1005,224,614,101,1,223,223,7,677,677,224,1002,223,2,223,1006,224,629,1001,223,1,223,1107,226,677,224,1002,223,2,223,1006,224,644,101,1,223,223,108,226,226,224,102,2,223,223,1005,224,659,1001,223,1,223,8,677,226,224,1002,223,2,223,1005,224,674,101,1,223,223,4,223,99,226);
//        List<Integer> memory = List.of(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99);
//        List<Integer> memory = List.of(3,9,7,9,10,9,4,9,99,-1,8);
//        List<Integer> memory = List.of(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9);
//        List<Integer> memory = List.of(1108,819,819,5,104,-1,99);

        System.out.println(advent2019Day5.run(memory));
    }

    public List<Integer> run(List<Integer> originalMemory) {
        List<Integer> memory = new ArrayList<>(originalMemory);
        for (int pc = 0; pc < memory.size(); ) {
            Integer instruction = memory.get(pc);
            Command command = getCommand(instruction);
            if (command.getCommand() == 99) return memory;

            Integer param1 = pc + 1 < memory.size() ? memory.get(pc + 1) : null;
            Integer param2 = pc + 2 < memory.size() ? memory.get(pc + 2) : null;
            Integer param3 = pc + 3 < memory.size() ? memory.get(pc + 3) : null;

            if (command.getCommand() == 1) {
                memory.set(param3, getValue(param1, command.getParamMode1(), memory) + getValue(param2, command.getParamMode2(), memory));
                pc = pc + 4;
            } else if (command.getCommand() == 2) {
                memory.set(param3, getValue(param1, command.getParamMode1(), memory) * getValue(param2, command.getParamMode2(), memory));
                pc = pc + 4;
            } else if (command.getCommand() == 3) {
                memory.set(param1, 5);
                pc = pc + 2;
            } else if (command.getCommand() == 4) {
                System.out.println(getValue(param1, command.getParamMode1(), memory));
                pc = pc + 2;
            } else if (command.getCommand() == 5) {
                if (getValue(param1, command.getParamMode1(), memory) != 0) {
                    pc = getValue(param2, command.getParamMode2(), memory);
                } else {
                    pc = pc + 3;
                }
            } else if (command.getCommand() == 6) {
                if (getValue(param1, command.getParamMode1(), memory) == 0) {
                    pc = getValue(param2, command.getParamMode2(), memory);
                } else {
                    pc = pc + 3;
                }
            } else if (command.getCommand() == 7) {
                memory.set(param3, getValue(param1, command.getParamMode1(), memory) < getValue(param2, command.getParamMode2(), memory) ? 1 : 0);
                pc = pc + 4;
            } else if (command.getCommand() == 8) {
                memory.set(param3, getValue(param1, command.getParamMode1(), memory).equals(getValue(param2, command.getParamMode2(), memory)) ? 1 : 0);
                pc = pc + 4;
            }
        }
        return memory;
    }

    private Integer getValue(Integer param, int paramMode, List<Integer> memory) {
        if (paramMode == 0) {
            return memory.get(param);
        } else {
            return param;
        }
    }

    public Command getCommand(Integer instruction) {
        Command command = new Command();
        String instructionText = instruction.toString();

        if (instructionText.length() <= 2) {
            command.setCommand(instruction);
        } else if (instructionText.length() == 3) {
            command.setCommand(Integer.parseInt(instructionText.substring(1, 3)));
            command.setParamMode1(Integer.parseInt(instructionText.substring(0, 1)));
        } else if (instructionText.length() == 4) {
            command.setCommand(Integer.parseInt(instructionText.substring(2, 4)));
            command.setParamMode1(Integer.parseInt(instructionText.substring(1, 2)));
            command.setParamMode2(Integer.parseInt(instructionText.substring(0, 1)));
        }

        return command;
    }

    @Data
    public static class Command {
        private int command;
        private int paramMode1;
        private int paramMode2;
        private final int paramMode3 = 0;
    }
}
