package si.model;

public enum AlienType {
	A(9, 7, 500, 1), B(4, 6,1000, 2);
	private int width;
	private int height;
	private int score;
	private int speed;

	private AlienType(int w, int h, int s, int sp) {
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
