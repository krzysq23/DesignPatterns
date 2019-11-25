package BehavioralPatterns.Memento;

/**
 * Wzorzec memento jest używany, gdy chcemy zapisać stan obiektu,
 * abyśmy mogli później go przywrócić. Wzorzec memento służy do realizacji tego w taki sposób,
 * że zapisane dane stanu obiektu nie są dostępne na zewnątrz obiektu, co chroni integralność zapisanych danych stanu.
 */
public class Memento {
    public static void main(String[] args) {
        FileWriterCaretaker caretaker = new FileWriterCaretaker();
        FileWriterUtil fileWriter = new FileWriterUtil("data.txt");
        fileWriter.write("First Set of Data");
        readFile(fileWriter);
        caretaker.save(fileWriter);
        fileWriter.write("\nSecond Set of Data");
        caretaker.save(fileWriter);
        readFile(fileWriter);
        fileWriter.write("\nThird Set of Data");
        readFile(fileWriter);
        caretaker.undo(fileWriter);
        readFile(fileWriter);
    }

    public static void readFile(FileWriterUtil fileWriter) {
        System.out.println("-------------- " + fileWriter.getFileName() + " --------------");
        System.out.println(fileWriter);
        System.out.println("-------------- END --------------\n\n");
    }
}

class FileWriterUtil {

    private String fileName;
    private StringBuilder content;

    public FileWriterUtil(String file) { this.fileName = file;this.content = new StringBuilder(); }

    @Override
    public String toString() { return this.content.toString(); }

    public String getFileName() { return fileName; }

    public void write(String str) { content.append(str); }

    public Memento save() { return new Memento(this.fileName, this.content); }

    public void undoToLastSave(Object obj) {
        Memento memento = (Memento) obj;
        this.fileName = memento.fileName;
        this.content = memento.content;
    }

    private class Memento {
        private String fileName;
        private StringBuilder content;

        public Memento(String file, StringBuilder content) {
            this.fileName = file;
            //notice the deep copy so that Memento and FileWriterUtil content variables don't refer to same object
            this.content = new StringBuilder(content);
        }
    }
}

class FileWriterCaretaker {
    private Object obj;
    public void save(FileWriterUtil fileWriter) { this.obj = fileWriter.save(); }
    public void undo(FileWriterUtil fileWriter) { fileWriter.undoToLastSave(obj); }
}