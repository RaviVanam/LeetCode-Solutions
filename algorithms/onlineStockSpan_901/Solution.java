package onlineStockSpan_901;

import java.util.ArrayList;
import java.util.List;

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
class StockSpanner {

    List<Integer> stockValues;
    List<Integer> stockSpans;

    public StockSpanner() {
        stockValues = new ArrayList<>();
        stockSpans = new ArrayList<>();
    }

    public int next(int price) {
        stockValues.add(price);
        int n = stockValues.size();

        int j = n - 2;
        while (j != -1 && stockValues.get(j) <= price) {
            j = stockSpans.get(j);
        }
        stockSpans.add(j);

        return (n - 1) - j;
    }
}