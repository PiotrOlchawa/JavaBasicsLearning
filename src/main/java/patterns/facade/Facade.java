package patterns.facade;

/*
Dokładnie tak działa wzorzec Fasada (ang. facade). Ma ukryć przed użytkownikiem skomplikowane wnętrze aplikacji
lub biblioteki czy klas i pokazać mu proste i przejrzyste w odbiorze oblicze.

Przykład:

System sterowania domem, który implementuje wiele różnych podsystemów,
a chcemy mieć jedno uniwersalne API. Więc na wejściu zastosujemy fasadę.

*/


interface Light{
    void on();

    void off();
}

interface Door{
    void open();

    void close();

    void lock();

    void unlock();
}

class HallLight implements Light{

    @Override
    public void on(){

        System.out.println("Hall light on.");

    }

    @Override
    public void off(){

        System.out.println("Hall light off.");

    }
}

class LivingRoomLight implements Light{

    @Override
    public void on(){

        System.out.println("Living room light on.");
    }

    @Override
    public void off(){

        System.out.println("Living room light off.");
    }
}

class MainDoor implements Door{

    @Override
    public void open(){

        System.out.println("Main door open.");
    }

    @Override
    public void close(){

        System.out.println("Main door close.");
    }

    @Override
    public void lock(){

        System.out.println("Main door lock.");
    }

    @Override
    public void unlock(){

        System.out.println("Main door unlock.");
    }
}

class GarageDoor implements Door{

    @Override
    public void open(){

        System.out.println("Garage door open.");
    }

    @Override
    public void close(){

        System.out.println("Garage door close.");
    }

    @Override
    public void lock(){

        System.out.println("Garage door lock.");
    }

    @Override
    public void unlock(){

        System.out.println("Garage door unlock.");
    }
}

class MainApi{

    private Light livingRoomLight = new LivingRoomLight();
    private Light hallLight = new HallLight();
    private Door mainDoor = new MainDoor();
    private Door garageDoor = new GarageDoor();

    public void lockHome(){

        System.out.println("------ LOCK HOME -----");
        livingRoomLight.off();
        hallLight.off();
        mainDoor.lock();
        garageDoor.lock();
    }

    public void unlockHome(){

        System.out.println("------ UNLOCK HOME -----");
        hallLight.on();
        mainDoor.unlock();
        garageDoor.unlock();
    }

    public void lightOff(){

        System.out.println("------ LIGHT OFF -----");
        livingRoomLight.off();
        hallLight.off();
    }

    public void lightOn(){

        System.out.println("------ LIGHT ON -----");
        livingRoomLight.on();
        hallLight.on();
    }
}

public class Facade{

    public static void main(String[] args){

        MainApi mainApi = new MainApi();
        mainApi.lockHome();
        mainApi.unlockHome();
        mainApi.lightOn();
        mainApi.lightOff();
    }
}