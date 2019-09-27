package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


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
     *
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

    /**
     * a method to find a way with exhaustive algorithm.
     */
    private void exhaustiveMethod() {
        ArrayList<ArrayList<Point>> permute = findPermute(locations.subList(1,locations.size()));
        float distance = Float.MAX_VALUE;
        for (ArrayList<Point> a : permute) {
            float temp = calculate_path_cost(a, locations.get(0));
            if (temp <= distance) {
                distance = temp;
                way = a;
                way.add(locations.get(0));
                way.add(0, locations.get(0));
            }
        }
        cost += distance;
    }

    /**
     * a method to calculate difference of points in the way.
     * @param arrayList is an array list that contains points.
     * @return a float digit that shows length of way.
     */
    private float calculate_path_cost(ArrayList<Point> arrayList,Point start) {
        float dis = (float) Point.distance(start.x, start.y , arrayList.get(0).x,arrayList.get(0).y);
        for (int i = 0; i < arrayList.size() - 1; i++)
            dis += Point.distance(arrayList.get(i).x, arrayList.get(i).y, arrayList.get(i + 1).x, arrayList.get(i + 1).y);
        dis += Point.distance(arrayList.get(arrayList.size() - 1).x, arrayList.get(arrayList.size() - 1).y, start.x, start.y);
        return dis;
    }

    public ArrayList<Point> getWay() {
        return way;
    }

    public float getCost() {
        return cost;
    }

    /**
     * a method that find nearest neighbor of a point.
     * @param src start point.
     * @return nearest neighbor.
     */
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

    /**
     * a method to find a way with nearest neighbor algorithm.
     * @param start is start point.
     */
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

    public ArrayList<Point> getLocations() {
        return locations;
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

    public void printP(ArrayList<ArrayList<Point>> result) {
        for (ArrayList a : result)
            System.out.println(a);
    }

    /**
     * a method for find all of permute of a arrayList.
     * @param points arrayList.
     * @return list of all permute of a arrayList.
     */
    private ArrayList<ArrayList<Point>> findPermute(List<Point> points) {
        ArrayList<ArrayList<Point>> result = new ArrayList<>();
        //start from an empty list
        result.add(new ArrayList<>());
        for (Point location : points) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Point>> current = new ArrayList<>();

            for (ArrayList<Point> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size() + 1; j++) {
                    // + add num[i] to different locations
                    l.add(j, location);

                    ArrayList<Point> temp = new ArrayList<>(l);
                    current.add(temp);

                    // - remove num[i] add
                    l.remove(j);
                }
            }

            result = new ArrayList<>(current);
        }
        return result;
    }

}