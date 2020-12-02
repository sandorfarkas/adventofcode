package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
	private static List<String> INPUT;
	
	public Day2() throws IOException {
		String raw = Files.readString(Paths.get("./advent/src/main/resources/2020/input-day2.txt"));
		INPUT = Arrays.stream(raw.split(System.lineSeparator())).collect(Collectors.toList());
	}
	
	public static void main(String[] args) throws IOException {
		Day2 day2 = new Day2();
		System.out.println("Result 1: " + day2.calculateFirst());
		System.out.println("Result 2: " + day2.calculateSecond());
	}
	
	private long calculateFirst() {
		return INPUT.stream().filter(
				input -> {
					String[] parts = input.split("\\ ");
					String[] minMax = parts[0].split("-");
					int min = Integer.parseInt(minMax[0]);
					int max = Integer.parseInt(minMax[1]);
					char chr = parts[1].charAt(0);
					String pass = parts[2];
					long occurence = pass.chars().filter(ch -> ch == chr ).count();
					if (occurence >= min && occurence <= max) {
						System.out.println(input);
					}
					return (occurence >= min && occurence <= max);
				}
		).count();
	}
	
	private long calculateSecond() {
		return INPUT.stream().filter(
				input -> {
					String[] parts = input.split("\\ ");
					String[] positions = parts[0].split("-");
					int pos1 = Integer.parseInt(positions[0]);
					int pos2 = Integer.parseInt(positions[1]);
					char chr = parts[1].charAt(0);
					String pass = parts[2];
					boolean predicate = (pass.charAt(pos1 - 1) == chr || pass.charAt(pos2 - 1) == chr) && pass.charAt(pos1 - 1) != pass.charAt(pos2 - 1);
					if (predicate) {
						System.out.println(input);
					}
					return predicate;
				}
		).count();
	}
}
