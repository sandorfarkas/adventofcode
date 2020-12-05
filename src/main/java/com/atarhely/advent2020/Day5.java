package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import lombok.Data;

public class Day5 {
	private static final List<String> INPUT = new ArrayList<>();
	private static List<Seat> seats;
	
	public Day5() throws IOException {
		Scanner scanner = new Scanner(Paths.get("./advent/src/main/resources/2020/input-day5.txt"));
		while (scanner.hasNext()) {
			INPUT.add(scanner.next());
		}
	}
	
	public static void main(String[] args) throws IOException {
		Day5 day5 = new Day5();
		seats = calculateSeats();
		System.out.println("Result 1: " + day5.calculateFirst());
		System.out.println("Result 2: " + day5.calculateSecond());
	}
	
	private static List<Seat> calculateSeats() {
		return INPUT.stream().map(input -> calculateSeatFromInput(input)).collect(Collectors.toList());
	}
	
	private static Seat calculateSeatFromInput(String input) {
		Seat seat = new Seat();
		seat.setInput(input);
		seat.setRow(calculateRow(input.substring(0, 7), 0, 127));
		seat.setColumn(calculateColumn(input.substring(7), 0, 7));
		seat.setSeatId(seat.getRow() * 8 + seat.getColumn());
		return seat;
	}
	
	private static int calculateRow(String substring, int min, int max) {
		if (min == max) {
			return min;
		}
		char range = substring.charAt(0);
		if (substring.length() == 1) {
			return (range == 'F') ? min : max;
		}
		int middle = (min + max) / 2;
		return (range == 'F')
				? calculateRow(substring.substring(1), min, middle)
				: calculateRow(substring.substring(1), middle + 1, max);
	}
	
	private static int calculateColumn(String substring, int min, int max) {
		if (min == max) {
			return min;
		}
		char range = substring.charAt(0);
		if (substring.length() == 1) {
			return (range == 'L') ? min : max;
		}
		int middle = (min + max) / 2;
		return (range == 'L')
				? calculateColumn(substring.substring(1), min, middle)
				: calculateColumn(substring.substring(1), middle + 1, max);
	}
	
	private long calculateFirst() {
		return seats.stream()
				.map(Seat::getSeatId)
				.max(Integer::compareTo).orElseThrow();
	}
	
	private long calculateSecond() {
		List<Integer> takenSeats = seats.stream()
				.map(Seat::getSeatId)
				.sorted()
				.collect(Collectors.toList());
		
		for (int i = 0; i < takenSeats.size() - 1; i++) {
			if (takenSeats.get(i) + 1 != takenSeats.get(i + 1)) {
				return takenSeats.get(i) + 1;
			}
		}
		
		return 0;
	}
	
	@Data
	private static class Seat {
		private String input;
		private int row;
		private int column;
		private int seatId;
	}
}
