
package patterns.factory;
//-----------------------------------------------------------------------------------------------MAIN
public class Factory {
    public static void main(String[]args){
        FabrykaKomputerow fabrykaKomputerow = new FabrykaKomputerow();
        System.out.println("PC");
        Komputer pc = fabrykaKomputerow.wydajKomputer("PC");
        System.out.println("\n\nLaptop");
        Komputer laptop = fabrykaKomputerow.wydajKomputer("Laptop");
    }
}
//-------------------------------------------------------------------------------------FABRYKA_CZESCI
interface FabrykaPodzespolowKomputerowych {
    public Grafika produkujemyGrafike();

    public Procesor produkujemyProcesor();
}
//------------------------------------------------------------------------------------KARTA_GRAFICZNA
interface Grafika {
    public Grafika dawajGrafike();

}
class GrafikaDlaPC implements Grafika {
    String name = "GrafikaDlaPC";
    public Grafika dawajGrafike(){
        System.out.println("Dodaje: " + name);
        return new GrafikaDlaPC();
    }

}
class GrafikaDlaLaptopa implements Grafika {
    String name = "GrafikaDlaPC";
    public Grafika dawajGrafike(){
        System.out.println("Dodaje: " + name);
        return new GrafikaDlaPC();
    }
}

//---------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------PROCESOR
interface Procesor {
    public Procesor dawajProcesor();

}
class ProcesorDlaPC implements Procesor {
    String name = "ProcesorDlaPC";
    public Procesor dawajProcesor(){
        System.out.println("Dodaje: " + name);
        return new ProcesorDlaPC();
    }

}
class ProcesorDlaLaptopa implements Procesor {
    String name = "ProcesorDlaLaptopa";
    public Procesor dawajProcesor(){
        System.out.println("Dodaje: " + name);
        return new ProcesorDlaLaptopa();
    }
}

//---------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------CZESCI_DO_PC
class FabrykaPodzespolowPC implements FabrykaPodzespolowKomputerowych {


    public Grafika produkujemyGrafike(){
        return new GrafikaDlaPC();
    }

    public Procesor produkujemyProcesor(){
        return new ProcesorDlaPC();
    }

}
//------------------------------------------------------------------------------------CZESCI_DO_LAPKA
class FabrykaPodzespolowLaptop implements FabrykaPodzespolowKomputerowych{

    public Grafika produkujemyGrafike(){
        return new GrafikaDlaLaptopa();
    }
    public Procesor produkujemyProcesor(){
        return new ProcesorDlaLaptopa();
    }

}
//-------------------------------------------------------------------------------ABSTRAKCYJNY_PRODUKT
abstract class Komputer {
    Grafika grafika;

    Procesor procesor;

    public abstract void skladanie();

    public void instalowanieOprogramowania(){
        System.out.println("Instaluje oprogramowanie...");
    }
    public void pakowanie(){
        System.out.println("Pakuje sprzet...");
    }
    public void sprzedawanie(){
        System.out.println("Sprzedaje sprzet...");
    }

}
//-------------------------------------------------------------------------------------TUTAJ_SKLADAMY
class FabrykaKomputerow {

    public Komputer wydajKomputer(String model){
        Komputer komputer = zlozKomputer(model);
        komputer.instalowanieOprogramowania();
        komputer.pakowanie();
        komputer.sprzedawanie();
        return komputer;
    }
    protected Komputer zlozKomputer(String model){
        Komputer komputer = null;
        if(model.equalsIgnoreCase("PC")){
            komputer =  new KomputerPC(new FabrykaPodzespolowPC());
        } else if(model.equalsIgnoreCase("Laptop")){
            komputer = new Laptop(new FabrykaPodzespolowLaptop());
        }
        return komputer;
    }

}
//-------------------------------------------------------------------------------------------------PC
class KomputerPC extends Komputer {

    FabrykaPodzespolowKomputerowych fabrykaPodzespolowKomputerowych;

    public KomputerPC(FabrykaPodzespolowKomputerowych f){
        fabrykaPodzespolowKomputerowych = f;
        skladanie();
    }
    public void skladanie(){
        grafika = fabrykaPodzespolowKomputerowych.produkujemyGrafike().dawajGrafike();
        procesor = fabrykaPodzespolowKomputerowych.produkujemyProcesor().dawajProcesor();
    }

}
//----------------------------------------------------------------------------------------------LAPEK
class Laptop extends Komputer {

    FabrykaPodzespolowLaptop fabrykaPodzespolowLaptop;

    public Laptop(FabrykaPodzespolowLaptop fabrykaPodzespolowLaptop){
        this.fabrykaPodzespolowLaptop = fabrykaPodzespolowLaptop;
        skladanie();
    }
    public void skladanie(){
        grafika = fabrykaPodzespolowLaptop.produkujemyGrafike().dawajGrafike();
        procesor = fabrykaPodzespolowLaptop.produkujemyProcesor().dawajProcesor();

    }

}
