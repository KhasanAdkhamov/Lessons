package work_07_01_26.task;

public class EmailNotification extends Notification{
    private  String subject;  //тема письма
    private String senderEmail;

    public EmailNotification(String recipient, String message, String subject, String senderEmail) {
        super(recipient, message);
        this.subject = subject;
        this.senderEmail = senderEmail;
    }

    @Override
    public void send() {
        super.send();
        System.out.println("тема письма " + subject + " от " + senderEmail);
    }

    @Override
    public String formatMessage() {
        super.formatMessage();
        return senderEmail;
    }
}
