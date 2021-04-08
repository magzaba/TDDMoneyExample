package moneyexample;

import java.util.HashMap;

class Bank {
    private HashMap<Pair,Integer> rates = new HashMap<>();

    Money reduce(Expression source, String to) {
        return source.reduce(this,to);
    }

    public int rate(String from, String to) {
        if(from.equals(to)) return 1;
        return rates.get(new Pair(from,to));
    }

    public void addRate(String from, String to, int rate) {
    rates.put(new Pair(from,to),rate);
    }
}
