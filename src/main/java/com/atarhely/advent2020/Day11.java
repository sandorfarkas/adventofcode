package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day11 {
	private int width = 0;
	private int height = 0;
	private char[][] map;
	
	public Day11() throws IOException {
		String raw = Files.readString(Paths.get("./src/main/resources/2020/input-day11.txt"));
		String[] rows = raw.split(System.lineSeparator());
		width = rows[0].length();
		height = rows.length;
		map = new char[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				map[x][y] = rows[y].charAt(x);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Day11 day11 = new Day11();
		System.out.println("Result 1: " + day11.calculateFirst());
		System.out.println("Result 2: " + day11.calculateSecond());
	}
	
	private int calculateSecond() {
		char[][] prev = map;
		char[][] next = calculateNextGenerationSecond(prev);
		while (!this.equals(prev, next)) {
			//printMap(prev);
			//System.out.println("---------");
			prev = next;
			next = calculateNextGenerationSecond(prev);
		}
		return countOccupiedSeats(prev);
	}
	
	private int calculateFirst() {
		char[][] prev = map;
		char[][] next = calculateNextGenerationFirst(prev);
		while (!this.equals(prev, next)) {
			prev = next;
			next = calculateNextGenerationFirst(prev);
		}
		return countOccupiedSeats(prev);
	}
	
	private void printMap(char[][] map) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
	}
	
	private int countOccupiedSeats(char[][] finalMap) {
		int count = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (finalMap[x][y] == '#') {
					count++;
				}
			}
		}
		return count;
	}
	
	private char[][] calculateNextGenerationFirst(char[][] prev) {
		char[][] next = new char[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int occupiedNeighbors = countOccupiedNeighbors(prev, x, y);
				if (prev[x][y] == 'L' && occupiedNeighbors == 0) {
					next[x][y] = '#';
				}
				else if (prev[x][y] == '#' && 4 <= occupiedNeighbors) {
					next[x][y] = 'L';
				}
				else {
					next[x][y] = prev[x][y];
				}
			}
		}
		return next;
	}
	
	private char[][] calculateNextGenerationSecond(char[][] prev) {
		char[][] next = new char[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int occupiedNeighbors = countOccupiedNeighborsSecondRound(prev, x, y);
				if (prev[x][y] == 'L' && occupiedNeighbors == 0) {
					next[x][y] = '#';
				}
				else if (prev[x][y] == '#' && 5 <= occupiedNeighbors) {
					next[x][y] = 'L';
				}
				else {
					next[x][y] = prev[x][y];
				}
			}
		}
		return next;
	}
	
	private int countOccupiedNeighbors(char[][] map, int x, int y) {
		int count = 0;
		if (x > 0 && y > 0 && map[x - 1][y - 1] == '#') {
			count++;
		}
		if (y > 0 && map[x][y - 1] == '#') {
			count++;
		}
		if (x < width - 1 && y > 0 && map[x + 1][y - 1] == '#') {
			count++;
		}
		if (x > 0 && map[x - 1][y] == '#') {
			count++;
		}
		if (x < width - 1 && map[x + 1][y] == '#') {
			count++;
		}
		if (x > 0 && y < height - 1 && map[x - 1][y + 1] == '#') {
			count++;
		}
		if (y < height - 1 && map[x][y + 1] == '#') {
			count++;
		}
		if (x < width - 1 && y < height - 1 && map[x + 1][y + 1] == '#') {
			count++;
		}
		return count;
	}
	
	private int countOccupiedNeighborsSecondRound(char[][] map, int x, int y) {
		int count = 0;
		int dx = -1;
		int dy = -1;
		while (x + dx >= 0 && y + dy >= 0 && map[x + dx][y + dy] != 'L') {
			if (map[x + dx][y + dy] == '#') {
				count++;
				break;
			}
			dx--;
			dy--;
		}
		
		dy = -1;
		while (y + dy >= 0 && map[x][y + dy] != 'L') {
			if (map[x][y + dy] == '#') {
				count++;
				break;
			}
			dy--;
		}
		
		dx = 1;
		dy = -1;
		while (x + dx <= width - 1 && y + dy >= 0 && map[x + dx][y + dy] != 'L') {
			if (map[x + dx][y + dy] == '#') {
				count++;
				break;
			}
			dx++;
			dy--;
		}
		
		dx = -1;
		while (x + dx >= 0 && map[x + dx][y] != 'L') {
			if (map[x + dx][y] == '#') {
				count++;
				break;
			}
			dx--;
		}
		
		dx = 1;
		while (x + dx <= width - 1 && map[x + dx][y] != 'L') {
			if (map[x + dx][y] == '#') {
				count++;
				break;
			}
			dx++;
		}
		
		dx = -1;
		dy = 1;
		while (x + dx >= 0 && y + dy <= height - 1 && map[x + dx][y + dy] != 'L') {
			if (map[x + dx][y + dy] == '#') {
				count++;
				break;
			}
			dx--;
			dy++;
		}
		
		dy = 1;
		while (y + dy <= height - 1 && map[x][y + dy] != 'L') {
			if (map[x][y + dy] == '#') {
				count++;
				break;
			}
			dy++;
		}
		
		dx = 1;
		dy = 1;
		while (x + dx <= width - 1 && y + dy <= height - 1 && map[x + dx][y + dy] != 'L') {
			if (map[x + dx][y + dy] == '#') {
				count++;
				break;
			}
			dx++;
			dy++;
		}
		return count;
	}
	
	private boolean equals(char[][] a, char[][] b) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (a[x][y] != b[x][y]) {
					return false;
				}
			}
		}
		return true;
	}
}
