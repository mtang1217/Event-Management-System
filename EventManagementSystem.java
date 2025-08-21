import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class Event{
    protected String eventName;
    protected String location;
    protected LocalDateTime eventDate;
    protected int maxCapacity;
    protected List<String> registeredParticipants = new ArrayList<>();

    Event(String eventName, String location, LocalDateTime eventDate, int maxCapacity){
        this.eventName = eventName;
        this.location = location;
        this.eventDate = eventDate;
        this.maxCapacity = maxCapacity;
    }

    public void registerParticipant(String participantName){
        if (registeredParticipants.size() < maxCapacity){
            registeredParticipants.add(participantName);
        }
    }
    public int getCurrentRegistrations(){
        return registeredParticipants.size();
    }
    public boolean isFull(){
        return registeredParticipants.size() == maxCapacity;
    }
    public List<String> getParticipants(){
        List<String> copiedList = new ArrayList<>(registeredParticipants);
        return copiedList;
    }
    public String geteventName(){
        return eventName;
    }
    public String getlocation(){
        return location;
    }
    public LocalDateTime geteventDate(){
        return eventDate;
    }
    public int getmaxCapacity(){
        return maxCapacity;
    }

    public abstract double calculateEventCost();
    public abstract String getEventDetails();
    public abstract boolean requiresSpecialEquipment();
}

class Conference extends Event{
    boolean isPaid;
    double registrationFee;

    Conference(String eventName, String location, LocalDateTime eventDate, int maxCapacity, boolean isPaid, double registrationFee){
        super(eventName, location, eventDate, maxCapacity);
        this.isPaid = isPaid;
        this.registrationFee = registrationFee;
    }

    public double calculateEventCost(){
        if (!isPaid) return 0;
        return registrationFee * super.getCurrentRegistrations();
    }
    public String getEventDetails(){
        if (!isPaid) return "No registration fee";
        String details = "Registration Fee: " + registrationFee;
        return details;
    }
    public boolean requiresSpecialEquipment(){
        return true;
    }
}

class Workshop extends Event{
    String skillLevel; //'Beginner' or 'Intermediate' or 'Advanced'
    List<String> materialsNeeded;

    Workshop(String eventName, String location, LocalDateTime eventDate, int maxCapacity, String skillLevel, List<String> materialsNeeded){
        super(eventName, location, eventDate, maxCapacity);
        this.skillLevel = skillLevel;
        this.materialsNeeded = materialsNeeded;
    }

    public double calculateEventCost(){
        return materialsNeeded.size() * 15.0;
    }
    public String getEventDetails(){
        String details = "Skill level: " + skillLevel;
        if (materialsNeeded.size() > 0){
            details += "; Materials needed: ";
            for (int i = 0; i < materialsNeeded.size(); i++){
                details += materialsNeeded.get(i);
                if (i < materialsNeeded.size() - 1){
                    details += ", ";
                }
            }
        }
        return details;
    }
    public boolean requiresSpecialEquipment(){
        if (materialsNeeded.size() > 0){return true;}
        return false;
    }
}

class Tournament extends Event{
    String sport;
    double prizePool;

    Tournament(String eventName, String location, LocalDateTime eventDate, int maxCapacity, String sport, double prizePool){
        super(eventName, location, eventDate, maxCapacity);
        this.sport = sport;
        this.prizePool = prizePool;
    }

    public double calculateEventCost(){
        return maxCapacity * 5.0 + prizePool;
    }
    public String getEventDetails(){
        String details = "Sport: " + sport + "; Prize Pool: " + prizePool;
        return details;
    }
    public boolean requiresSpecialEquipment(){
        return true;
    }
}

public class EventManagementSystem{
    public static void main(String[] args){
        // TEST CASE 1: Conference
        Conference conference = new Conference(
            "Research Conference",
            "NYC",
            LocalDateTime.of(2025, 8, 20, 9, 0),
            10,
            true,
            50.0
        );
        conference.registerParticipant("Alice");
        conference.registerParticipant("Bob");

        System.out.println("=== Conference Test ===");
        System.out.println(conference.getEventDetails());
        System.out.println("Current Registrations: " + conference.getCurrentRegistrations());
        System.out.println("Event Cost: " + conference.calculateEventCost());
        System.out.println("Requires Special Equipment? " + conference.requiresSpecialEquipment());
        System.out.println();

        // TEST CASE 2: Workshop
        Workshop workshop = new Workshop(
            "Java Workshop",
            "San Francisco",
            LocalDateTime.of(2025, 9, 10, 13, 30),
            20,
            "Intermediate",
            Arrays.asList("Laptop", "Notebook", "Pen")
        );
        workshop.registerParticipant("Charlie");
        workshop.registerParticipant("Dana");

        System.out.println("=== Workshop Test ===");
        System.out.println(workshop.getEventDetails());
        System.out.println("Current Registrations: " + workshop.getCurrentRegistrations());
        System.out.println("Event Cost: " + workshop.calculateEventCost());
        System.out.println("Requires Special Equipment? " + workshop.requiresSpecialEquipment());
        System.out.println();

        // TEST CASE 3: Tournament
        Tournament tournament = new Tournament(
            "City Cup",
            "Los Angeles",
            LocalDateTime.of(2025, 10, 5, 10, 0),
            16,
            "Soccer",
            50.0
        );
        tournament.registerParticipant("Team A");
        tournament.registerParticipant("Team B");

        System.out.println("=== Tournament Test ===");
        System.out.println(tournament.getEventDetails());
        System.out.println("Current Registrations: " + tournament.getCurrentRegistrations());
        System.out.println("Event Cost: " + tournament.calculateEventCost());
        System.out.println("Requires Special Equipment? " + tournament.requiresSpecialEquipment());
    }
}
