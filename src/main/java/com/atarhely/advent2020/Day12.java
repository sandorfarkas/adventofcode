package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Day12 {
	private static final List<String> INPUT = new ArrayList<>();
	
	public Day12() throws IOException {
		Scanner scanner = new Scanner(Paths.get("./src/main/resources/2020/input-day12.txt"));
		while (scanner.hasNextLine()) {
			INPUT.add(scanner.nextLine());
		}
	}
	
	public static void main(String[] args) throws IOException {
		Day12 day12 = new Day12();
		System.out.println("Result 1: " + day12.calculateFirst());
		System.out.println("Result 2: " + day12.calculateSecond());
//		Coord coord = new Coord(10, 4);
//		System.out.println(day12.rotate(coord, Direction.L, 90));
//		System.out.println(day12.rotate(coord, Direction.L, 180));
//		System.out.println(day12.rotate(coord, Direction.R, 90));
//		System.out.println(day12.rotate(coord, Direction.R, 180));
	}
	
	private int calculateSecond() {
		Coord wayPoint = new Coord(10, 1);
		List<Coord> moves = new ArrayList<>();
		for (String i : INPUT) {
			String command = i.substring(0, 1);
			Integer value = Integer.parseInt(i.substring(1));
			
			switch (command) {
				case "N":
					wayPoint.y += value;
					break;
				case "E":
					wayPoint.x += value;
					break;
				case "S":
					wayPoint.y -= value;
					break;
				case "W":
					wayPoint.x -= value;
					break;
				case "L":
					wayPoint = rotate(wayPoint, Direction.L, value);
					break;
				case "R":
					wayPoint = rotate(wayPoint, Direction.R, value);
					break;
				case "F":
					moves.add(new Coord(wayPoint.x * value, wayPoint.y * value));
					break;
			}
		}
		
		int dx = moves.stream().map(c -> c.x).reduce(Integer::sum).orElseThrow();
		int dy = moves.stream().map(c -> c.y).reduce(Integer::sum).orElseThrow();
		return Math.abs(dx) + Math.abs(dy);
	}
	
	private Coord rotate(Coord wayPoint, Direction direction, Integer value) {
		wayPoint.y *= -1;
		double angle = direction == Direction.L ? (double) value * -1 : (double) value;
		
		double radian = Math.toRadians(angle);
		
		int x = (int) Math.round(wayPoint.x * Math.cos(radian) - wayPoint.y * Math.sin(radian));
		int y = (int) Math.round(wayPoint.x * Math.sin(radian) + wayPoint.y * Math.cos(radian)) * -1;
		return new Coord(x, y);
	}
	
	private int calculateFirst() {
		Map<Direction, Integer> moves = new HashMap<>();
		Direction direction = Direction.E;
		
		for (String i : INPUT) {
			String command = i.substring(0, 1);
			Integer value = Integer.parseInt(i.substring(1));
			
			switch (command) {
				case "N":
					moves.put(Direction.N, moves.containsKey(Direction.N) ? moves.get(Direction.N) + value : value);
					break;
				case "E":
					moves.put(Direction.E, moves.containsKey(Direction.E) ? moves.get(Direction.E) + value : value);
					break;
				case "S":
					moves.put(Direction.S, moves.containsKey(Direction.S) ? moves.get(Direction.S) + value : value);
					break;
				case "W":
					moves.put(Direction.W, moves.containsKey(Direction.W) ? moves.get(Direction.W) + value : value);
					break;
				case "L":
					direction = updateDirection(direction, Direction.L, value);
					break;
				case "R":
					direction = updateDirection(direction, Direction.R, value);
					break;
				case "F":
					updateMoves(moves, direction, value);
					break;
			}
		}
		return Math.abs(moves.getOrDefault(Direction.N, 0) - moves.getOrDefault(Direction.S, 0))
				+ Math.abs(moves.getOrDefault(Direction.E, 0) - moves.getOrDefault(Direction.W, 0));
	}
	
	private void updateMoves(Map<Direction, Integer> moves, Direction direction, Integer value) {
		switch (direction) {
			case N:
				moves.put(Direction.N, moves.containsKey(Direction.N) ? moves.get(Direction.N) + value : value);
				break;
			case E:
				moves.put(Direction.E, moves.containsKey(Direction.E) ? moves.get(Direction.E) + value : value);
				break;
			case S:
				moves.put(Direction.S, moves.containsKey(Direction.S) ? moves.get(Direction.S) + value : value);
				break;
			case W:
				moves.put(Direction.W, moves.containsKey(Direction.W) ? moves.get(Direction.W) + value : value);
				break;
		}
	}
	
	private Direction updateDirection(Direction direction, Direction newDirection, Integer value) {
		int degree = 0;
		switch (direction) {
			case E:
				degree = 90;
				break;
			case S:
				degree = 180;
				break;
			case W:
				degree = 270;
				break;
			case N:
				degree = 0;
				break;
		}
		switch (newDirection) {
			case L:
				degree -= value; break;
			case R:
				degree += value; break;
		}
		if (degree < 0) {
			degree = 360 + degree;
		}
		if (degree >= 360) {
			degree = degree % 360;
		}
		switch (degree) {
			case 0:
				return Direction.N;
			case 90:
				return Direction.E;
			case 180:
				return Direction.S;
			case 270:
				return Direction.W;
		}
		return null;
	}
	
	private enum Direction {
		N, E, W, S, L, R, F
	}
	
	@Data
	@AllArgsConstructor
	private static class Coord {
		int x;
		int y;
	}
}
