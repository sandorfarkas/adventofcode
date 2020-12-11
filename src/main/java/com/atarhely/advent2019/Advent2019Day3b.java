package com.atarhely.advent2019;

import java.util.ArrayList;
import java.util.List;

import lombok.Value;

public class Advent2019Day3b {

	public static void main(String[] args) {
		Advent2019Day3b advent2019Day3 = new Advent2019Day3b();
		List<Line> lines1 = advent2019Day3.getLines("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
		List<Line> lines2 = advent2019Day3.getLines("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
		List<Point> intersections = advent2019Day3.getIntersections(lines1, lines2);
		System.out.println(intersections.stream()
				.mapToInt(p -> Math.abs(p.x) + Math.abs(p.y))
				.min().getAsInt());
	}

	private List<Point> getIntersections(List<Line> lines1, List<Line> lines2) {
		List<Point> intersections = new ArrayList<>();
		for (Line line1 : lines1) {
			for (Line line2 : lines2) {
				List<Point> intersects = getIntersection(line1, line2);
				if (intersects.size() != 0) {
					intersections.addAll(intersects);
				}
			}
		}
		return intersections;
	}

	private List<Line> getLines(String raw) {
		List<Line> lines = new ArrayList<>();
		String[] split = raw.split(",");
		Point o = new Point(0,0);
		for (String s : split) {
			Line l = getLine(o, s);
			lines.add(l);
			o = l.getP2();
		}
		return lines;
	}

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

	public List<Point> getIntersection(Line l1, Line l2) {
		Point p1 = l1.getP1();
		Point p2 = l1.getP2();
		Point p3 = l2.getP1();
		Point p4 = l2.getP2();

		boolean l1Horizontal = (p1.y == p2.y);
		boolean l2Horizontal = (p3.y == p4.y);

		if (l1Horizontal == l2Horizontal) return List.of();

		List<Point> l1Points = getPointsOfLine(l1, l1Horizontal);
		List<Point> l2Points = getPointsOfLine(l2, l2Horizontal);

		List<Point> intersections = new ArrayList<>();

		for (Point point:l1Points) {
			if (l2Points.contains(point) && !point.equals(new Point(0, 0))) intersections.add(point);
		}

		return intersections;
	}

	private List<Point> getPointsOfLine(Line line, boolean horizontal) {
		List<Point> points = new ArrayList<>();
		if (horizontal) {
			for (int i=line.p1.x; i<=line.p2.x;i++) {
				points.add(new Point(i, line.p1.y));
			}
		} else {
			for (int i=line.p1.y; i<=line.p2.y;i++) {
				points.add(new Point(line.p1.x, i));
			}
		}
		return points;
	}

//	public Point getIntersectionWorking(Line l1, Line l2) {
//		Point A = l1.getP1();
//		Point B = l1.getP2();
//		Point C = l2.getP1();
//		Point D = l2.getP2();
//
//		// Line AB represented as a1x + b1y = c1
//		double a1 = B.y - A.y;
//		double b1 = A.x - B.x;
//		double c1 = a1*(A.x) + b1*(A.y);
//
//		// Line CD represented as a2x + b2y = c2
//		double a2 = D.y - C.y;
//		double b2 = C.x - D.x;
//		double c2 = a2*(C.x)+ b2*(C.y);
//
//		double determinant = a1*b2 - a2*b1;
//
//		if (determinant == 0)
//		{
//			// The lines are parallel. This is simplified
//			// by returning a pair of FLT_MAX
//			return new Point(Double.MAX_VALUE, Double.MAX_VALUE);
//		}
//		else
//		{
//			double x = (b2*c1 - b1*c2)/determinant;
//			double y = (a1*c2 - a2*c1)/determinant;
//			return new Point(x, y);
//		}
//	}
	
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
