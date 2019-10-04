package Controller;

import Model.AlgorithmModel;
import View.AlgorithmView;
import View.Constants;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class AlgorithmController {
    private AlgorithmModel algorithmModel;
    private AlgorithmView algorithmView;
    private File map;

    public AlgorithmController(AlgorithmModel algorithmModel, AlgorithmView algorithmView, File map, String method) {
        this.algorithmModel = algorithmModel;
        this.algorithmView = algorithmView;
        this.map = map;
        System.out.println(map.getPath());
        addLocations();
        Instant start = Instant.now();
        if (method.equals(Constants.NEAREST_METHOD) ) {
            this.algorithmModel.execute(AlgorithmModel.NEAREST_NEIGHBOR);
        }
        else {
            this.algorithmModel.execute(AlgorithmModel.EXHAUSTIVE);
        }
        this.algorithmModel.printWay();
        this.algorithmModel.printLocations();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        this.algorithmView.setVisible(true);
        System.out.println("Time taken: "+ timeElapsed.toNanos() +" milliseconds");
        this.algorithmView.startRender(this.algorithmModel.getLocations(),this.algorithmModel.getWay(), this.algorithmModel.getCost(),timeElapsed.toNanos());
    }

    private void addLocations() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(map.getPath()));
            int count = Integer.parseInt(reader.readLine());
            for (int i = 0; i < count; i++) {
                String[] point = reader.readLine().split(" ");
                this.algorithmModel.addLocation(new Point(Integer.parseInt(point[0]), Integer.parseInt(point[1])));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
