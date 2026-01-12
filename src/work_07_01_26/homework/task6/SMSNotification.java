package work_07_01_26.homework.task6;

import java.util.Arrays;

public class SMSNotification extends Notification{
    private String phoneNumber;

    public SMSNotification(String recipient, String message, String phoneNumber) {
        super(recipient, message);
        this.phoneNumber = phoneNumber;
    }

    public String truncateMessage() {
            if (message.length() > 160) {
                return message.substring(0, 159);
            }
            return message;
    }

    @Override
    public void send() {
        if (message.length() > 160) {
            throw new RuntimeException("limit maximum 160 chars");
        }
        super.send();
    }
}
