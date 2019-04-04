import javafx.util.Pair;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Day {

    public int id;
    public String date;
    public boolean isWorkday;
    public ArrayList<PlannedEvent> listOfPlannedEvent;
    public ArrayList<UnplannedEvent> listOfUnplannedEvent;
    public ArrayList<DailyEvent> listOfDailyEvent;
    public static TreeSet<Integer> listID = new TreeSet<>();

    public Day() {
    }

    public Day(String date, boolean isWorkday) {
        id = listID.last() + 1;
        listID.add(id);
        this.date = date;
        this.isWorkday = isWorkday;
        listOfDailyEvent = new ArrayList<>();
        listOfPlannedEvent = new ArrayList<>();
        listOfUnplannedEvent = new ArrayList<>();
    }

    void infoShort() {
        System.out.printf("%-10d %-10s %-9s %16s %17s %15s %n", id, date, isWorkday ? "рабочий" : "нерабочий",
                listOfUnplannedEvent == null ? "-" : listOfPlannedEvent.size(),
                listOfUnplannedEvent == null ? "-" : listOfUnplannedEvent.size(), listOfDailyEvent == null ? "-" : listOfDailyEvent.size());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Day d = (Day) obj;
        return id == d.id && (date != null && date.equals(d.date)) && isWorkday == d.isWorkday &&
                (listOfDailyEvent != null && listOfDailyEvent.equals(d.listOfDailyEvent)) &&
                (listOfPlannedEvent != null && listOfPlannedEvent.equals(d.listOfPlannedEvent)) &&
                (listOfUnplannedEvent != null && listOfUnplannedEvent.equals(d.listOfUnplannedEvent));
    }

    @Override
    public String toString() {
        return "ID = " + id + " Дата: " + date + (isWorkday ? " рабочий" : " нерабочий") + " день";
                /*"\n-> Список запланированных событий: " + listOfPlannedEvent.toString() +
                "\n-> Список незапланированных событий: " + listOfUnplannedEvent.toString() +
                "\n-> Список ежедневных событий: " + listOfDailyEvent.toString();*/
    }

    @JsonIgnore
    public int getSummaryProductivity() {
        int sum = 0;
        for(Event e : getListAllEvents())
            sum += e.productivityRating;
        return sum;
    }

    //ключ - категория, значение - <кол-во с такой категорий, сумма продуктивности>
    @JsonIgnore
    public HashMap<String, Pair<Integer, Double>> getProductivityOnCategory() {
        HashMap<String, Pair<Integer,Double>> productivity = new HashMap<>();
        for(Event e : getListAllEvents()) {
            if (productivity.containsKey(e.category)) {
                int number = productivity.get(e.category).getKey();
                double prod = productivity.get(e.category).getValue();
                productivity.put(e.category, new Pair(number + 1, prod + e.productivityRating));
            } else
                productivity.put(e.category, new Pair(1, (double)e.productivityRating));
        }
        return productivity;
    }

    @JsonIgnore
    public ArrayList<Event> getListAllEvents() {
        ArrayList<Event> listEvents = new ArrayList<>(listOfUnplannedEvent);
        listEvents.addAll(listOfDailyEvent);
        listEvents.addAll(listOfPlannedEvent);
        return listEvents;
    }
}
