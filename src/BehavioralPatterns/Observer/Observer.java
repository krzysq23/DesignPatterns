package BehavioralPatterns.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Wzorzec projektowy obserwatora jest przydatny, gdy interesuje Cię stan obiektu
 * i chcesz otrzymywać powiadomienia o każdej zmianie. We wzorcu obserwatora obiekt obserwujący
 * stan innego obiektu nazywa się Obserwator, a obserwowany obiekt nazywa się Obiekt .
 */
public class Observer {
    public static void main(String[] args) {
//create subject
        MyTopic topic = new MyTopic();

        //create observers
        Observers obj1 = new MyTopicSubscriber("Obj1");
        Observers obj2 = new MyTopicSubscriber("Obj2");
        Observers obj3 = new MyTopicSubscriber("Obj3");

        //register observers to the subject
        topic.register(obj1);
        topic.register(obj2);
        topic.register(obj3);

        //attach observer to subject
        obj1.setSubject(topic);
        obj2.setSubject(topic);
        obj3.setSubject(topic);

        //check if any update is available
        obj1.update();

        //now send message to subject
        topic.postMessage("New Message");
    }
}

interface Subject {

    //methods to register and unregister observers
    public void register(Observers obj);
    public void unregister(Observers obj);

    //method to notify observers of change
    public void notifyObservers();

    //method to get updates from subject
    public Object getUpdate(Observers obj);

}

interface Observers {

    //method to update the observer, used by subject
    public void update();

    //attach with subject to observe
    public void setSubject(Subject sub);
}

class MyTopic implements Subject {

    private List<Observers> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();

    public MyTopic(){ this.observers=new ArrayList<>(); }

    @Override
    public void register(Observers obj) {
        if(obj == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if(!observers.contains(obj)) observers.add(obj);
        }
    }

    @Override
    public void unregister(Observers obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observers> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed=false;
        }
        for (Observers obj : observersLocal) {
            obj.update();
        }

    }

    @Override
    public Object getUpdate(Observers obj) { return this.message; }

    //method to post message to the topic
    public void postMessage(String msg){
        System.out.println("Message Posted to Topic:"+msg);
        this.message=msg;
        this.changed=true;
        notifyObservers();
    }

}

class MyTopicSubscriber implements Observers {

    private String name;
    private Subject topic;

    public MyTopicSubscriber(String nm){
        this.name=nm;
    }
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(name+":: No new message");
        }else
            System.out.println(name+":: Consuming message::"+msg);
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }

}