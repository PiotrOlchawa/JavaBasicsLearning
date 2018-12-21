package patterns.strategy;

/****************************************
 *   Wzorzec Projektowy Strategy         *
 *   (strategia )                        *
 *   www.algorytm.org                    *
 *   Opracowal Dworak Kamil              *
 *****************************************/

/*Wzorzec Strategy (pol. strategia) polega na możliwości "wstrzykiwania" całych kawałków kodu,
 realizujących w różny sposób pewne zadania (w zależności od tego, jaką przyjmiemy strategię działania). */

import java.util.Scanner;

interface Pracowac {
    public void pracuj();
}

interface Dojezdzac {
    public void dojezdzaj();
}

interface SpedzanieWolnegoCzasu{
    public void spedzajWolnyCzas();
}

//poszczegolne algorytmy
class NaprawaSamochodow implements Pracowac {
    public void pracuj(){
        System.out.println("Praca: naprawia samochody");
    }
}

class Leczenie implements Pracowac {
    public void pracuj(){
        System.out.println("Praca: leczy ludzi");
    }
}

class RoznoszenieListow implements Pracowac {
    public void pracuj(){
        System.out.println("Praca: roznosi listy");
    }
}

class Samochod implements Dojezdzac {
    public void dojezdzaj(){
        System.out.println("Dojazd: samochod");
    }
}

class Rower implements Dojezdzac {
    public void dojezdzaj(){
        System.out.println("Dojazd: rower");
    }
}

class Silownia implements SpedzanieWolnegoCzasu {
    public void spedzajWolnyCzas(){
        System.out.println("Wolny Czas: silownia");
    }
}

class LiteraturaPopularnoNaukowa implements SpedzanieWolnegoCzasu {
    public void spedzajWolnyCzas(){
        System.out.println("Wolny Czas: literatura popularno-naukowa");
    }
}

//kontekst
class Pracownik {
    private Pracowac pracowac = null;
    private Dojezdzac dojezdzac = null;
    private SpedzanieWolnegoCzasu spedzanieWolnegoCzasu = null;

    public Pracownik(String zawod){
        if(zawod.equalsIgnoreCase("mechanik")){
            pracowac = new NaprawaSamochodow();
            dojezdzac = new Samochod();
            spedzanieWolnegoCzasu = new Silownia();
        }
        else if(zawod.equalsIgnoreCase("lekarz")){
            pracowac = new Leczenie();
            dojezdzac = new Samochod();
            spedzanieWolnegoCzasu = new LiteraturaPopularnoNaukowa();
        }
        else if(zawod.equalsIgnoreCase("listonosz")){
            pracowac = new RoznoszenieListow();
            dojezdzac = new Rower();
            spedzanieWolnegoCzasu = new Silownia();
        }
    }

    //tutaj wywolujemy algorytmy
    public void methods(){
        pracowac.pracuj();
        dojezdzac.dojezdzaj();
        spedzanieWolnegoCzasu.spedzajWolnyCzas();
    }

    public static void main(String[]args){
        Scanner in = new Scanner(System.in);
        String zawod = in.nextLine();

        try {
            Pracownik pracownik = new Pracownik(zawod);
            pracownik.methods();
        }catch(Exception e){
            System.out.println("nie ma takiego zawodu");
        }
    }
}
