package patterns.builder;

// 1.Prywatny konstruktor House
// 2.Publiczna statyczna klasa wewnetrzna Build
// 3.Metoda build wewnetrznej klasy zwracająca nowy obiekt House.
// 4.Pola prywatne finaalne klasy House oraz finalna klasa House


public final class House {

    private final String okna;
    private final String drzwi;
    private final String dachowka;
    private final String elewacja;

    private House(String okna, String drzwi, String dachowka, String elewacja) {
        this.okna = okna;
        this.drzwi = drzwi;
        this.dachowka = dachowka;
        this.elewacja = elewacja;
    }

    public static class Builder {
        private String okna;
        private String drzwi;
        private String dachowka;
        private String elewacja;

        public Builder() {
        }

        public Builder okna(String okna) {
            this.okna = okna;
            return this;
        }

        public Builder drzwi(String drzwi) {
            this.drzwi = drzwi;
            return this;
        }

        public Builder dachowka(String dachowka) {
            this.dachowka = dachowka;
            return this;
        }

        public Builder elewacja(String elewacja) {
            this.elewacja = elewacja;
            return this;
        }

        public House build() {
            return new House(okna, drzwi, dachowka, elewacja);
        }
    }

    public static void main(String args[]) {
        House house = new Builder().okna("okna")
                .dachowka("dachowka")
                .elewacja("elewacja")
                .drzwi("drzwi")
                .build();
    }


}

