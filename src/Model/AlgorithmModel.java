package Model;

import java.awt.*;
import java.util.ArrayList;


public class AlgorithmModel {
    private ArrayList<Point> locations;
    private ArrayList<Point> way;
    private ArrayList<Boolean> visited;
    private float cost;

    public static final Methods NEAREST_NEIGHBOR = Methods.NEAREST_NEIGHBOR;
    public static final Methods EXHAUSTIVE = Methods.EXHAUSTIVE;

    private enum Methods {
        NEAREST_NEIGHBOR,
        EXHAUSTIVE,
    }

    /**
     * Constructor that creates a arrayList.
     */
    public AlgorithmModel() {
        locations = new ArrayList<>();
    }

    /**
     * add point to location arrayList.
     * @param point is a object of Point Class that contains x and y of location.
     */
    public void addLocation(Point point) {
        locations.add(point);
    }

    /**
     * Start Algorithm.
     *
     * @param method shows method of answer that contains nearest neighbor and exhaustive.
     */
    public void execute(Methods method) {
        if (!locations.isEmpty()) {
            cost = 0;
            way = new ArrayList<>();
            if (method.equals(Methods.NEAREST_NEIGHBOR))
                nearestNeighborMethod(locations.get(0));
            else if (method.equals(Methods.EXHAUSTIVE))
                exhaustiveMethod();
            else
                System.out.println("CAN'T CALCULATE ...");
        }
    }

    private void exhaustiveMethod() {

    }

    public ArrayList<Point> getWay() {
        return way;
    }

    public float getCost() {
        return cost;
    }

    private Point getNearestNeighbor(Point src) {
        Point des = null;
        int index = -1;
        float distance = Float.MAX_VALUE;
        for (int i = 0; i < locations.size(); i++) {
            if (!locations.get(i).equals(src) && !visited.get(i)) {
                float tempDistance = (float) Point.distance(src.x, src.y, locations.get(i).x, locations.get(i).y);
                if (tempDistance < distance) {
                    index = i;
                    distance = tempDistance;
                    des = locations.get(i);
                }
            }
        }
        visited.set(index, true);
        way.add(des);
        cost += distance;
        return des;
    }


    public ArrayList<Point> getLocations() {
        return locations;
    }

    private void nearestNeighborMethod(Point start) {
        visited = new ArrayList<>();
        way.add(start);
        for (int i = 0; i < locations.size(); i++) {
            visited.add(false);
        }
        visited.set(0, true);
        Point nextPosition = start;
        for (int i = 0; i < locations.size() - 1; i++) {
            nextPosition = getNearestNeighbor(nextPosition);
        }
        way.add(start);
        cost += (float) Point.distance(start.x, start.y, nextPosition.x, nextPosition.y);
    }

    public void printWay() {
        System.out.println("The way is ...");
        for (int i = 0; i < way.size() - 1; i++)
            System.out.println("(" + way.get(i).x + "," + way.get(i).y + ") -> (" + way.get(i + 1).x + "," + way.get(i + 1).y + ")");
        System.out.println("The Cost Is" + cost);
    }

    public void printLocations() {
        for (Point p : locations) System.out.println("Point is (" + p.x + ", " + p.y + ")");
    }

}