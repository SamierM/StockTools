package Stocks;

public class ArkStock extends Stock {
    private float marketValue;
    private float portfolioWeight;

    public ArkStock(String companyName, float marketValue, float portfolioWeight) {
        super(companyName, "NULL_SYMBOL");
        this.marketValue = marketValue;
        this.portfolioWeight = portfolioWeight;
    }


    public float getMarketValue() {
        return marketValue;
    }

    public float getPortfolioWeight() {
        return portfolioWeight;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getCompanyName()).append(", ");
        stringBuilder.append(getPortfolioWeight());

        return stringBuilder.toString();
    }
}
