package StructuralPatterns.Decorator;

/**
 *
 Wzorzec dekoratora służy do modyfikowania funkcjonalności obiektu w czasie wykonywania.
 Jednocześnie nie będzie to miało wpływu na inne instancje tej samej klasy,
 więc pojedynczy obiekt otrzymuje zmodyfikowane zachowanie.
 */
public class Decorator {
    public static void main(String[] args) {
        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }
}

/* Interfejs komponentowy */
interface Car {
    public void assemble();
}

/* Implementacja komponentu */
class BasicCar implements Car {

    public String basicName = "basic";

    public String getBasicName() { return basicName; }

    @Override
    public void assemble() { System.out.print("Basic Car."); }

}

/* Decorator */
class CarDecorator implements Car {

    protected Car car;

    public CarDecorator(Car c){
        this.car=c;
    }

    @Override
    public void assemble() { this.car.assemble(); }

}

/* Concrete Decorators */
class SportsCar extends CarDecorator {

    public String sportName = "super";

    public SportsCar(Car c) {
        super(c);
    }

    public String getSportName() { return sportName; }

    @Override
    public void assemble(){
        super.assemble();
        System.out.print(" Adding features of Sports Car.");
    }
}

class LuxuryCar extends CarDecorator {

    public String luxuryName = "luksus";

    public LuxuryCar(Car c) {
        super(c);
    }

    public String getLuxuryName() { return luxuryName; }

    @Override
    public void assemble(){
        super.assemble();
        System.out.print(" Adding features of Luxury Car.");
    }
}


