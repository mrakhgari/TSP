package View;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

class WayCardView extends JPanel {
    private Point src;
    private Point des;
    private float cost;

    WayCardView(Point src, Point des, Dimension size) {
        this.src = src;
        this.des = des;
        this.cost = (float) src.distance(des);
        createView();
        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), Constants.Way);
        titledBorder.setTitleColor(Color.WHITE);
        titledBorder.setTitleFont(new Font("B Nazanin", Font.BOLD, 15));
        setBorder(titledBorder);
//        setPreferredSize(size);
        setMinimumSize(size);
        this.setVisible(true);
    }

    private void createView() {
        setBackground(new Color(162, 202, 131));
        JLabel srcLabel = setLabel(Constants.SRC, new Color(0x3576A5));
        JLabel srcCity = setLabel(pointToString(this.src));
        JLabel desLabel = setLabel(Constants.DES);
        JLabel desCity = setLabel(pointToString(this.des));
        JLabel costLabel = setLabel(Constants.COST);
        JLabel costText = setLabel(String.valueOf(this.cost));
        JLabel image = new JLabel(new ImageIcon("Images/transportation.png"));

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.add(image);
        JPanel detailPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        detailPanel.add(srcLabel);
        detailPanel.add(srcCity);
        detailPanel.add(desLabel);
        detailPanel.add(desCity);
        detailPanel.add(costLabel);
        detailPanel.add(costText);
        detailPanel.setOpaque(false);
        this.add(detailPanel);
    }

    private JLabel setLabel(String text) {
        return setLabel(text, Color.WHITE);
    }

    private JLabel setLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setOpaque(false);
        label.setForeground(color);
        label.setFont(new Font("B Nazanin", Font.PLAIN, 15));
        return label;
    }

    static String pointToString(Point p) {
        return "( " + p.x + ", " + p.y + " )";
    }
}
