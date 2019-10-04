import Controller.AlgorithmController;
import Model.AlgorithmModel;
import View.AlgorithmView;
import View.Constants;
import View.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Run {
    private static MainFrame startFrame;

    public static void main(String[] args) {
        startFrame = new MainFrame(Constants.TITLE, Constants.WELCOME_MESSAGE);
        startFrame.startSearchWay(new StartListener());
    }

    private static class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AlgorithmModel algorithmModel = new AlgorithmModel();
            String mapAddress = "Random.txt";
            File map;
            startFrame.setVisible(false);
            if (startFrame.isRandom()) {
                RandomGenerator randomGenerator = new RandomGenerator(10);
                map = RandomGenerator.writeInFile(mapAddress, randomGenerator.generator());
            } else
                map = startFrame.getMap();
            AlgorithmController algorithmController = new AlgorithmController(algorithmModel, new AlgorithmView(), map, startFrame.getMethod());
        }
    }
}