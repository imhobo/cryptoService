import java.util.ArrayList;
import java.util.List;

public class Main {

    /*
    Create a crypto notification service as an HTTP Rest API server


    Create a Notification (Input parameters: Current Price of Bitcoin, Daily Percentage Change, Trading Volume, etc)

    Send a notification to email/emails

    List notifications (Sent, Pending, Failed)

        Update/Delete notification
    */

    public static void main(String[] args) {

        CryptoNotificationService service = new CryptoNotificationService();

        try {
            String n1 = service.addNotification(new NotificationRequest(100, 20, 1000));
            String n2 = service.addNotification(new NotificationRequest(120, 30, 2000));
            List<String> e1 = new ArrayList<>();
            e1.add("abc@xyz.com");
            e1.add("pqr@xyz.com");

            System.out.println(service.getNotifications(NotificationStatus.PENDING));
            //sending emails
            service.sendNotificationEmails(n1, e1);
            service.sendNotificationEmails(n2, e1);

            //checking notifications by status
            System.out.println(service.getNotifications(NotificationStatus.PENDING));
            System.out.println(service.getNotifications(NotificationStatus.SENT));

            //delete notification
            service.deleteNotification(n1);
            System.out.println(service.getNotifications(NotificationStatus.DELETED));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
