package com.atarhely.advent;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Value;

public class Advent2019Day6 {
	private static String INPUT;
	private static List<Orbit> orbits;
	
	public static void main(String[] args) throws IOException {
		System.out.println(FileSystems.getDefault().getPath(".").toAbsolutePath());
		INPUT = Files.readString(Paths.get("./advent/src/main/resources/input-day6.txt"));
		
		orbits = Arrays.stream(INPUT.split("\n"))
				.map(row -> {
					String[] parts = row.split("\\)");
					return new Orbit(parts[0], parts[1]);
				})
				.collect(Collectors.toList());
		
		Advent2019Day6 advent2019Day6 = new Advent2019Day6();
		int sum = 0;
		for (Orbit orbit : orbits) {
			sum = sum + advent2019Day6.countIndirect(orbit, orbits);
		}
		System.out.println(sum);
	}
	
	public int countIndirect(Orbit orbit, List<Orbit> orbits) {
		if (orbit.getCore().equals("COM")) {
			return 1;
		}
		
		Orbit parentOrbit = orbits.stream().filter(o -> o.getOrbiting().equals(orbit.getCore()))
				.findFirst()
				.get();
		
		return 1 + countIndirect(parentOrbit, orbits);
	}
	
	public List<Orbit> getCommonAnchestors(Orbit orbit1, Orbit orbit2, List<Orbit> orbits) {
		List<Orbit> common = new ArrayList<>();
		List<Orbit> orbit1Anchestors = getAnchestors(orbit1, orbits);
		List<Orbit> orbit2Anchestors = getAnchestors(orbit2, orbits);
		
		for(Orbit o : orbit1Anchestors) {
			if (orbit2Anchestors.contains(o)) common.add(o);
		}
		
		return common;
	}
	
	public List<Orbit> getAnchestors(Orbit orbit, List<Orbit> orbits) {
		Optional<Orbit> parentOrbitOptional = orbits.stream().filter(o -> o.getOrbiting().equals(orbit.getCore()))
				.findFirst();
		
		if (parentOrbitOptional.isEmpty()) return new ArrayList<>();
		
		;
		Orbit parentOrbit = parentOrbitOptional.get();
		List<Orbit> anchestors = getAnchestors(parentOrbit, orbits);
		anchestors.add(parentOrbit);
		
		return anchestors;
	}
	
	@Value
	public static class Orbit {
		private String core;
		private String orbiting;
	}
}
