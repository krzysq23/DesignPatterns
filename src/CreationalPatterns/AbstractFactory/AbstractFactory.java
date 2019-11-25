package CreationalPatterns.AbstractFactory;

/**
 * Zadaniem fabryki abstrakcyjnej jest określenie interfejsu do tworzenia różnych obiektów należących
 * do tego samego typu (rodziny).Interfejs ten definiuje grupę metod, za pomocą których tworzone są obiekty.
 */
public class AbstractFactory {
    public static void main(String[] args) {
        Computer pc = ComputerFactory2.getComputer(new PCFactory("2 GB","500 GB","2.4 GHz"));
        Computer server = ComputerFactory2.getComputer(new ServerFactory("16 GB","1 TB","2.9 GHz"));
        System.out.println("AbstractFactory PC Config::"+pc);
        System.out.println("AbstractFactory Server Config::"+server);
    }
}

abstract class Computer {

    public abstract String getRAM();
    public abstract String getHDD();
    public abstract String getCPU();

    @Override
    public String toString(){
        return "RAM= "+this.getRAM()+", HDD="+this.getHDD()+", CPU="+this.getCPU();
    }
}

class PC extends Computer {

    private String ram;
    private String hdd;
    private String cpu;

    public PC(String ram, String hdd, String cpu){
        this.ram=ram;
        this.hdd=hdd;
        this.cpu=cpu;
    }

    @Override
    public String getRAM() { return this.ram; }
    @Override
    public String getHDD() { return this.hdd; }
    @Override
    public String getCPU() { return this.cpu; }

}

class Server extends Computer {

    private String ram;
    private String hdd;
    private String cpu;

    public Server(String ram, String hdd, String cpu){
        this.ram=ram;
        this.hdd=hdd;
        this.cpu=cpu;
    }
    @Override
    public String getRAM() { return this.ram; }
    @Override
    public String getHDD() { return this.hdd; }
    @Override
    public String getCPU() { return this.cpu; }

}

interface ComputerAbstractFactory {
    public Computer createComputer();
}

class PCFactory implements ComputerAbstractFactory {

    private String ram;
    private String hdd;
    private String cpu;

    public PCFactory(String ram, String hdd, String cpu){
        this.ram=ram;
        this.hdd=hdd;
        this.cpu=cpu;
    }

    @Override
    public Computer createComputer() {
        return new PC(ram,hdd,cpu);
    }

}

class ServerFactory implements ComputerAbstractFactory {

    private String ram;
    private String hdd;
    private String cpu;

    public ServerFactory(String ram, String hdd, String cpu){
        this.ram=ram;
        this.hdd=hdd;
        this.cpu=cpu;
    }

    @Override
    public Computer createComputer() {
        return new Server(ram,hdd,cpu);
    }

}

class ComputerFactory2 {

    public static Computer getComputer(ComputerAbstractFactory factory){
        return factory.createComputer();
    }
}