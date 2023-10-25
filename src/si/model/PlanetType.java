package si.model;

public enum PlanetType {
	A(50, 50, 1), B(30,100, 2), C(20, 200, 3);
	private final int radius;
	private final int score;
	private final int speed;


	// for enum's constructor, private is default
	PlanetType(int r, int s, int sd) {
		radius = r;
		score = s;
		speed = sd;
	}

	public int getRadius() { return radius; }

	public int getSpeed() { return speed; }

	public int getScore() {
		return score;
	}
}
