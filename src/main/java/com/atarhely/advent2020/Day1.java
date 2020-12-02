package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
	private static List<Integer> INPUT;
	
	public Day1() throws IOException {
		String raw = Files.readString(Paths.get("./advent/src/main/resources/2020/input-day1.txt"));
		INPUT = Arrays.stream(raw.split(System.lineSeparator())).map(Integer::parseInt).collect(Collectors.toList());
	}
	
	public static void main(String[] args) throws IOException {
		Day1 day1 = new Day1();
		System.out.println("Result 1: " + day1.calculateFirst());
		System.out.println("Result 2: " + day1.calculateSecond());
	}
	
	private int calculateFirst() {
		for (int i = 0; i < INPUT.size() - 1; i++) {
			for (int j = i + 1; j < INPUT.size(); j++) {
				int a = INPUT.get(i);
				int b = INPUT.get(j);
				if (a + b == 2020) {
					System.out.println("a + b = " + a + " + " + b + " = " + (a + b));
					return a * b;
				}
			}
		}
		return 0;
	}
	
	private int calculateSecond() {
		for (int i = 0; i < INPUT.size() - 2; i++) {
			for (int j = i + 1; j < INPUT.size() - 1; j++) {
				for (int k = j + 1; k < INPUT.size(); k++) {
					int a = INPUT.get(i);
					int b = INPUT.get(j);
					int c = INPUT.get(k);
					if (a + b + c == 2020) {
						System.out.println("a + b + c = " + a + " + " + b + " + " + c + " = " + (a + b + c));
						return a * b * c;
					}
				}
			}
		}
		return 0;
	}
}
