package work_07_01_26.task;

public class SMSNotification extends Notification{
    private String phoneNumber;

    public SMSNotification(String recipient, String message) {
        super(recipient, message);
    }

   // public String truncateMessage() {
   // }

    @Override
    public void send() {
        if (message.length() > 160) {
            throw new RuntimeException("limit maximum 160 chars");
        }
        super.send();
    }
}
