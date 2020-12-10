package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day9 {
	private static final List<Long> INPUT = new ArrayList<>();
//	private static final int preamble = 5;
//	private static final Long numberToFind = 127L;
//	private static final String inputFileName = "./advent/src/main/resources/2020/input-day9-test1.txt";
	private static final int preamble = 25;
	private static final Long numberToFind = 258585477L;
	private static final String inputFileName = "./advent/src/main/resources/2020/input-day9.txt";
	
	public Day9() throws IOException {
		Scanner scanner = new Scanner(Paths.get(inputFileName));
		while (scanner.hasNextLine()) {
			INPUT.add(Long.valueOf(scanner.nextLine()));
		}
	}
	
	public static void main(String[] args) throws IOException {
		Day9 day9 = new Day9();
		System.out.println("Result 1: " + day9.calculateFirst());
		System.out.println("Result 2: " + day9.calculateSecond());
	}
	
	private long calculateSecond() {
		for (int i = 0; i < INPUT.size() - 1; i++) {
			Long sum = INPUT.get(i);
			for (int j = i + 1; j < INPUT.size(); j++) {
				sum += INPUT.get(j);
				if (sum.equals(numberToFind)) {
					Long max = IntStream.range(i, j + 1)
							.mapToLong(INPUT::get)
							.max().orElseThrow();
					Long min = IntStream.range(i, j + 1)
							.mapToLong(INPUT::get)
							.min().orElseThrow();
					return min + max;
				}
			}
			
		}
		return 0;
	}
	
	private long calculateFirst() {
		for (int pc=preamble; pc<INPUT.size(); pc++) {
			if (!isValid(preamble, pc)) {
				return INPUT.get(pc);
			}
		}
		return 0;
	}
	
	private boolean isValid(int preamble, int pc) {
		for (int i = pc - preamble; i < pc - 1; i++) {
			for (int j = i + 1; j < pc; j++) {
				Long sum = INPUT.get(i) + INPUT.get(j);
				if (sum.equals(INPUT.get(pc))) {
					return true;
				}
			}
		}
		return false;
	}
}
