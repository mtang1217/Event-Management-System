import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class EventManager {
    List<Event> events;

    EventManager(){
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event){
        events.add(event);
    }
    public List<Event> getEventsByDate(LocalDate date){
        return events.stream()
                .filter(e -> e.geteventDate().toLocalDate().equals(date))
                .collect(Collectors.toList());
        /*
        List<Event> res = new ArrayList<>();
        for (int i = 0; i < events.size(); i++){
            Event event = events.get(i);
            LocalDateTime datetime = event.geteventDate();
            LocalDate temp = datetime.toLocalDate();
            if (temp == date){
                res.add(event);
            }
        }
        return res;
        */
    }
    public List<Event> getAvailableEvents(){
        return events.stream()
                .filter(e -> !e.isFull())
                .collect(Collectors.toList());
        /*
        List<Event> res = new ArrayList<>();
        for (int i = 0; i < events.size(); i++){
            Event event = events.get(i);
            if (!event.isFull()){
                res.add(event);
            }
        }
        return res;
        */
    }
    public Map<String, Long> getEventCountByType(){
        return events.stream()
                .collect(Collectors.groupingBy(
                    e -> e.getClass().getSimpleName(),
                    Collectors.counting()
                ));
    }
    public OptionalDouble getAverageEventCost(){
        return events.stream()
                .mapToDouble(Event::calculateEventCost)
                .average();
    }
    public List<String> getAllParticipants(){
        return events.stream()
                .flatMap(e -> e.getParticipants().stream())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    public Optional<Event> getMostExpensiveEvent(){
        return events.stream()
                .max(Comparator.comparing(Event::calculateEventCost));
    }
    public List<Event> getEventsRequiringEquipment(){
        return events.stream()
                .filter(Event::requiresSpecialEquipment)
                .sorted(Comparator.comparing(Event::geteventDate))
                .collect(Collectors.toList());
    }
    public List<Event> filterEvents(Predicate<? super Event> filter) {
    return events.stream()
            .filter(filter)
            .collect(Collectors.toList());
    }
}
