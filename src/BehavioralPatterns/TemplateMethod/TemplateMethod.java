package BehavioralPatterns.TemplateMethod;

/**
 * Wzorzec projektu metody szablonu służy do utworzenia kodu pośredniczącego metody
 * i odroczenia niektórych etapów implementacji do podklas.
 */
public class TemplateMethod {
    public static void main(String[] args) {
        HouseTemplate houseType = new WoodenHouse();
        houseType.buildHouse();
        System.out.println("************");
        houseType = new GlassHouse();
        houseType.buildHouse();
    }
}

abstract class HouseTemplate {

    //template method, final so subclasses can't override
    public final void buildHouse() {
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        System.out.println("House is built.");
    }

    //default implementation
    private void buildWindows() { System.out.println("Building Glass Windows"); }

    //methods to be implemented by subclasses
    public abstract void buildWalls();
    public abstract void buildPillars();

    private void buildFoundation() { System.out.println("Building foundation with cement,iron rods and sand"); }
}

class WoodenHouse extends HouseTemplate {

    @Override
    public void buildWalls() { System.out.println("Building Wooden Walls"); }

    @Override
    public void buildPillars() { System.out.println("Building Pillars with Wood coating"); }

}

class GlassHouse extends HouseTemplate {

    @Override
    public void buildWalls() { System.out.println("Building Glass Walls"); }

    @Override
    public void buildPillars() { System.out.println("Building Pillars with glass coating"); }

}