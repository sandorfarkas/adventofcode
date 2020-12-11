package com.atarhely.advent2019;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.atarhely.advent2019.Advent2019Day6.Orbit;

class Advent2019Day6Test {
	private Advent2019Day6 advent2019Day6;
	private List<Orbit> orbits;
	
	@BeforeEach
	void setUp() {
		advent2019Day6 = new Advent2019Day6();
	}
	
	@Test
	void countIndirect() {
		orbits = List.of(
				new Orbit("COM", "B"),
				new Orbit("B", "C"),
				new Orbit("C", "D"),
				new Orbit("D", "E"),
				new Orbit("E", "F"),
				new Orbit("B", "G"),
				new Orbit("G", "H"),
				new Orbit("D", "I"),
				new Orbit("E", "J"),
				new Orbit("J", "K"),
				new Orbit("K", "L")
		);
		
		int sum = 0;
		for (Orbit orbit : orbits) {
			sum = sum + advent2019Day6.countIndirect(orbit, orbits);
		}
		System.out.println(sum);
	}
	
	@Test
	void a() {
		orbits = List.of(
				new Orbit("COM", "B"),
				new Orbit("B", "C"),
				new Orbit("C", "D"),
				new Orbit("D", "E"),
				new Orbit("E", "F"),
				new Orbit("B", "G"),
				new Orbit("G", "H"),
				new Orbit("D", "I"),
				new Orbit("E", "J"),
				new Orbit("J", "K"),
				new Orbit("K", "L")
		);
		
		List<Orbit> anchestors = advent2019Day6.getAnchestors(new Orbit("K", "L"), orbits);
	}
}