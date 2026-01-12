package work_07_01_26.homework.task6;

public class EmailNotification extends Notification{
    private String subject;
    private String senderEmail;

    public EmailNotification(String recipient, String message, String subject, String senderEmail) {
        super(recipient, message);
        this.subject = subject;
        this.senderEmail = senderEmail;
    }

    @Override
    public void send() {
        System.out.println(message + " пришло получателю " + recipient + timestamp + " отправил " + senderEmail + " тема " + subject);
    }

    @Override
    public String formatMessage() {
        return super.formatMessage();
    }
}
