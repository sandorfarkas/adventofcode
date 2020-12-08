package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import lombok.Data;

public class Day8 {
	private final List<Command> code = new ArrayList<>();
	
	private int acc = 0;
	private int pc = 0;
	
	public Day8() throws IOException {
		Scanner scanner = new Scanner(Paths.get("./advent/src/main/resources/2020/input-day8.txt"));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split("\\s+");
			Command command = new Command();
			command.command = parts[0];
			command.value = Integer.parseInt(parts[1]);
			code.add(command);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Day8 day8 = new Day8();
		System.out.println("Result 1: " + day8.calculateFirst());
		System.out.println("Result 2: " + day8.calculateSecond());
	}
	
	private int calculateFirst() {
		Set<Integer> visitedLines = new HashSet<>();
		while (!visitedLines.contains(pc) && pc < code.size()) {
			Command command = code.get(pc);
			visitedLines.add(pc);
			switch (command.command) {
				case "nop":
					pc++;
					break;
				case "acc":
					acc += command.value;
					pc++;
					break;
				case "jmp":
					pc += command.value;
					break;
			}
		}
		return acc;
	}
	
	private int calculateSecond() {
		int result;
		int swapLocation = 0;
		boolean firstRun = true;
		do {
			pc = 0;
			acc = 0;
			result = calculateFirst();
			if (pc > code.size() - 1) {
				return result;
			}
			if (!firstRun) {
				swap(swapLocation);
				swapLocation++;
			}
			swapLocation = swapOne(swapLocation);
			firstRun = false;
		}
		while (pc != code.size() + 1);
		
		return result;
	}
	
	private int swapOne(int swapLocation) {
		for (int i = swapLocation; swapLocation < code.size(); i++) {
			Command command = code.get(i);
			if (command.command.equals("nop") || command.command.equals("jmp")) {
				swap(i);
				return i;
			}
		}
		return swapLocation;
	}
	
	private void swap(int location) {
		Command command = code.get(location);
		if (command.command.equals("jmp")) {
			command.command = "nop";
			return;
		}
		if (command.command.equals("nop")) {
			command.command = "jmp";
		}
	}
	
	@Data
	private static class Command {
		private String command;
		private Integer value;
	}
}
