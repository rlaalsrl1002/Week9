import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingManager {
    private HashMap<String, ParkingInfo> parkingMap;

    public ParkingManager() {
        parkingMap = new HashMap<>();
    }

    public void enterCar(String carNumber, String carType) {
        LocalDateTime entryTime = LocalDateTime.now();
        ParkingInfo info = new ParkingInfo(carNumber, carType, entryTime);
        parkingMap.put(carNumber, info);
        System.out.println(carNumber + " 차량이 입차되었습니다.");
    }

    public void exitCar(String carNumber) {
        ParkingInfo info = parkingMap.get(carNumber);
        if (info == null) {
            System.out.println("주차된 차량이 아닙니다.");
            return;
        }

        LocalDateTime exitTime = LocalDateTime.now();
        long durationMinutes = info.getEntryTime().until(exitTime, ChronoUnit.MINUTES);
        int parkingFee = calculateParkingFee(durationMinutes);
        System.out.println("Time: " + durationMinutes + "분");
        System.out.println("Money: " + parkingFee + "원");

        parkingMap.remove(carNumber);
    }

    private int calculateParkingFee(long durationMinutes) {
        if (durationMinutes <= 10) {
            return 0; // 무료 주차
        } else {
            long remainingMinutes = durationMinutes - 10;
            return (int) (remainingMinutes / 10) * 500 + 500; // 10분 단위로 500원씩 부과
        }
    }

    public void listParkingInfo() {
        System.out.println("Parking Info:");
        for (ParkingInfo info : parkingMap.values()) {
            System.out.println("CarNumber: " + info.getCarNumber() + ", Type: " + info.getCarType() +
                    ", Time: " + info.getEntryTime());
        }
    }

    public static void main(String[] args) {
        ParkingManager manager = new ParkingManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n주차 관리 기능을 선택하세요:");
            System.out.println("1)enter 2)exit  3)list  4)quit");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Number: ");
                    String carNumber = scanner.nextLine();
                    System.out.print("type: ");
                    String carType = scanner.nextLine();
                    manager.enterCar(carNumber, carType);
                    break;
                case "2":
                    System.out.print("Out Car number: ");
                    carNumber = scanner.nextLine();
                    manager.exitCar(carNumber);
                    break;
                case "3":
                    manager.listParkingInfo();
                    break;
                case "4":
                    System.out.println("Exist.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Wrong choice.");
            }
        }
    }
}
