package carcool.com.model;

import java.util.HashSet;

public class Route {

	HashSet<Point> Chemin;

	public Route() {
		super();
		Chemin = new HashSet<Point>();
	}

	public HashSet<Point> getChemin() {
		return Chemin;
	}

	public void setChemin(HashSet<Point> chemin) {
		Chemin = chemin;
	}
	
}
