package CreationalPatterns.Prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Prototypowy wzorzec projektowy jest wykorzystywany, gdy tworzenie obiektu jest kosztownym przedsięwzięciem
 * i wymaga dużo czasu i zasobów, a podobny obiekt już istnieje.
 * Wzór prototypowy zapewnia mechanizm kopiowania oryginalnego obiektu do nowego obiektu, a następnie modyfikowania go
 * zgodnie z naszymi potrzebami. Prototypowy wzorzec projektowy wykorzystuje klonowanie Java do skopiowania obiektu.
 */
public class Prototype {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employees emps = new Employees();
        emps.loadData();

        Employees empsNew = (Employees) emps.clone();
        Employees empsNew1 = (Employees) emps.clone();
        List<String> list = empsNew.getEmpList();
        list.add("John");
        List<String> list1 = empsNew1.getEmpList();
        list1.remove("Krzysiek");

        System.out.println("emps List: "+emps.getEmpList());
        System.out.println("empsNew List: "+list);
        System.out.println("empsNew1 List: "+list1);
    }
}

class Employees implements Cloneable {

    private List<String> empList;

    public Employees() {
        empList = new ArrayList<String>();
    }

    public Employees(List<String> list) {
        this.empList = list;
    }

    public void loadData() {
        empList.add("Marcin");
        empList.add("Damian");
        empList.add("Krzysiek");
        empList.add("Waldek");
    }

    public List<String> getEmpList() {
        return empList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<String> temp = new ArrayList<String>();
        for (String s : this.getEmpList()) {
            temp.add(s);
        }
        return new Employees(temp);
    }

}