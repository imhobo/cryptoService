import java.util.List;

public class CryptoNotificationService {

    private DataStore store;
    private EmailService emailService;
    private final String HOST = "localhost";
    private final int PORT = 8080;

    public CryptoNotificationService() {
        this.store = new DataStore(HOST, PORT);
        this.emailService = new EmailService();
    }

    public String addNotification(NotificationRequest notificationRequest) throws Exception {
        Notification notification = new Notification(notificationRequest.getBitcoinPrice(), notificationRequest.getDailyPercentageChange(), notificationRequest.getTradingVolume());
        store.saveNotification(notification);
        return notification.getId();
    }

    void sendNotificationEmails(String notificationId, List<String> emails) {
        Notification notification = store.getNotification(notificationId);
        for(String emailAddress : emails) {
            try {
                emailService.sendEmail("Received notification " + notification, emailAddress);
            } catch (Exception e) {
                System.out.println("Could not send email for notification id : " + notificationId);
                store.updateStatus(notificationId, NotificationStatus.FAILED);
            }
        }

        store.updateStatus(notificationId, NotificationStatus.SENT);
    }

    List<Notification> getNotifications(NotificationStatus status) {
        return store.getNotificationsByStatus(status);
    }

    void deleteNotification(String notificationId) {
        store.deleteNotification(notificationId);
    }
}
