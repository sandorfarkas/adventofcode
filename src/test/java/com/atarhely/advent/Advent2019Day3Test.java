package com.atarhely.advent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.Optional;

import com.atarhely.advent.Advent2019Day3.Line;
import com.atarhely.advent.Advent2019Day3.Point;


class Advent2019Day3Test {
	private Advent2019Day3 advent2019Day3;
	
	@BeforeEach
	void setUp() {
		advent2019Day3 = new Advent2019Day3();
	}
	
	@Test
	void shouldReturnSection() {
		Point origo = new Point(0, 0);
		
		String command = "R13";
		assertThat(advent2019Day3.getLine(origo, command), is(new Line(origo, new Point(13, 0))));
		
		command = "L13";
		assertThat(advent2019Day3.getLine(origo, command), is(new Line(origo, new Point(-13, 0))));
		
		command = "D13";
		assertThat(advent2019Day3.getLine(origo, command), is(new Line(origo, new Point(0, 13))));
		
		command = "U13";
		assertThat(advent2019Day3.getLine(origo, command), is(new Line(origo, new Point(0, -13))));
	}
	
	@Test
	void shouldReturnnullIfNoIntersection() {
		Line l1 = new Line(new Point(0, 0), new Point(10, 0));
		Line l2 = new Line(new Point(11, 0), new Point(20, 0));
		
		assertThat(advent2019Day3.getIntersection(l1, l2), is(Optional.empty()));
	}
	
	@Test
	void shouldReturnIntersection() {
		Line l1 = new Line(new Point(0, 0), new Point(10, 0));
		Line l2 = new Line(new Point(5, -5), new Point(5, 5));
		
		assertThat(advent2019Day3.getIntersection(l1, l2).get(), is(new Point(5, 0)));
	}
}