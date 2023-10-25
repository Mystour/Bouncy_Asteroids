package si.model;


import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Crater{
    private double detailX, detailY;
    private final double[] x_coords_detail;
    private final double[] y_coords_detail;
    private final int numVertices;
    private final int detailSize;

    public Crater(int detailSize, double detailX, double detailY, double[] x_coords_detail, double[] y_coords_detail, int numVertices) {
        this.detailSize = detailSize;
        this.detailX = detailX;
        this.detailY = detailY;
        this.x_coords_detail = x_coords_detail;
        this.y_coords_detail = y_coords_detail;
        this.numVertices = numVertices;
    }

    public void move(double cX, double cY) {
        detailX += cX;
        detailY += cY;
        for (int i = 0; i < numVertices; i++) {
            x_coords_detail[i] += cX;
            y_coords_detail[i] += cY;
        }
    }

    public double[] x_coords_detail() {
        return x_coords_detail;
    }

    public double[] y_coords_detail() {
        return y_coords_detail;
    }

    public int numVertices() {
        return numVertices;
    }

    public RadialGradient getColor() {
        return new RadialGradient(
                0, 0, detailX + detailSize / 2.0, detailY + detailSize / 2.0, detailSize / 2.0, false, CycleMethod.NO_CYCLE,

                new Stop(0.0, Color.WHITE),
                new Stop(0.8, Color.LIGHTGRAY),
                new Stop(1.0, Color.DARKGRAY)
        );
    }
}
