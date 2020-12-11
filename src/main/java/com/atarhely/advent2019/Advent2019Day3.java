package com.atarhely.advent2019;

import java.util.Optional;

import lombok.Value;

public class Advent2019Day3 {
	
	public Line getLine(Point origo, String command) {
		if (command.startsWith("L")) {
			int deltaX = Integer.parseInt(command.substring(1));
			return new Line(origo, new Point(origo.getX() - deltaX, origo.getY()));
		}
		if (command.startsWith("R")) {
			int deltaX = Integer.parseInt(command.substring(1));
			return new Line(origo, new Point(origo.getX() + deltaX, origo.getY()));
		}
		if (command.startsWith("U")) {
			int deltaY = Integer.parseInt(command.substring(1));
			return new Line(origo, new Point(origo.getX(), origo.getY() - deltaY));
		}
		if (command.startsWith("D")) {
			int deltaY = Integer.parseInt(command.substring(1));
			return new Line(origo, new Point(origo.getX(), origo.getY() + deltaY));
		}
		return null;
	}
	
	public Optional<Point> getIntersection(Line l1, Line l2) {
		// https://stackoverflow.com/questions/12612407/linear-equation-java
		double m1 = ((double)(l1.p2.y-l1.p1.y)) / ((double)(l1.p2.x-l1.p1.x));
		double b1 = (double) l1.p1.y - (m1 * (double) l1.p1.x);
		
		double m2 = ((double)(l2.p2.y-l2.p1.y)) / ((double)(l2.p2.x-l2.p1.x));
		double b2 = (double) l2.p1.y - (m2 * (double) l2.p1.x);
		
		return calculateIntersectionPoint(m1, b1, m2, b2);
	}
	
	public Optional<Point> calculateIntersectionPoint(double m1, double b1, double m2, double b2) {
		if (m1 == m2) {
			return Optional.empty();
		}
		
		double x = (b2 - b1) / (m1 - m2);
		double y = m1 * x + b1;
		
		Point point = new Point((int) x,(int)  y);
		return Optional.of(point);
	}
	
	@Value
	public static class Point {
		private int x;
		private int y;
	}
	
	@Value
	public static class Line {
		private Point p1;
		private Point p2;
	}
}
