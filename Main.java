import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // === TEST CASE 1: Conference ===
        Conference conference = new Conference(
            "Research Conference",
            "NYC",
            LocalDateTime.of(2025, 8, 20, 9, 0),
            2,   // small capacity to test full event handling
            true,
            50.0
        );

        try {
            conference.registerParticipant("Alice");
            conference.registerParticipant("Bob");
            conference.registerParticipant("Charlie"); // exceeds capacity
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("=== Conference Test ===");
        System.out.println(conference.getEventDetails());
        System.out.println("Current Registrations: " + conference.getCurrentRegistrations());
        System.out.println("Event Cost: " + conference.calculateEventCost());
        System.out.println("Requires Special Equipment? " + conference.requiresSpecialEquipment());
        System.out.println();

        // === TEST CASE 2: Workshop ===
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

        // === TEST CASE 3: Tournament ===
        Tournament tournament = new Tournament(
            "City Cup",
            "Los Angeles",
            LocalDateTime.of(2025, 10, 5, 10, 0),
            16,
            "Soccer",
            500.0
        );
        tournament.registerParticipant("Team A");
        tournament.registerParticipant("Team B");

        System.out.println("=== Tournament Test ===");
        System.out.println(tournament.getEventDetails());
        System.out.println("Current Registrations: " + tournament.getCurrentRegistrations());
        System.out.println("Event Cost: " + tournament.calculateEventCost());
        System.out.println("Requires Special Equipment? " + tournament.requiresSpecialEquipment());
        System.out.println();

        // === EventManager Demonstration ===
        EventManager manager = new EventManager();
        manager.addEvent(conference);
        manager.addEvent(workshop);
        manager.addEvent(tournament);

        System.out.println("=== EventManager Tests ===");

        // Events on specific date
        System.out.println("Events on 2025-08-20: " + 
            manager.getEventsByDate(LocalDate.of(2025, 8, 20))
                .stream()
                .map(Event::geteventName)
                .toList()
        );

        // Available events
        System.out.println("Available Events: " + 
            manager.getAvailableEvents()
                .stream()
                .map(Event::geteventName)
                .toList()
        );

        // Event Count by Type
        System.out.println("Event Count by Type: " + manager.getEventCountByType());

        // Average event cost
        System.out.println("Average Event Cost: " + manager.getAverageEventCost().orElse(0.0));

        // All participants
        System.out.println("All Participants: " + manager.getAllParticipants());

        // Most expensive event
        Optional<Event> mostExpensive = manager.getMostExpensiveEvent();
        mostExpensive.ifPresent(e -> 
            System.out.println("Most Expensive Event: " + e.geteventName() + " (Cost: " + e.calculateEventCost() + ")")
        );

        // Events requiring equipment
        System.out.println("Events Requiring Equipment: " + 
            manager.getEventsRequiringEquipment()
                .stream()
                .map(Event::geteventName)
                .toList()
        );

        // Bonus: Custom filtering
        System.out.println("Filtered Events (cost > 100): " + 
            manager.filterEvents(e -> e.calculateEventCost() > 100)
                .stream()
                .map(Event::geteventName)
                .toList()
        );
    }
}
