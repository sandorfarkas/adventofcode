package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day10 {
	private List<Integer> input;
	
	public Day10() throws IOException {
		Scanner scanner = new Scanner(Paths.get("./advent/src/main/resources/2020/input-day10-test2.txt"));
		input = new ArrayList<>();
		while (scanner.hasNextLine()) {
			input.add(Integer.parseInt(scanner.nextLine()));
		}
		List<Integer> collect = input.stream().sorted().collect(Collectors.toList());
		collect.add(collect.get(collect.size() - 1) + 3);
		input = List.copyOf(input.stream().sorted().collect(Collectors.toList()));
		System.out.println(input);
	}
	
	public static void main(String[] args) throws IOException {
		Day10 day10 = new Day10();
		System.out.println("Result 1: " + day10.calculateFirst());
		System.out.println("Result 2: " + day10.calculateSecond());
	}
	
	private long calculateFirst() {
		long prev = 0;
		long one = 0;
		long two = 0;
		long three = 0;
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i) - prev == 1) {
				one++;
			}
			if (input.get(i) - prev == 2) {
				two++;
			}
			if (input.get(i) - prev == 3) {
				three++;
			}
			prev = input.get(i);
		}
		three++;
		System.out.println("one: " + one + " two: " + two + " three: " + three);
		return one * three;
	}
	
	private long calculateSecond() {
		List<Integer> children = getChildren(0);
		return children.stream().mapToLong(this::countChildren).sum();
	}
	
	private long countChildren(Integer parentValue) {
//		System.out.print(parentValue + " ");
		List<Integer> children = getChildren(parentValue);
		if (children.isEmpty()) {
//			System.out.println();
			return 1;
		}
		return children.stream().mapToLong(this::countChildren).sum();
//		return (children.size() > 1 ? children.size() : 0) + children.stream().mapToLong(this::countChildren).sum();
	}
	
	private List<Integer> getChildren(Integer parentValue) {
		return input.stream()
				.filter(i -> 1 <= i - parentValue && i - parentValue <= 3)
				.collect(Collectors.toList());
	}
}
