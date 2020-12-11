package com.atarhely.advent2019;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class Advent2019Day4Test {
	@Test
	void increasing() {
		Advent2019Day4 advent2019Day4 = new Advent2019Day4();
		assertThat(advent2019Day4.isIncreasing(123456), is(true));
		assertThat(advent2019Day4.isIncreasing(123450), is(false));
		assertThat(advent2019Day4.isIncreasing(123455), is(true));
	}
	
	@Test
	void doubles() {
		Advent2019Day4 advent2019Day4 = new Advent2019Day4();
		
		assertThat(advent2019Day4.hasDoubles(112345), is(true));
		assertThat(advent2019Day4.hasDoubles(123454), is(false));
		assertThat(advent2019Day4.hasDoubles(102345), is(false));
		assertThat(advent2019Day4.hasDoubles(112222), is(true));
	}
	
	@Test
	void a() {
		Advent2019Day4 advent2019Day4 = new Advent2019Day4();
		
		assertThat(advent2019Day4.hasDoublePairsOnly(112345), is(true));
		assertThat(advent2019Day4.hasDoublePairsOnly(111345), is(false));
		assertThat(advent2019Day4.hasDoublePairsOnly(345111), is(false));
		assertThat(advent2019Day4.hasDoublePairsOnly(311133), is(true));
		assertThat(advent2019Day4.hasDoublePairsOnly(341111), is(false));
		assertThat(advent2019Day4.hasDoublePairsOnly(111111), is(false));
		assertThat(advent2019Day4.hasDoublePairsOnly(111133), is(true));
		assertThat(advent2019Day4.hasDoublePairsOnly(112233), is(true));
		assertThat(advent2019Day4.hasDoublePairsOnly(111113), is(false));
		assertThat(advent2019Day4.hasDoublePairsOnly(311111), is(false));
		assertThat(advent2019Day4.hasDoublePairsOnly(123444), is(false));
		assertThat(advent2019Day4.hasDoublePairsOnly(111122), is(true));
	}
	
	@Test
	void triples() {
		Advent2019Day4 advent2019Day4 = new Advent2019Day4();
		
		assertThat(advent2019Day4.hasTriples(112345), is(false));
		assertThat(advent2019Day4.hasTriples(111454), is(true));
		assertThat(advent2019Day4.hasTriples(102333), is(true));
		
	}
	
	@Test
	void four() {
		Advent2019Day4 advent2019Day4 = new Advent2019Day4();
		
		assertThat(advent2019Day4.hasFour(111145), is(true));
		assertThat(advent2019Day4.hasFour(111114), is(true));
		assertThat(advent2019Day4.hasFour(331111), is(true));
		assertThat(advent2019Day4.hasFour(102333), is(false));
		assertThat(advent2019Day4.hasFour(102333), is(false));
	}
	
	@Test
	void five() {
		Advent2019Day4 advent2019Day4 = new Advent2019Day4();
		
		assertThat(advent2019Day4.hasFive(211115), is(false));
		assertThat(advent2019Day4.hasFive(111114), is(true));
		assertThat(advent2019Day4.hasFive(211111), is(true));
	}
	
	@Test
	void six() {
		Advent2019Day4 advent2019Day4 = new Advent2019Day4();
		
		assertThat(advent2019Day4.hasSix(111111), is(true));
		assertThat(advent2019Day4.hasSix(888888), is(true));
		assertThat(advent2019Day4.hasSix(111114), is(false));
		assertThat(advent2019Day4.hasSix(211111), is(false));
	}
}