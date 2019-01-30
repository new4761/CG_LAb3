import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.*;

class Lab3_60050143 extends JPanel {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        Lab3_60050143 m = new Lab3_60050143();
        JFrame f = new JFrame();
        f.add(m);
        f.setTitle("Lab3");
        // f.setBackground(Color.WHITE);
        f.setSize(600, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    public void paintComponent(Graphics g) {
        BufferedImage buffer = new BufferedImage(601, 601, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = buffer.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 600, 600);
        g2.setColor(Color.BLACK);
        int xPoly[] = { 150, 250, 325, 375, 400, 275, 100 };
        int yPoly[] = { 150, 100, 125, 225, 325, 375, 300 };
        Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
        g2.drawPolygon(poly);
        Point pT = new Point(200, 150);

        buffer = FLoodFill(g, buffer, pT, getCurrent_Color(buffer, pT), Color.GREEN);
       g.drawImage(buffer, 0, 0, null);
    }

    public BufferedImage FLoodFill(Graphics g, BufferedImage buffer, Point pT, Color target_c, Color replace_c) {

        Queue<Point> n = new LinkedList<Point>();
   
        setColor(buffer, pT, replace_c);
        n.add(new Point(pT));

        while (!(n.isEmpty())) {
            Point current = n.remove();
            Point target_Point;
            if (getCurrent_Color(buffer, current.x, current.y + 1).equals(target_c)) {
                target_Point = new Point(current.x, current.y + 1);


                setColor(buffer, target_Point, replace_c);
                n.add(target_Point);
            }
            if (getCurrent_Color(buffer, current.x, current.y - 1).equals(target_c)) {
                target_Point = new Point(current.x, current.y - 1);

                setColor(buffer, target_Point, replace_c);
                n.add(target_Point);

            }
            if (getCurrent_Color(buffer, current.x - 1, current.y).equals(target_c)) {
                target_Point = new Point(current.x - 1, current.y);


                setColor(buffer, target_Point, replace_c);

                n.add(target_Point);
            }
            if (getCurrent_Color(buffer, current.x + 1, current.y).equals(target_c)) {
                target_Point = new Point(current.x + 1, current.y);
                setColor(buffer, target_Point, replace_c);
                n.add(target_Point);
            }

        }
        return buffer;
    }

    // draw dot Graphics
    public void plot(Graphics g, Color c, Point pT) {

        g.setColor(c);
        g.fillRect(pT.x, pT.y, 1, 1);

    }

    // draw dot with size
    public void plot(Graphics g, Color c, Point pT, int sizeX, int sizeY) {
        g.setColor(c);
        g.fillRect(pT.x, pT.y, sizeX, sizeY);

    }

    // setCurrent Color
    public Color getCurrent_Color(BufferedImage g, Point pT) {
        int rgba = g.getRGB(pT.x, pT.y);
        Color col = new Color(rgba, true);
        return col;

    }

    public void setColor(BufferedImage g, Point pT, Color replace_c) {
        int rgba = replace_c.getRGB();
        g.setRGB(pT.x, pT.y, rgba);

    }

    public Color getCurrent_Color(BufferedImage g, int x, int y) {
        int rgba = g.getRGB(x, y);
        Color col = new Color(rgba, true);
        return col;

    }
}
