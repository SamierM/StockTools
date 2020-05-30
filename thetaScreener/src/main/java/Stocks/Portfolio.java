package Stocks;

import Stocks.Stock;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Portfolio extends ArrayList {
    private ArrayList<Stock> holdings;

    public Portfolio() {
        this.holdings = new ArrayList<Stock>();
    }

    public ArrayList getHoldings() {
        return holdings;
    }

    public void setHoldings(ArrayList<Stock> holdings) {
        this.holdings = holdings;
    }

    public void addStock(Stock stockToAdd) {
        this.holdings.add(stockToAdd);
    }
}
