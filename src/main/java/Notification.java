import java.util.Objects;
import java.util.UUID;

public class Notification {

    private String id;
    private int bitcoinPrice;
    private int dailyPercentageChange;
    private int tradingVolume;
    private NotificationStatus status;
    private boolean isDeleted = false;

    public Notification(int bitcoinPrice, int dailyPercentageChange, int tradingVolume) {
        this.bitcoinPrice = bitcoinPrice;
        this.dailyPercentageChange = dailyPercentageChange;
        this.tradingVolume = tradingVolume;
        this.id = UUID.randomUUID().toString();
        this.status = NotificationStatus.PENDING;
    }

    public int getBitcoinPrice() {
        return bitcoinPrice;
    }

    public int getDailyPercentageChange() {
        return dailyPercentageChange;
    }

    public int getTradingVolume() {
        return tradingVolume;
    }

    public String getId() {
        return id;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", bitcoinPrice=" + bitcoinPrice +
                ", dailyPercentageChange=" + dailyPercentageChange +
                ", tradingVolume=" + tradingVolume +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
