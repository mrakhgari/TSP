package View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class AlgorithmView extends JFrame {
    private JPanel upPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private int i = 0;

    public AlgorithmView() {
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gradientPaint = new GradientPaint(this.getX(), this.getY(), Color.BLACK,
                        this.getX() + this.getWidth(), this.getY() + this.getHeight(), Color.GRAY);
                g2.setPaint(gradientPaint);
                g2.fill(new Rectangle2D.Double(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight()));
            }
        });
        pack();
        setMinimumSize(new Dimension(1500, 820));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setResizable(false);
//        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon("images\\colporteur.png").getImage());
        setTitle(Constants.TITLE);

        upPanel = new JPanel();
        upPanel.setOpaque(false);
        upPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.add(upPanel, BorderLayout.NORTH);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);


        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JScrollPane rightScroll = new JScrollPane(rightPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightScroll.setOpaque(false);
        this.add(rightScroll, BorderLayout.EAST);

        JScrollPane leftScroll = new JScrollPane(leftPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightScroll.setOpaque(false);
        this.add(leftScroll, BorderLayout.WEST);
        this.repaint();
        this.revalidate();
    }

    public void startRender(ArrayList<Point> map, ArrayList<Point> way, double cost, long time) {
        CartesianPanel c = new CartesianPanel();
        this.add(c, BorderLayout.CENTER);
        rightPanel.removeAll();
        leftPanel.removeAll();
        upPanel.removeAll();

        for (Point point : map) {
            leftPanel.add(new CityCartView(point, new Dimension(310, 150)));
        }
//
        i = 0;
        c.drawPoint(way.get(0));
        Timer timer = new Timer(1000, e -> {
            rightPanel.add(new WayCardView(way.get(i), way.get(i + 1), new Dimension(310, 150)));
            c.drawPoint(way.get(i + 1));
            i++;
            if (i >= way.size() - 1) {
                Timer t = (Timer) e.getSource();
                t.stop();
                c.drawPoint(way.get(0));
                JLabel totalCostLabel = setLabel(Constants.TOTAL_COST, Color.WHITE);
                upPanel.add(totalCostLabel);
                JLabel totalCostText = setLabel(String.valueOf(cost), Color.BLACK);
                upPanel.add(totalCostText);
                JLabel totalTimeLabel = setLabel(Constants.TOTAL_TIME, Color.WHITE);
                upPanel.add(totalTimeLabel);
                JLabel totalTimeText = setLabel(time + Constants.NANO, Color.BLACK);
                upPanel.add(totalTimeText);
            }
            this.repaint();
            this.revalidate();
        });
        timer.setRepeats(true);
        timer.start();

    }


    private JLabel setLabel(String text, Color c) {
        JLabel label = new JLabel(text);
        label.setOpaque(false);
        label.setFont(new Font("B Nazanin", Font.PLAIN, 15));
        label.setForeground(c);
        return label;
    }
}
