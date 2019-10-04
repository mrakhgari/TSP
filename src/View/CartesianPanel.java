package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class CartesianPanel extends JPanel {
    private ArrayList<Point> points = new ArrayList<>();
    private int xLength;
    private int yLength;
    // x-axis coord constants
    private static int X_AXIS_FIRST_X_COORD = 50;
    private static int X_AXIS_SECOND_X_COORD = 600;

    // y-axis coord constants
    private static int Y_AXIS_FIRST_Y_COORD = 50;
    private static int Y_AXIS_SECOND_Y_COORD = 600;

    //arrows of axis are represented with "hipotenuse" of
    //triangle
    // now we are define length of cathetas of that triangle
    private static final int FIRST_LENGHT = 10;
    private static final int SECOND_LENGHT = 5;

    // size of start coordinate lenght
    private static final int ORIGIN_COORDINATE_LENGHT = 6;

    // distance of coordinate strings from axis
    private static final int AXIS_STRING_DISTANCE = 20;


    public void paintComponent(Graphics g) {
        int x_AXIS_Y_COORD = this.getHeight() / 2;

        int y_AXIS_X_COORD = this.getWidth() / 2;

        X_AXIS_FIRST_X_COORD = (int) (this.getWidth() * 0.1);
        X_AXIS_SECOND_X_COORD = (int) (this.getWidth() * 0.9);

        Y_AXIS_FIRST_Y_COORD = (int) (this.getHeight() * 0.1);
        Y_AXIS_SECOND_Y_COORD = (int) (this.getHeight() * 0.9);

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // x-axis
        g2.drawLine(X_AXIS_FIRST_X_COORD, x_AXIS_Y_COORD,
                X_AXIS_SECOND_X_COORD, x_AXIS_Y_COORD);
        // y-axis
        g2.drawLine(y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD,
                y_AXIS_X_COORD, Y_AXIS_SECOND_Y_COORD);

        // x-axis arrow
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT,
                x_AXIS_Y_COORD - SECOND_LENGHT,
                X_AXIS_SECOND_X_COORD, x_AXIS_Y_COORD);
        g2.drawLine(X_AXIS_SECOND_X_COORD - FIRST_LENGHT,
                x_AXIS_Y_COORD + SECOND_LENGHT,
                X_AXIS_SECOND_X_COORD, x_AXIS_Y_COORD);

        // y-axis arrow
        g2.drawLine(y_AXIS_X_COORD - SECOND_LENGHT,
                Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
                y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);
        g2.drawLine(y_AXIS_X_COORD + SECOND_LENGHT,
                Y_AXIS_FIRST_Y_COORD + FIRST_LENGHT,
                y_AXIS_X_COORD, Y_AXIS_FIRST_Y_COORD);

        // draw origin Point
        g2.fillOval((X_AXIS_FIRST_X_COORD + X_AXIS_SECOND_X_COORD) / 2 - (ORIGIN_COORDINATE_LENGHT / 2),
                (Y_AXIS_SECOND_Y_COORD + Y_AXIS_FIRST_Y_COORD) / 2 - (ORIGIN_COORDINATE_LENGHT / 2),
                ORIGIN_COORDINATE_LENGHT, ORIGIN_COORDINATE_LENGHT);

        // draw text "X" and draw text "Y"
        g2.drawString("X", X_AXIS_SECOND_X_COORD - AXIS_STRING_DISTANCE / 2,
                x_AXIS_Y_COORD + AXIS_STRING_DISTANCE);
        g2.drawString("Y", y_AXIS_X_COORD + AXIS_STRING_DISTANCE,
                Y_AXIS_FIRST_Y_COORD + AXIS_STRING_DISTANCE / 2);

        // numerate axis
        int xCoordNumbers = 100;
        int yCoordNumbers = 100;
        xLength = (X_AXIS_SECOND_X_COORD - X_AXIS_FIRST_X_COORD) / xCoordNumbers;
        yLength = (Y_AXIS_SECOND_Y_COORD - Y_AXIS_FIRST_Y_COORD) / yCoordNumbers;

        // draw x-axis numbers
        for (int i = 0; i < xCoordNumbers; i += 2) {
            g2.drawLine(X_AXIS_FIRST_X_COORD + (i * xLength),
                    x_AXIS_Y_COORD - SECOND_LENGHT,
                    X_AXIS_FIRST_X_COORD + (i * xLength),
                    x_AXIS_Y_COORD + SECOND_LENGHT);
        }

        //draw y-axis numbers
        for (int i = 0; i < yCoordNumbers; i += 2) {
            g2.drawLine(y_AXIS_X_COORD - SECOND_LENGHT,
                    Y_AXIS_SECOND_Y_COORD - (i * yLength),
                    y_AXIS_X_COORD + SECOND_LENGHT,
                    Y_AXIS_SECOND_Y_COORD - (i * yLength));
//            g2.drawString(Integer.toString(i),
//                    Y_AXIS_X_COORD - AXIS_STRING_DISTANCE,
//                    Y_AXIS_SECOND_Y_COORD - (i * yLength));
        }

        for (Point point : points) {
            drawPointOnPanel(point, g2);
        }
        for (int i = 0; i < points.size() - 1; i++) {
            drawLineOnPanel(points.get(i), points.get(i + 1), g2);
        }
    }

    void drawPoint(Point point) {
        points.add(point);
        repaint();
    }

    private void drawPointOnPanel(Point point, Graphics2D g) {
        final int pointDiameter = 5;
        final int x = (X_AXIS_FIRST_X_COORD + X_AXIS_SECOND_X_COORD) / 2 + (point.x * (xLength / 2)) - pointDiameter / 2;
        final int y = (Y_AXIS_FIRST_Y_COORD + Y_AXIS_SECOND_Y_COORD) / 2 - (point.y * (yLength / 2)) - pointDiameter / 2;
        g.fillOval(x, y, pointDiameter, pointDiameter);
        g.drawString("( " + point.x + "," + point.y + " )", x - AXIS_STRING_DISTANCE, y);

    }

    private void drawLineOnPanel(Point src, Point des, Graphics2D g) {
        int x1 = (X_AXIS_FIRST_X_COORD + X_AXIS_SECOND_X_COORD) / 2 + (src.x * (xLength / 2));
        int x2 = (X_AXIS_FIRST_X_COORD + X_AXIS_SECOND_X_COORD) / 2 + (des.x * (xLength / 2));
        int y1 = (Y_AXIS_FIRST_Y_COORD + Y_AXIS_SECOND_Y_COORD) / 2 - (src.y * (yLength / 2));
        int y2 = (Y_AXIS_FIRST_Y_COORD + Y_AXIS_SECOND_Y_COORD) / 2 - (des.y * (yLength / 2));
        g.drawLine(x1, y1, x2, y2);
    }
}

