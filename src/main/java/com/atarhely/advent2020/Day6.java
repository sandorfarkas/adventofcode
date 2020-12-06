package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day6 {
	private List<List<String>> input = new ArrayList<>();
	
	public Day6() throws IOException {
		Scanner scanner = new Scanner(Paths.get("./advent/src/main/resources/2020/input-day6.txt"));
		List<String> group = new ArrayList<>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.equals("")) {
				input.add(group);
				group = new ArrayList<>();
			} else {
				group.add(line);
			}
		}
		input.add(group);
	}
	
	public static void main(String[] args) throws IOException {
		Day6 day6 = new Day6();
		System.out.println("Result 1: " + day6.calculateFirst());
		System.out.println("Result 2: " + day6.calculateSecond());
	}
	
	private int calculateFirst() {
		return input.stream()
				.map(group -> {
					Set<Integer> occurance = new HashSet<>();
					String.join("", group).chars().forEach(ch -> occurance.add(ch));
					return occurance.size();
				})
				.reduce(Integer::sum).orElseThrow();
	}
	
	private int calculateSecond() {
		return input.stream()
				.map(group -> {
					Map<Integer, Integer> occurance = new HashMap<>();
					group.stream().forEach(answer -> {
							answer.chars().forEach(ch -> {
								occurance.put(ch, occurance.containsKey(ch) ? occurance.get(ch) + 1 : 1);
							});
						}
					);
					return (int) occurance.entrySet().stream()
							.filter(entry -> entry.getValue().equals(group.size()))
							.count();
				})
				.reduce(Integer::sum).orElseThrow();
	}
}
