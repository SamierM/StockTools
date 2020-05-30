package Stocks;

import java.util.ArrayList;
import java.util.List;

public class ZacksStock extends Stock {
    private final char COMMA_DELIMITER = ',';

    private static final int MINIMUM_VGM_RATING = 1;
    private static final int MINIMUM_ZACK_RATING = 1;
    private static final char MAXIMUM_VGM_RATING = 'A';

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

    //Verifies that the stock has an 'A' VGM rating and a rank 1 Zack score.
    public boolean isZacksApproved() {
        if((MAXIMUM_VGM_RATING - vgmScore) < 0 || (MINIMUM_ZACK_RATING < zacksRank)){
            return false;
        } else {
            return true;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(zacksRank).append(COMMA_DELIMITER)
                .append(vgmScore);
        return sb.toString();

    }
}
