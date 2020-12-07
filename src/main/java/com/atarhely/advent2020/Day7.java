package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import lombok.Data;

public class Day7 {
	private static final List<Rule> rules = new ArrayList<>();
	private static final String MY_BAG = "shiny gold";
	
	public Day7() throws IOException {
		Scanner scanner = new Scanner(Paths.get("./advent/src/main/resources/2020/input-day7.txt"));
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Rule rule = new Rule();
			String[] parts = line.split(" bags contain ");
			rule.setBagColor(parts[0]);
			rule.setCanContain(new HashMap<>());
			Arrays.stream(parts[1].split(", ")).forEach(contain -> {
				if (contain.contains("no other bags.")) {
					return;
				}
				String raw = contain
						.replace("bags.", "")
						.replace("bags", "")
						.replace("bag.", "")
						.replace("bag", "")
						.trim();
				rule.getCanContain().put(raw.substring(2), Integer.parseInt(raw.substring(0, 1)));
			});
			rules.add(rule);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Day7 day7 = new Day7();
		System.out.println("Result 1: " + day7.calculateFirst());
		System.out.println("Result 2: " + day7.calculateSecond());
	}
	
	private int calculateFirst() {
		Set<String> allBags = new HashSet<>();
		Set<String> bagsToCheck = new HashSet<>();
		bagsToCheck.add(MY_BAG);
		while (!bagsToCheck.isEmpty()) {
			String bagToCheck = bagsToCheck.iterator().next();
			rules.forEach(rule -> {
				if (rule.getCanContain().containsKey(bagToCheck)) {
					bagsToCheck.add(rule.bagColor);
					allBags.add(rule.bagColor);
				}
			});
			bagsToCheck.remove(bagToCheck);
		}
		return allBags.size();
	}
	
	private int calculateSecond() {
		return countBagsIn(MY_BAG);
	}
	
	private int countBagsIn(String myBag) {
		Map<String, Integer> canContain = rules.stream()
				.filter(rule -> rule.getBagColor().equals(myBag))
				.map(Rule::getCanContain)
				.findFirst().orElseThrow();
		if (canContain.isEmpty()) { return 0; }
		
		int sum = canContain.values().stream().reduce(Integer::sum).orElseThrow();
		sum += canContain.entrySet().stream().mapToInt(entry -> countBagsIn(entry.getKey()) * entry.getValue()).sum();
		return sum;
	}
	
	@Data
	private class Rule {
		private String bagColor;
		private Map<String, Integer> canContain;
	}
	
}
