import java.util.*;

public class DataStore {

    private Map<String, Notification> notificationMap;
    private Map<NotificationStatus, List<Notification>> statusNotificationMap;
    private String host;
    private int port;

    public DataStore(String host, int port) {
        this.host = host;
        this.port = port;
        this.notificationMap = new HashMap<>();
        this.statusNotificationMap = new HashMap<>();
    }

    public void saveNotification(Notification notification) throws Exception{
        if(notificationMap.containsKey(notification.getId())) {
            throw new Exception("Notification already exists " + notification.getId());
        }
        notificationMap.put(notification.getId(), notification);
        statusNotificationMap.putIfAbsent(notification.getStatus(), new ArrayList<>());
        List<Notification> notificationList = statusNotificationMap.get(notification.getStatus());
        notificationList.add(notification);
    }

    public Notification getNotification(String notificationId) {
        if(!notificationMap.containsKey(notificationId)) {
            throw new NoSuchElementException("Invalid Notification Id : " + notificationId);
        }

        return notificationMap.get(notificationId);
    }

    public void updateStatus(String notificationId, NotificationStatus status) {
        if(!notificationMap.containsKey(notificationId)) {
            throw new NoSuchElementException("Invalid Notification Id : " + notificationId);
        }

        Notification notification = notificationMap.get(notificationId);
        NotificationStatus oldStatus = notification.getStatus();
        notification.setStatus(status);

        List<Notification> oldNotifications = statusNotificationMap.get(oldStatus);
        oldNotifications.remove(notification);
        statusNotificationMap.putIfAbsent(status, new ArrayList<>());
        List<Notification> statusNotifications = statusNotificationMap.get(status);
        statusNotifications.add(notification);
    }

    public List<Notification> getNotificationsByStatus(NotificationStatus status) {
        return statusNotificationMap.get(status);
    }

    public void deleteNotification(String notificationId) {
        if(!notificationMap.containsKey(notificationId)) {
            throw new NoSuchElementException("Invalid Notification Id : " + notificationId);
        }
        updateStatus(notificationId, NotificationStatus.DELETED);
    }
}
