import Model.AlgorithmModel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        AlgorithmModel model = new AlgorithmModel();
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < n; i++) {
            int[] arr ;
            try {
                arr = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                model.addLocation(new Point(arr[0], arr[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.execute(AlgorithmModel.NEAREST_NEIGHBOR);
        model.printWay();
//        model.printLocations();
        model.execute(AlgorithmModel.EXHAUSTIVE);
        model.printWay();
//        model.printLocations();
    }
}
