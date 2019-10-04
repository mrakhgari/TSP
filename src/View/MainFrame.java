package View;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class MainFrame extends JFrame {
    private Image img = new ImageIcon("images\\colporteur.png").getImage();
    private JPanel rightPanel;
    private File mapFile;
    private JButton okButton;
    private JCheckBox isRandom;

    // return map
    public File getMap() {
        return mapFile;
    }

    private JRadioButton nearestMethod;
    private JRadioButton exhaustiveMethod;

    public MainFrame(String title, String header) {
        // paint background
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint blackToGray = new GradientPaint(this.getX(), this.getY(), Color.BLACK,
                        this.getX() + this.getWidth(), this.getY() + this.getHeight(), Color.LIGHT_GRAY);
                g2.setPaint(blackToGray);
                g2.fill(new Rectangle2D.Double(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight()));
                g.drawImage(img, 0, 0, null);
            }
        });
        pack();
        // change size
        setSize(img.getWidth(this) * 4, (int) (img.getHeight(this) * 1.1));
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        addHeader(header);
        addOkButton();
        addMidPanel();
        setIconImage(img);
        setTitle(title);
        this.repaint();
        this.revalidate();
    }

    private void addMidPanel() {
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        midPanel.setOpaque(false);
        midPanel.setBorder(new CompoundBorder(midPanel.getBorder(), new EmptyBorder(0, 0, 0, this.getWidth() / 7)));
        midPanel.add(addRadioButtons(midPanel));
        midPanel.add(addMapSelector(midPanel));
        rightPanel.add(midPanel, BorderLayout.CENTER);
    }

    private JPanel addMapSelector(JPanel midPanel) {
        JPanel pointsDG = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pointsDG.setOpaque(false);
        pointsDG.setBorder(new CompoundBorder(pointsDG.getBorder(), new EmptyBorder(0, 0, 0, this.getWidth() / 5)));


        // label that shows "select the map"
        JLabel selectMap = new JLabel(Constants.SELECT_MAP);
        selectMap.setFont(new Font("B Nazanin", Font.BOLD, 15));
        selectMap.setForeground(Color.WHITE);
        selectMap.setBorder(new CompoundBorder(selectMap.getBorder(), new EmptyBorder(0, (int) (this.getWidth() / 3.5), 0, 0)));

        // a checkbox that shows the map is random or not
        isRandom = new JCheckBox(Constants.RANDOM);
        isRandom.setForeground(Color.WHITE);
        isRandom.setSelected(true);
        isRandom.setOpaque(false);
        isRandom.setFont(new Font("B Nazanin", Font.BOLD, 15));

        // a button for select map from computer
        JButton selectMapBtn = new JButton(Constants.SELECT_MAP);
        selectMapBtn.setEnabled(false);
        selectMapBtn.setOpaque(false);
        selectMapBtn.setFont(new Font("B Nazanin", Font.BOLD, 13));

        // a label that shows name of map
        JLabel map = new JLabel();
        map.setOpaque(false);
        map.setFont(new Font("Arial", Font.ITALIC, 13));

        // add Event Listener to checkbox for change type of map
        isRandom.addActionListener(e -> {
            selectMapBtn.setEnabled(!selectMapBtn.isEnabled());
            if (selectMapBtn.isEnabled()) {
                map.setForeground(Color.BLACK);
                mapFile = null;
            } else {
                map.setForeground(Color.GRAY);
            }
        });

        // add event listener to map chooser button
        selectMapBtn.addActionListener(e -> {
            JFileChooser mapChooser = new JFileChooser("Points/");
            mapChooser.setFileFilter(new FileFilter() {
                // for handling user choose text file
                @Override
                public boolean accept(File f) {
                    return f.getName().toUpperCase().contains(".TXT") || !f.getName().contains(".");
                }

                @Override
                public String getDescription() {
                    return ".txt files";
                }
            });

            if (mapChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                mapFile = mapChooser.getSelectedFile();
                map.setText(mapFile.getName());
            }
        });

        midPanel.add(selectMap);
        pointsDG.add(isRandom);
        pointsDG.add(selectMapBtn);
        pointsDG.add(map);

        return pointsDG;
    }

    private JPanel addRadioButtons(JPanel midPanel) {
        JPanel radioDG = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 30));
        radioDG.setOpaque(false);

        JLabel methods = new JLabel(Constants.METHODS);
        methods.setBorder(new CompoundBorder(methods.getBorder(), new EmptyBorder(0, this.getWidth() / 4, 0, 0)));
        methods.setFont(new Font("B Nazanin", Font.BOLD, 15));
        methods.setForeground(Color.WHITE);
        midPanel.add(methods);

        ButtonGroup buttonGroup = new ButtonGroup();

        nearestMethod = new JRadioButton();
        exhaustiveMethod = new JRadioButton();

        nearestMethod.setOpaque(false);
        exhaustiveMethod.setOpaque(false);

        nearestMethod.setText(Constants.NEAREST_METHOD);
        exhaustiveMethod.setText(Constants.EXHAUSTIVE);

        nearestMethod.setFont(new Font("B Nazanin", Font.BOLD, 15));
        exhaustiveMethod.setFont(new Font("B Nazanin", Font.BOLD, 15));
        buttonGroup.add(nearestMethod);
        buttonGroup.add(exhaustiveMethod);

        nearestMethod.setSelected(true);

        radioDG.add(nearestMethod);
        radioDG.add(exhaustiveMethod);
        return radioDG;
    }

    private void addOkButton() {
        okButton = new JButton(new ImageIcon("Images/search.png"));
        rightPanel.add(okButton, BorderLayout.SOUTH);
        okButton.setPreferredSize(new Dimension(50, 50));
    }

    private void addHeader(String headerText) {
        rightPanel = new JPanel(new BorderLayout(100, 10));
        rightPanel.setOpaque(false);
        JLabel header = new JLabel(headerText);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("B Titr", Font.BOLD, 18));
//        System.out.println(this.getHeight()+""+this.getWidth());
        header.setBorder(new CompoundBorder(header.getBorder(), new EmptyBorder(this.getHeight() / 15, this.getWidth() / 20, this.getHeight() / 15, this.getWidth() / 4)));
        rightPanel.add(header, BorderLayout.NORTH);
        this.add(rightPanel, BorderLayout.EAST);
    }


    public void startSearchWay(ActionListener submitListener) {
        okButton.addActionListener(submitListener);
    }

    public String getMethod() {
        if (nearestMethod.isSelected())
            return nearestMethod.getText();
        else
            return exhaustiveMethod.getText();
    }

    public boolean isRandom() {
        return this.isRandom.isSelected();
    }
}
