package MiscellaneousPatterns.DependencyInjection;

public class DependencyInjection {
    public static void main(String[] args) {
        String msg = "Hi Kris", email = "kris@example.com", phone = "4088888888";
        MessageServiceInjector injector = null;
        Consumer app = null;

        injector = new EmailServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, email);

        injector = new SMSServiceInjector();
        app = injector.getConsumer();
        app.processMessages(msg, phone);
    }
}
class MyDIApplication implements Consumer {
    private MessageService service;
    public MyDIApplication(){}
    public void setService(MessageService service) {
        this.service = service;
    }
    @Override
    public void processMessages(String msg, String rec){
        this.service.sendMessage(msg, rec);
    }
}

interface MessageService {
    void sendMessage(String msg, String rec);
}

class EmailServiceImpl implements MessageService {
    @Override
    public void sendMessage(String msg, String rec) {
        System.out.println("Email sent to " + rec + " with Message = " + msg);
    }
}

class SMSServiceImpl implements MessageService {
    @Override
    public void sendMessage(String msg, String rec) {
        System.out.println("SMS sent to " + rec + " with Message = " + msg);
    }
}

interface Consumer {
    void processMessages(String msg, String rec);
}

interface MessageServiceInjector {
    public Consumer getConsumer();
}

class EmailServiceInjector implements MessageServiceInjector {
    @Override
    public Consumer getConsumer() {
        MyDIApplication app = new MyDIApplication();
        app.setService(new EmailServiceImpl());
        return app;
    }
}

class SMSServiceInjector implements MessageServiceInjector {
    @Override
    public Consumer getConsumer() {
        MyDIApplication app = new MyDIApplication();
        app.setService(new SMSServiceImpl());
        return app;
    }
}