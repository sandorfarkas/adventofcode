package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day3 {
	private static Character[][] MAP;
	private final int WIDTH;
	private final int HEIGHT;
	
	public Day3() throws IOException {
		String raw = Files.readString(Paths.get("./advent/src/main/resources/2020/input-day3.txt"));
		String[] rows = raw.split(System.lineSeparator());
		WIDTH = rows[0].length();
		HEIGHT = rows.length;
		MAP = new Character[WIDTH][HEIGHT];
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				MAP[x][y] = rows[y].charAt(x);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Day3 day3 = new Day3();
		System.out.println("Result 1: " + day3.calculateFirst());
		System.out.println("Result 1: " + day3.calculateSecond());
	}
	
	private long calculateFirst() {
		return calculateWithTraverse(3, 1);
	}
	
	private long calculateSecond() {
		return calculateWithTraverse(1, 1) * calculateWithTraverse(3, 1) * calculateWithTraverse(5, 1)
				* calculateWithTraverse(7, 1) * calculateWithTraverse(1, 2);
	}
	
	private Character getCharAt(int x, int y) {
		return MAP[x % WIDTH][y];
	}
	
	private long calculateWithTraverse(int dx, int dy) {
		int x = 0;
		long count = 0;
		for (int y = 0; y < HEIGHT; y += dy) {
			if (getCharAt(x, y).equals('#')) {
				count++;
			}
			x += dx;
		}
		return count;
	}
}
