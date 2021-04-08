package moneyexample;

import java.util.HashMap;

class Bank {
    private final HashMap<Pair,Integer> rates = new HashMap<>();

   private static class Pair {
        private final String from;
        private final String to;

        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object object) {
            Pair pair =(Pair) object;
            return from.equals(pair.from)&&to.equals(pair.to);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    Money reduce(Expression source, String to) {
        return source.reduce(this,to);
    }

    public int rate(String from, String to) {
        if(from.equals(to)) return 1;
        return rates.get(new Pair(from, to));
    }

    public void addRate(String from, String to, int rate) {
    rates.put(new Pair(from, to),rate);
    }
}
