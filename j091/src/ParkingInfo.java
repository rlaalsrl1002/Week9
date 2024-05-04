import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class ParkingInfo {
    private String carNumber;
    private String carType;
    private LocalDateTime entryTime;

    public ParkingInfo(String carNumber, String carType, LocalDateTime entryTime) {
        this.carNumber = carNumber;
        this.carType = carType;
        this.entryTime = entryTime;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getCarType() {
        return carType;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
