package com.atarhely.advent2019;

import java.util.ArrayList;
import java.util.List;

public class Advent2019Day4 {
		private static Integer FROM = 356261;
	private static Integer TO = 846303;
//	private static Integer FROM = 111122;
//	private static Integer TO = 111122;

	public static void main(String[] args) {
		Advent2019Day4 advent2019Day4 = new Advent2019Day4();
		int count = 0;
		for (Integer i = FROM; i <= TO; i++) {
			
			if (advent2019Day4.isIncreasing(i) && advent2019Day4.hasDoublePairsOnly(i)) {
				count++;
			}
			
		}
		System.out.println(count);
	}
	
	public boolean hasDoublePairsOnly(Integer number) {
		String raw = number.toString();
		String prevChar = raw.substring(0, 1);
		String sequence = "";
		List<String> sequences = new ArrayList<>();
		
		for (int i=1; i<raw.length(); i++) {
			String currentChar = raw.substring(i, i + 1);
			if (sequence.equals("")) {
				if (!currentChar.equals(prevChar)) {
					prevChar = currentChar;
					continue;
				}
				sequence += prevChar + currentChar;
			} else {
				if (currentChar.equals(prevChar)) {
					sequence += currentChar;
				} else {
					sequences.add(sequence);
					sequence = "";
				}
			}
			prevChar = currentChar;
		}
		if (sequence.length() > 0) sequences.add(sequence);
		
		for (String s : sequences) {
			if (s.length() == 2) return true;
		}
		
		return false;
	}
	
	public boolean hasDoubles(Integer i) {
		String raw = i.toString();
		for (int k = 0; k < 5; k++) {
			if (Integer.parseInt(raw.substring(k, k + 1)) == Integer.parseInt(raw.substring(k + 1, k + 2))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasTriples(Integer i) {
		String raw = i.toString();
		for (int k = 0; k < 4; k++) {
			if (Integer.parseInt(raw.substring(k, k + 1)) == Integer.parseInt(raw.substring(k + 1, k + 2)) &&
					Integer.parseInt(raw.substring(k + 1, k + 2)) == Integer.parseInt(raw.substring(k + 2, k + 3))
			) {
				return !hasFour(i) && !hasFive(i) && !hasSix(i);
			}
		}
		return false;
	}
	
	public boolean hasFour(Integer i) {
		String raw = i.toString();
		for (int k = 0; k < 3; k++) {
			if (Integer.parseInt(raw.substring(k, k + 1)) == Integer.parseInt(raw.substring(k + 1, k + 2)) &&
					Integer.parseInt(raw.substring(k + 1, k + 2)) == Integer.parseInt(raw.substring(k + 2, k + 3))
					&& Integer.parseInt(raw.substring(k + 2, k + 3)) == Integer.parseInt(raw.substring(k + 3, k + 4))
			) {
				return !hasFive(i);
			}
		}
		return false;
	}
	
	public boolean hasFive(Integer i) {
		String raw = i.toString();
		for (int k = 0; k < 2; k++) {
			if (Integer.parseInt(raw.substring(k, k + 1)) == Integer.parseInt(raw.substring(k + 1, k + 2)) &&
					Integer.parseInt(raw.substring(k + 1, k + 2)) == Integer.parseInt(raw.substring(k + 2, k + 3))
					&& Integer.parseInt(raw.substring(k + 2, k + 3)) == Integer.parseInt(raw.substring(k + 3, k + 4))
					&& Integer.parseInt(raw.substring(k + 3, k + 4)) == Integer.parseInt(raw.substring(k + 4, k + 5))
			) {
				return !hasSix(i);
			}
		}
		return false;
	}
	
	public boolean hasSix(Integer i) {
		String raw = i.toString();
		for (int k = 0; k < 1; k++) {
			if (Integer.parseInt(raw.substring(k, k + 1)) == Integer.parseInt(raw.substring(k + 1, k + 2)) &&
					Integer.parseInt(raw.substring(k + 1, k + 2)) == Integer.parseInt(raw.substring(k + 2, k + 3))
					&& Integer.parseInt(raw.substring(k + 2, k + 3)) == Integer.parseInt(raw.substring(k + 3, k + 4))
					&& Integer.parseInt(raw.substring(k + 3, k + 4)) == Integer.parseInt(raw.substring(k + 4, k + 5))
					&& Integer.parseInt(raw.substring(k + 4, k + 5)) == Integer.parseInt(raw.substring(k + 5, k + 6))
			) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isIncreasing(Integer i) {
		String raw = i.toString();
		
		for (int k = 0; k < 5; k++) {
			for (int j = k + 1; j <= 5; j++) {
				if (Integer.parseInt(raw.substring(k, k + 1)) > Integer.parseInt(raw.substring(j, j + 1))) {
					return false;
				}
			}
		}
		return true;
	}
	
}
