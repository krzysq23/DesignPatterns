package CreationalPatterns.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Wzorzec budowniczego stosowany jest do oddzielenia sposobu tworzenia obiektów od tego jak te obiekty mają wyglądać.
 * Buildery są dodatkowymi klasami, który ułatwiają tworzenie innych złożonych klas.
 *
 */
public class Builder {
    public static void main(String[] args) {
        List<Level> levels = new ArrayList<>();
        levels.add(new Level("5km", "Cookie"));
        levels.add(new Level("10km", "Wine"));
        levels.add(new Level("25km", "New laptop"));

        Checklist checklist = new Checklist("Todos");
        checklist.addItem(new ListItem("Buy shoes"));
        checklist.addItem(new ListItem("Run every second day"));
        checklist.addItem(new ListItem("Other"));

        /* ------- Method #1 ---------*/
        Goal goal = new Goal.Builder()
                .name("Run the marathon")
                .description("My goal")
                .levels(levels)
                .checklist(checklist)
                .achieved()
                .build();
        System.out.println(goal);

        /* ------- Method #2 ---------*/
        Goal goal2 = Goal.builder()
                .name("Run the marathon")
                .description("My goal")
                .addLevel(new Level("5km", "Cookie"))
                .addLevel(new Level("10km", "Wine"))
                .addLevel(new Level("25km", "New laptop"))
                .checklist(checklist)
                .achieved()
                .build();
        System.out.println(goal2);
    }
}

class Goal {

    private String name;
    private String description;
    private List<Level> levels;
    private Checklist checklist;
    private LocalDate deadline;
    private boolean achieved;

    /**
     * Możemy też uniemożliwić instancjonowanie klasy zwykłym konstruktorem dodając go jako prywatny
     * # Pozwala zachować niemutowalność klasy
     * # Wymuszone użycia buildera, aby utworzyć instancję klasy
     * # Builder może mieć kilka parametrów var args
     */
    private Goal() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String description;
        private List<Level> levels = new ArrayList<Level>();
        private Checklist checklist;
        private LocalDate deadline;
        private boolean achieved = false;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder levels(List<Level> levels) {
            this.levels = levels;
            return this;
        }

        public Builder checklist(Checklist checklist) {
            this.checklist = checklist;
            return this;
        }

        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder achieved() {
            this.achieved = true;
            return this;
        }

        public Builder addLevel(Level level) {
            this.levels.add(level);
            return this;
        }

        public Goal build() {
            if(name.isEmpty()){
                throw new IllegalStateException("Name cannot be empty");
            }
            if(levels.isEmpty()){
                throw new IllegalStateException("Levels cannot be empty");
            }

            Goal goal = new Goal();
            goal.deadline = this.deadline;
            goal.name = this.name;
            goal.checklist = this.checklist;
            goal.levels = this.levels;
            goal.description = this.description;
            goal.achieved = this.achieved;
            return goal;
        }
    }

    @Override
    public String toString() {
        return "Goal{\n name='" + name + '\'' + ", \n description='" + description + '\'' + ", \n levels=" + levels
                + ", \n checklist=" + checklist + ", \n deadline=" + deadline + ", \n achieved=" + achieved + "\n}";
    }
}

class Level {
    private String distance;
    private String gift;

    public String getDistance() { return distance; }
    public void setDistance(String distance) { this.distance = distance; }
    public String getGift() { return gift; }
    public void setGift(String gift) { this.gift = gift; }

    public Level(String distance, String gift) {
        this.distance = distance;
        this.gift = gift;
    }

    @Override
    public String toString() {
        return "Level{distance='" + distance + '\'' + ", gift='" + gift + '\'' + '}'; }
}

class Checklist {
    private String name;
    private List<ListItem> list = new ArrayList<ListItem>();
    public Checklist(String name) { this.name = name; }
    public void addItem(ListItem item) {
        this.list.add(item); }
    @Override
    public String toString() { return "Checklist{name='" + name + '\'' + ", list=" + list + '}'; }
}

class ListItem {
    private String item;
    public ListItem(String item) { this.item = item; }
    public String getItem() { return item; }
    @Override
    public String toString() { return item; }
}