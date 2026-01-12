package work_07_01_26.task;

import java.time.LocalDate;
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
        return recipient + timestamp;
    }

    public void displayInfo() {
        toString();
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

