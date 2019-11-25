package StructuralPatterns.Flyweight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Wzorzec projektu Flyweight jest używany, gdy musimy stworzyć wiele obiektów klasy.
 * Ponieważ każdy obiekt zużywa miejsce w pamięci, które może mieć kluczowe znaczenie
 * dla urządzeń o niskiej pamięci, takich jak urządzenia mobilne lub systemy wbudowane,
 * można zastosować wzorzec projektowy w celu zmniejszenia obciążenia pamięci poprzez współdzielenie obiektów.
 * Liczba obiektów tworzonych przez aplikację powinna być ogromna.
 * Tworzenie obiektów obciąża pamięć i może być również czasochłonne.
 * Właściwości obiektu można podzielić na właściwości wewnętrzne i zewnętrzne,
 * zewnętrzne właściwości obiektu powinny być zdefiniowane przez program kliencki.
 */
public class Flyweight extends JFrame {

    private static final long serialVersionUID = -1350200437285282550L;
    private final int WIDTH;
    private final int HEIGHT;

    public static void main(String[] args) {
        Flyweight drawing = new Flyweight(500,600);
    }

    private static final ShapeFactory.ShapeType shapes[] = { ShapeFactory.ShapeType.LINE,
            ShapeFactory.ShapeType.OVAL_FILL, ShapeFactory.ShapeType.OVAL_NOFILL };
    private static final Color colors[] = { Color.RED, Color.GREEN, Color.YELLOW };

    public Flyweight(int width, int height){
        this.WIDTH=width;
        this.HEIGHT=height;

        Container contentPane = getContentPane();

        JButton startButton = new JButton("Draw");
        final JPanel panel = new JPanel();

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(startButton, BorderLayout.SOUTH);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Graphics g = panel.getGraphics();
                for (int i = 0; i < 20; ++i) {
                    Shape shape = ShapeFactory.getShape(getRandomShape());
                    shape.draw(g, getRandomX(), getRandomY(), getRandomWidth(),
                            getRandomHeight(), getRandomColor());
                }
            }
        });
    }

    private ShapeFactory.ShapeType getRandomShape() {
        return shapes[(int) (Math.random() * shapes.length)];
    }

    private int getRandomX() {
        return (int) (Math.random() * WIDTH);
    }

    private int getRandomY() {
        return (int) (Math.random() * HEIGHT);
    }

    private int getRandomWidth() {
        return (int) (Math.random() * (WIDTH / 10));
    }

    private int getRandomHeight() {
        return (int) (Math.random() * (HEIGHT / 10));
    }

    private Color getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

}

interface Shape {
    public void draw(Graphics g, int x, int y, int width, int height,
                     Color color);
}

class Line implements Shape {

    public Line(){
        System.out.println("Creating Line object");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics line, int x1, int y1, int x2, int y2,
                     Color color) {
        line.setColor(color);
        line.drawLine(x1, y1, x2, y2);
    }

}

class Oval implements Shape {

    private boolean fill;

    public Oval(boolean f){
        this.fill=f;
        System.out.println("Creating Oval object with fill="+f);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics circle, int x, int y, int width, int height,
                     Color color) {
        circle.setColor(color);
        circle.drawOval(x, y, width, height);
        if(fill){
            circle.fillOval(x, y, width, height);
        }
    }

}

class ShapeFactory {

    private static final HashMap<ShapeType,Shape> shapes = new HashMap<ShapeType,Shape>();

    public static Shape getShape(ShapeType type) {
        Shape shapeImpl = shapes.get(type);

        if (shapeImpl == null) {
            if (type.equals(ShapeType.OVAL_FILL)) {
                shapeImpl = new Oval(true);
            } else if (type.equals(ShapeType.OVAL_NOFILL)) {
                shapeImpl = new Oval(false);
            } else if (type.equals(ShapeType.LINE)) {
                shapeImpl = new Line();
            }
            shapes.put(type, shapeImpl);
        }
        return shapeImpl;
    }

    public static enum ShapeType{
        OVAL_FILL,OVAL_NOFILL,LINE;
    }
}