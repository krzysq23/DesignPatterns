package CreationalPatterns.Singleton;

/**
 * Singleton.Singleton - jego celem jest ograniczenie możliwości tworzenia obiektów danej klasy
 * do jednej instancji oraz zapewnienie globalnego dostępu do stworzonego obiektu
 * – jest to obiektowa alternatywa dla zmiennych globalnych.
 */
public class Singleton {

    public static void main(String[] args) {
        //Singleton.StaticBlockSingleton.getInstance();
        //Singleton.LazyInitializedSingleton.getInstance();
        //Singleton.ThreadSafeSingleton.getInstance();
    }
}

/**
 * Podczas szybkiej inicjalizacji instancja klasy Singleton.Singleton jest tworzona podczas ładowania klasy,
 * jest to najłatwiejsza metoda tworzenia klasy singleton, ale ma tę wadę, że instancja jest tworzona,
 * nawet jeśli aplikacja kliencka może jej nie używać.
 */
class EagerInitializedSingleton {

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    private EagerInitializedSingleton() {
    }

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
}

/**
 * Implementacja inicjalizacji bloku statycznego jest tworzona w bloku statycznym,
 * który zapewnia obsługę wyjątków.
 * Zarówno szybka inicjalizacja, jak i statyczna inicjalizacja bloku
 * tworzy instancję jeszcze przed jej użyciem i nie jest to najlepsza praktyka
 */
class StaticBlockSingleton {

    private static StaticBlockSingleton instance;

    private StaticBlockSingleton(){}

    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}

/**
 * Leniwa metoda inicjalizacji w celu zaimplementowania wzorca Singleton.Singleton
 * tworzy instancję w globalnej metodzie. Powyższa implementacja działa dobrze w przypadku środowiska
 * jednowątkowego, ale w przypadku systemów wielowątkowych może powodować problemy,
 * jeśli wiele wątków znajduje się jednocześnie w warunku if.
 */
class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton(){}

    public static LazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}

/**
 * Najłatwiejszym sposobem utworzenia bezpiecznej dla wątków klasy singletonu
 * jest zsynchronizowanie globalnej metody dostępu , tak aby tylko jeden wątek
 * mógł wykonać tę metodę jednocześnie. Poniższa implementacja działa dobrze
 * i zapewnia bezpieczeństwo wątków, ale zmniejsza wydajność ze względu na koszty
 * związane z metodą zsynchronizowaną, chociaż potrzebujemy jej tylko dla kilku
 * pierwszych wątków, które mogą utworzyć osobne instancje
 */
class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){}

    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance == null){
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    /**
     * Aby uniknąć tego dodatkowego obciążenia za każdym razem,
     * stosowana jest zasada podwójnie sprawdzonej blokady .
     */
    public static ThreadSafeSingleton getInstanceUsingDoubleLocking(){
        if(instance == null){
            synchronized (ThreadSafeSingleton.class) {
                if(instance == null){
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}

/**
 * Kiedy zbyt wiele wątków próbuje uzyskać instancję Singletona,
 * można tworzyć instancję przy użyciu wewnętrznej klasy pomocniczej.
 * Gdy klasa singleton jest ładowana, klasa SingletonHelper nie jest ładowana do pamięci
 * i tylko wtedy, gdy ktoś wywoła metodę getInstance , klasa ta zostaje załadowana i tworzy instancję klasy Singleton.Singleton.
 * Jest to najczęściej stosowane podejście w klasie Singleton.Singleton, ponieważ nie wymaga synchronizacji.
 */
class BillPughSingleton {

    private BillPughSingleton(){}

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}