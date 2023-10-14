package si.model;

public enum PlanetType {
	A(50, 50, 3), B(30,100, 4), C(20, 200, 5);
	private int radius;
	private int score;
	private int speed;

	private PlanetType(int r, int s, int sd) {
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
