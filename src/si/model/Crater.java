package si.model;

import javafx.scene.paint.RadialGradient;

public record Crater(RadialGradient gradient, double[] x_coords_detail, double[] y_coords_detail, int numVertices) {
    public RadialGradient getGradient() {
        return gradient;
    }

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
