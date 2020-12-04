package com.atarhely.advent2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPasswordField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public class Day4 {
	private final List<Passport> passports = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		Day4 day4 = new Day4();
		System.out.println("All passports: " + day4.passports.size());
		System.out.println("Result 1: " + day4.calculateFirst());
		System.out.println("Result 2: " + day4.calculateSecond());
	}
	
	private long calculateFirst() {
		return passports.stream().filter(Passport::isValidFirstRound).count();
	}
	
	private long calculateSecond() {
		return passports.stream().filter(Passport::isValidSecondRound).count();
	}
	
	public Day4() throws IOException {
		String raw = Files.readString(Paths.get("./advent/src/main/resources/2020/input-day4.txt"));
		String[] blocks = raw.split("\\n\\n");
		for (String block : blocks) {
			String[] parts = block.split("\\s+");
			passports.add(createPassport(parts));
		}
	}
	
	private Passport createPassport(String[] parts) {
		Passport passport = new Passport();
		Arrays.stream(parts).forEach(part -> {
			String[] keyValue = part.split(":");
			switch (keyValue[0]) {
				case "byr": passport.birthYear = keyValue[1]; break;
				case "iyr": passport.issueYear = keyValue[1]; break;
				case "eyr": passport.expirationYear = keyValue[1]; break;
				case "hgt": passport.height = keyValue[1]; break;
				case "hcl": passport.hairColor = keyValue[1]; break;
				case "ecl": passport.eyeColor = keyValue[1]; break;
				case "pid": passport.passportId = keyValue[1]; break;
				case "cid": passport.countryId = keyValue[1]; break;
			}
		});
		return passport;
	}
	
	@Data
	@NoArgsConstructor
	private class Passport {
		private String birthYear;
		private String issueYear;
		private String expirationYear;
		private String height;
		private String hairColor;
		private String eyeColor;
		private String passportId;
		private String countryId;
		
		public boolean isValidFirstRound() {
			return birthYear != null && issueYear != null && expirationYear != null && height != null
					&& hairColor != null && eyeColor != null && passportId != null;
		}
		
		public boolean isValidSecondRound() {
			boolean birthPredicate = birthYear != null && Integer.parseInt(birthYear) >= 1920 && Integer.parseInt(birthYear) <= 2002;
			boolean issueYearPredicate = issueYear != null && Integer.parseInt(issueYear) >= 2010 && Integer.parseInt(issueYear) <= 2020;
			boolean expirationPredicate = expirationYear != null && Integer.parseInt(expirationYear) >= 2020 && Integer.parseInt(expirationYear) <= 2030;
			boolean heightPredicate = height != null &&
					(
						(height.endsWith("in") && Integer.parseInt(height.substring(0, height.length() -2)) >= 59 && Integer.parseInt(height.substring(0, height.length() -2)) <= 76)
							|| (height.endsWith("cm") && Integer.parseInt(height.substring(0, height.length() -2)) >= 150 && Integer.parseInt(height.substring(0, height.length() -2)) <= 193)
					);
			boolean hairPredicate = hairColor != null && hairColor.matches("#[a-f0-9]{6}");
			boolean eyePredicate = eyeColor != null &&
					(
						eyeColor.equals("amb") || eyeColor.equals("blu") || eyeColor.equals("brn")
							|| eyeColor.equals("gry") ||eyeColor.equals("grn") ||eyeColor.equals("hzl")
							||eyeColor.equals("oth")
					);
			boolean passportPredicate = passportId != null && passportId.matches("[0-9]{9}");
			
			return birthPredicate && issueYearPredicate && expirationPredicate
			&& heightPredicate && hairPredicate && eyePredicate && passportPredicate;
		}
	}
}
