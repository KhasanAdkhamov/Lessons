package work_07_01_26.homework.task6;

public class PushNotification extends Notification{
    private String appName;
    private boolean withSound;

    public PushNotification(String recipient, String message) {
        super(recipient, message);
        withSound = false;
    }

    @Override
    public void send() {
        System.out.println(message + " пришло получателю " + recipient + timestamp + " в приложение " + appName);
    }

    public boolean playSound() {
        if (!withSound) {
            System.out.println("звук уведомлений включен");
            withSound = true;
        }
        return false;
    }
}
