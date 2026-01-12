package work_07_01_26.homework.task6;

import java.time.LocalDateTime;

public class Notification {
    protected String recipient;
    protected String message;
    protected String timestamp;

    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    public void  send() {
        System.out.println(message + " пришло получателю " + recipient + timestamp);
    }

    public String formatMessage() {
        return recipient + recipient;
    }

    public void displayInfo() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Notification{" +
                "recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}

