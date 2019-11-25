package BehavioralPatterns.Mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Wzorzec projektowy Mediator jest bardzo pomocny w aplikacji korporacyjnej,
 * w której wiele obiektów oddziałuje ze sobą. Jeśli obiekty oddziałują bezpośrednio na siebie,
 * komponenty systemu są ściśle ze sobą powiązane, co powoduje, że koszty utrzymania są wyższe
 * i nie są trudne do rozszerzenia. Wzorzec mediatora koncentruje się na zapewnieniu mediatora
 * między obiektami do komunikacji i pomocy w implementacji sprzężenia między obiektami.
 */
public class Mediator {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Kris");
        User user2 = new UserImpl(mediator, "John");
        User user3 = new UserImpl(mediator, "Max");
        User user4 = new UserImpl(mediator, "David");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi All");
    }
}

interface ChatMediator {
    public void sendMessage(String msg, User user);
    void addUser(User user);
}

abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name){
        this.mediator=med;
        this.name=name;
    }

    public abstract void send(String msg);
    public abstract void receive(String msg);
}

class ChatMediatorImpl implements ChatMediator {

    private List<User> users;

    public ChatMediatorImpl(){
        this.users=new ArrayList<>();
    }

    @Override
    public void addUser(User user){ this.users.add(user); }

    @Override
    public void sendMessage(String msg, User user) {
        for(User u : this.users){
            //message should not be received by the user sending it
            if(u != user){
                u.receive(msg);
            }
        }
    }

}

class UserImpl extends User {

    public UserImpl(ChatMediator med, String name) {
        super(med, name);
    }

    @Override
    public void send(String msg){
        System.out.println(this.name+": Sending Message="+msg);
        mediator.sendMessage(msg, this);
    }
    @Override
    public void receive(String msg) {
        System.out.println(this.name+": Received Message:"+msg);
    }

}