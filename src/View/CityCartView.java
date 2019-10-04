package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

class CityCartView extends JPanel {
    private Point city;

    CityCartView(Point city, Dimension size) {
        this.city = city;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        initialView();
        setMinimumSize(size);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), Constants.CITY);
        titledBorder.setTitleColor(Color.WHITE);

        titledBorder.setTitleFont(new Font("B Nazanin", Font.BOLD, 15));
        setBorder(titledBorder);
    }

    private void initialView() {
        JLabel image = new JLabel(new ImageIcon("Images/cityscape.png"));
        image.setBorder(new CompoundBorder(image.getBorder(), new EmptyBorder(10, 75, 10, 75)));
        this.add(image);
        JLabel cityLabel = new JLabel(WayCardView.pointToString(this.city));
        cityLabel.setBorder(new CompoundBorder(cityLabel.getBorder(), new EmptyBorder(10, 83, 10, 83)));
        cityLabel.setForeground(Color.WHITE);
        cityLabel.setOpaque(false);
        this.add(cityLabel);
    }
}
