package CreationalPatterns.Factory;

/**
 * Dzięki użyciu fabryki programista zyskuje abstrakcyjną warstwę, odpowiedzialną
 * za tworzenie instancji obiektów w jakiś sposób powiązanych wspólnym interfejsem.
 * Uzyskujemy wtedy kod, który jest bardziej skalowalny, łatwiejszy na rozbudowę.
 */
public class Factory {
    public static void main(String[] args) {
        Computer computer = ComputerFactory.getComputer("Factory.PC", "16GB", "500GB", "i7");
        //Factory.ComputerFactory.getComputer("Laptop", "16GB", "500GB", "i7").toString();
        // java.lang.NullPointerException
        Computer server = ComputerFactory.getComputer("server","16 GB","1 TB","2.9 GHz");
        System.out.println("Factory.Factory Factory.PC Config::" + computer);
        System.out.println("Factory.Factory Factory.Server Config::" + server);
    }
}

/**
 * Super klasa we wzorcu projektowania fabrycznego może być interfejsem, klasą abstrakcyjną lub zwykłą klasą.
 */
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

/**
 * klasa fabryczna
 */
class ComputerFactory {

    public static Computer getComputer(String type, String ram, String hdd, String cpu){
        if("Factory.PC".equalsIgnoreCase(type)) return new PC(ram, hdd, cpu);
        else if("Factory.Server".equalsIgnoreCase(type)) return new Server(ram, hdd, cpu);
        return null;
    }
}