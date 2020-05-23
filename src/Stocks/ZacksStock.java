package Stocks;

public class ZacksStock extends Stock {
    private int industryRank;
    private int zacksRank;
    private char vgmScore;

    public ZacksStock(String companyName, String tickerSymbol, float lastPrice, int industryRank, int zacksRank, char vgmScore) {
        super(companyName, tickerSymbol, lastPrice);
        this.industryRank = industryRank;
        this.zacksRank = zacksRank;
        this.vgmScore = vgmScore;
    }

    public ZacksStock(String companyName, String tickerSymbol, int industryRank, int zacksRank, char vgmScore) {
        super(companyName, tickerSymbol);
        this.industryRank = industryRank;
        this.zacksRank = zacksRank;
        this.vgmScore = vgmScore;
    }

    public int getIndustryRank() {
        return industryRank;
    }

    public int getZacksRank() {
        return zacksRank;
    }

    public char getVgmScore() {
        return vgmScore;
    }
}
