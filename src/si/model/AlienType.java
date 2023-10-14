package si.model;

public enum AlienType {
	A(9, 7, 20), B(8, 7,15), C(10, 8, 10);
	private int width;
	private int height;
	private int score;

	private AlienType(int w, int h, int s) {
		width = w;
		height = h;
		score = s;
	}

	public int getWidth() {
		return width;
	}

	public int getScore() {
		return score;
	}

	public int getHeight() {return height; }
}
