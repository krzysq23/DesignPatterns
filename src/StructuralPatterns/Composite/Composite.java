package StructuralPatterns.Composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Gdy musimy stworzyć strukturę w taki sposób, że obiekty w strukturze muszą być traktowane w ten sam sposób,
 * możemy zastosować kompozytowy wzór projektowy.
 * - Wzór złożony należy stosować tylko wtedy, gdy grupa obiektów powinna zachowywać się jak pojedynczy obiekt.
 * - Złożony wzór projektowy można wykorzystać do stworzenia struktury przypominającej drzewo.
 */
public class Composite {
    public static void main(String[] args) {
        Shape tri = new Triangle();
        Shape tri1 = new Triangle();
        Shape cir = new Circle();

        Drawing drawing = new Drawing();
        drawing.add(tri1);
        drawing.add(tri1);
        drawing.add(cir);

        drawing.draw("Red");
        drawing.clear();

        drawing.add(tri);
        drawing.add(cir);
        drawing.draw("Green");
    }
}

/* Komponent podstawowy */
interface Shape {
    public void draw(String fillColor);
}
class Triangle implements Shape {
    @Override
    public void draw(String fillColor) {
        System.out.println("Drawing Triangle with color " + fillColor);
    }
}

/* Liść wzoru konstrukcyjnego (Leaf Objects) */
class Circle implements Shape {
    @Override
    public void draw(String fillColor) {
        System.out.println("Drawing Circle with color " + fillColor);
    }
}

/* Obiekt kompozytowy */
class Drawing implements Shape{

    private List<Shape> shapes = new ArrayList<Shape>();

    @Override
    public void draw(String fillColor) {
        for(Shape sh : shapes)
        {
            sh.draw(fillColor);
        }
    }

    public void add(Shape s){
        this.shapes.add(s);
    }

    public void remove(Shape s){
        shapes.remove(s);
    }

    public void clear(){
        System.out.println("Clearing all the shapes from drawing");
        this.shapes.clear();
    }
}