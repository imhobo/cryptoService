public class NotificationRequest {

    private int bitcoinPrice;
    private int dailyPercentageChange;
    private int tradingVolume;

    public NotificationRequest(int bitcoinPrice, int dailyPercentageChange, int tradingVolume) {
        this.bitcoinPrice = bitcoinPrice;
        this.dailyPercentageChange = dailyPercentageChange;
        this.tradingVolume = tradingVolume;
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
}
