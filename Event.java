import java.time.LocalDateTime;
import java.util.ArrayList;
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
        if (isFull()) {
            throw new IllegalStateException("Event is full: cannot add " + participantName);
        }
        registeredParticipants.add(participantName);
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

    @Override
    public double calculateEventCost(){
        if (!isPaid) return 0;
        return registrationFee * super.getCurrentRegistrations();
    }
    @Override
    public String getEventDetails(){
        if (!isPaid) return "No registration fee";
        String details = "Registration Fee: " + registrationFee;
        return details;
    }
    @Override
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

    @Override
    public double calculateEventCost(){
        return materialsNeeded.size() * 15.0;
    }
    @Override
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
    @Override
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

    @Override
    public double calculateEventCost(){
        return maxCapacity * 5.0 + prizePool;
    }
    @Override
    public String getEventDetails(){
        String details = "Sport: " + sport + "; Prize Pool: " + prizePool;
        return details;
    }
    @Override
    public boolean requiresSpecialEquipment(){
        return true;
    }
}


