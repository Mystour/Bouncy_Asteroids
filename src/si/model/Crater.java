package si.model;

import javafx.scene.paint.RadialGradient;

public class Crater {
    private RadialGradient gradient;
    private double[] x_coords_detail;
    private double[] y_coords_detail;
    private int numVertices;

    public Crater(RadialGradient gradient, double[] x_coords_detail, double[] y_coords_detail, int numVertices) {
        this.gradient = gradient;
        this.x_coords_detail = x_coords_detail;
        this.y_coords_detail = y_coords_detail;
        this.numVertices = numVertices;
    }
    public void setGradient(RadialGradient gradient) { this.gradient = gradient; }
    public double[] getX_coords_detail() {
        return x_coords_detail;
    }

    public double[] getY_coords_detail() {
        return y_coords_detail;
    }

    public int getNumVertices() {
        return numVertices;
    }
}
