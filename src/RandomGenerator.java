import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class RandomGenerator {
    private final static int LIMIT = 100;
    private int count;
    private static String FILE_DIRECTORY = "Points/";

    /**
     * a class for create a random map
     *
     * @param count number of cities
     */
    RandomGenerator(int count) {
        this.count = count;
    }

    /**
     * create random map
     * @return a array list that contains location of cities.
     */
    ArrayList<Point> generator() {
        ArrayList<Point> points = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Point p = new Point(random.nextInt(2 * LIMIT) - LIMIT, random.nextInt(2 * LIMIT) - LIMIT);
            if (points.contains(p))
                i--;
            else
                points.add(p);
        }
        return points;
    }

    /**
     *  a static method to write map in a text file.
     * @param title name of file
     * @param points array list of points that contains locations.
     * @return the file that created.
     */
    static File writeInFile(String title, ArrayList<Point> points) {
        try {
            FileWriter fileWriter = new FileWriter(new File(RandomGenerator.FILE_DIRECTORY + title));
            fileWriter.write(points.size() + "");
            fileWriter.write("\n");
            for (Point point : points) {
                fileWriter.write(point.x + " " + point.y);
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("FILE NOT FOUNDED...");
        }
        return new File(RandomGenerator.FILE_DIRECTORY + title);
    }
}
