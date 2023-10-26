package si.model;

public enum AlienType {
	A(9, 7, 500, 1, 200), B(4, 6,1000, 2, 100);
	private final int width;
	private final int height;
	private final int score;
	private final int speed;
	private final int fireRate;

	// for enum's constructor, private is default
	AlienType(int w, int h, int s, int sp, int fr) {
		width = w;
		height = h;
		score = s;
		speed = sp;
		fireRate = fr;
	}

	public int getWidth() {
		return width;
	}

	public int getScore() {
		return score;
	}

	public int getHeight() { return height; }

	public int getSpeed() { return speed; }

	public int getFireRate() { return fireRate; }
}
