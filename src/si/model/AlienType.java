package si.model;

public enum AlienType {
	A(9, 7, 500, 1), B(4, 6,1000, 2);
	private final int width;
	private final int height;
	private final int score;
	private final int speed;

	// for enum's constructor, private is default
	AlienType(int w, int h, int s, int sp) {
		width = w;
		height = h;
		score = s;
		speed = sp;
	}

	public int getWidth() {
		return width;
	}

	public int getScore() {
		return score;
	}

	public int getHeight() {return height; }

	public int getSpeed() {return speed; }
}
