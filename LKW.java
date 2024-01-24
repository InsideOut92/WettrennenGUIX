public class LKW extends Fahrzeug{
    // Ladekapazität in Tonnen
    private int cargoCapacity;

    public LKW(){
    }

    public LKW(int cargoCapacity){
        this.cargoCapacity = cargoCapacity;
    }

    public LKW(double tankvolumen, double tankinhalt, double verbrauch, double maxSpeed, double currentSpeed,
               String kennzeichen, String color, String driver, double strecke, double money,
               double reifenProzent, int cargoCapacity){
        super(tankvolumen, tankinhalt, verbrauch, maxSpeed, currentSpeed, kennzeichen, color, driver, strecke, money,
                reifenProzent);
        this.cargoCapacity = cargoCapacity;
    }

    public LKW(double tankvolumen, double tankinhalt, double verbrauch, double maxSpeed, double currentSpeed,
               String kennzeichen, String color, String driver, double strecke, double rennstrecke,
               boolean isFueling, double money, double reifenProzent, int cargoCapacity){
        super(tankvolumen, tankinhalt, verbrauch, maxSpeed, currentSpeed, kennzeichen, color, driver, strecke,
                rennstrecke, isFueling, money, reifenProzent);
        this.cargoCapacity = cargoCapacity;
    }

    public int getCargoCapacity(){
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity){
        this.cargoCapacity = cargoCapacity;
    }

    // Methode zum Hupen
    public void honk(){
        System.out.println("DRÖÖÖÖÖÖHN! Der LKW mit Kennzeichen " + getKennzeichen() + " hupt.");
    }

    @Override
    public String toString(){
        String truckInformation = "Der LKW mit dem Kennzeichen " + getKennzeichen() + " hat einen " +
                "Kilometerstand von " + round(this.getStrecke(), 2) + " km, fährt derzeit " +
                getCurrentSpeed() + " km/h\nschnell und verbraucht dabei " + getVebrauch() +
                " l/100km und hat noch " + round(getTankinhalt(), 2) + " l im Tank.\nDerzeitiger Fahrer ist: " +
                getDriver() + ", das Fahrzeug hat die Farbe: " + getColor() + " und im aktuellen Rennen: " +
                getRennstrecke() + " km zurückgelegt.\n" +
                "Ich bin ein LKW, mit einer Ladekapazität von " + getCargoCapacity() + " Tonnen. Nenn mich Brummi.";
        return truckInformation;
    }
}
