package si.model;

public enum PlanetType {
	A(50, 20), B(30,15), C(20, 10);
	private int radius;
	private int score;

	private PlanetType(int r, int s) {
		radius = r;
		score = s;
	}

	public int getRadius() { return radius; }

	public int getScore() {
		return score;
	}
}
