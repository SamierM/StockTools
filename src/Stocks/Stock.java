package Stocks;

public abstract class Stock {
    private final char COMMA_DELIMITER = ',';

    private String companyName;
    private String tickerSymbol;
    private float lastPrice = 0;

    public String getCompanyName() {
        return companyName;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public Stock(String companyName, String tickerSymbol, float lastPrice) {
        this.companyName = companyName;
        this.tickerSymbol = tickerSymbol;
        this.lastPrice = lastPrice;
    }

    public Stock(String companyName, String tickerSymbol) {
        this.companyName = companyName;
        this.tickerSymbol = tickerSymbol;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(companyName).append(COMMA_DELIMITER)
                .append(tickerSymbol).append(COMMA_DELIMITER);
        return sb.toString();
    }
}
