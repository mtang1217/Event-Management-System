import java.time.LocalDateTime;
import java.util.List;


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
            List.of("Projector", "Whiteboard", "Markers")

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