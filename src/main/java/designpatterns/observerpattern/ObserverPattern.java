// Observer Design Pattern

package designpatterns.observerpattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    // Observer interface: defines the contract for objects that want to be notified
    interface Observer {
        void update(String event);
    }

    // Subject: maintains a list of observers and notifies them when state changes
    static class NewsPublisher {
        private final List<Observer> observers = new ArrayList<>();

        void subscribe(Observer observer) {
            observers.add(observer);
        }

        void unsubscribe(Observer observer) {
            observers.remove(observer);
        }

        // Walk through every registered observer and push the event
        void publish(String headline) {
            for (Observer observer : observers) {
                observer.update(headline);
            }
        }
    }

    // Concrete observer: email subscriber
    static class EmailSubscriber implements Observer {
        private final String email;

        EmailSubscriber(String email) {
            this.email = email;
        }

        @Override
        public void update(String event) {
            System.out.println("Email to " + email + ": " + event);
        }
    }

    // Concrete observer: SMS subscriber
    static class SmsSubscriber implements Observer {
        private final String phone;

        SmsSubscriber(String phone) {
            this.phone = phone;
        }

        @Override
        public void update(String event) {
            System.out.println("SMS to " + phone + ": " + event);
        }
    }

    // Concrete observer: push notification subscriber
    static class PushSubscriber implements Observer {
        private final String deviceId;

        PushSubscriber(String deviceId) {
            this.deviceId = deviceId;
        }

        @Override
        public void update(String event) {
            System.out.println("Push to " + deviceId + ": " + event);
        }
    }

    public static void main(String[] args) {
        NewsPublisher publisher = new NewsPublisher();

        EmailSubscriber emailSub = new EmailSubscriber("user@example.com");
        SmsSubscriber smsSub = new SmsSubscriber("+1-555-0100");
        PushSubscriber pushSub = new PushSubscriber("device-abc-123");

        // Register all three observers
        publisher.subscribe(emailSub);
        publisher.subscribe(smsSub);
        publisher.subscribe(pushSub);

        // All observers get notified
        publisher.publish("Breaking: Observer Pattern Implemented!");

        System.out.println();

        // Unsubscribe SMS, only email and push get the next notification
        publisher.unsubscribe(smsSub);
        publisher.publish("Update: SMS subscriber removed");
    }
}
