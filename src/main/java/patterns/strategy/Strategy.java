package patterns.strategy;

import java.util.Scanner;

/*Wzorzec Strategy (pol. strategia) polega na możliwości "wstrzykiwania" całych kawałków kodu,
 realizujących w różny sposób pewne zadania (w zależności od tego, jaką przyjmiemy strategię działania). */

interface DeliveryInfo {

    void showAdress();

    void showVehicleInfo();

    void showMethod();
}


class FeDex implements DeliveryInfo {

    @Override
    public void showAdress() {
        System.out.println("Tarnów");
    }

    @Override
    public void showVehicleInfo() {
        System.out.println("Car - Transporter");
    }

    @Override
    public void showMethod() {
        System.out.println("Express");
    }
}

class Polamer implements DeliveryInfo {

    @Override
    public void showAdress() {
        System.out.println("Brzesko");
    }

    @Override
    public void showVehicleInfo() {
        System.out.println("Car - mini");
    }

    @Override
    public void showMethod() {
        System.out.println("Normal");
    }
}

public class Strategy {

    void showInfo(DeliveryInfo deliveryInfo) {
        deliveryInfo.showAdress();
        deliveryInfo.showMethod();
        deliveryInfo.showVehicleInfo();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String deliverer = scanner.nextLine();
        Strategy strategy = new Strategy();

        switch (deliverer) {
            case "fedex":
                strategy.showInfo(new FeDex());
                break;
            case "polamer":
                strategy.showInfo(new Polamer());
                break;
            default:
                System.out.println("No correct deliverer");
        }
    }
}



