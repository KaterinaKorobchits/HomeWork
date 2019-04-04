import javafx.util.Pair;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Logic {

    static Day[] daysFromFile;
    static Day[] daysFromUrl;

    static ObjectMapper objectMapper = new ObjectMapper();

    static void combineDataFromFileAndServer() {
        if (daysFromFile != null)
            for (Day d : daysFromFile)
                Dairy.getInstance().dairy.put(d.id, d);
        if (daysFromUrl != null)
            for (Day d : daysFromUrl) {
                if (Dairy.getInstance().dairy.containsKey(d.id) && !(Dairy.getInstance().dairy.get(d.id).equals(d))) {
                    System.out.print("Найдены записи с одинаковым ID = " + d.id + ". Версию с файла или сервера оставить? (F/S) -> ");
                    String tempFromFileOrServer = DairyPrintStream.in.nextLine();
                    if (tempFromFileOrServer.equals("F"))
                        continue;
                }
                Dairy.getInstance().dairy.put(d.id, d);
            }
    }

    static void getLastIDForDaysAndEvents() {
        TreeSet<Integer> listIdDays = new TreeSet<>(Dairy.getInstance().dairy.keySet());
        if (listIdDays.size() == 0)
            listIdDays.add(0);
        Day.listID = listIdDays;

        TreeSet<Long> listIdEvents = new TreeSet<>();
        for (Day d : Dairy.getInstance().dairy.values()) {
            if (d.listOfDailyEvent == null)
                d.listOfDailyEvent = new ArrayList<>();
            if (d.listOfPlannedEvent == null)
                d.listOfPlannedEvent = new ArrayList<>();
            if (d.listOfUnplannedEvent == null)
                d.listOfUnplannedEvent = new ArrayList<>();

            for (Event e : d.getListAllEvents())
                listIdEvents.add(e.id);
        }
        if (listIdEvents.size() == 0)
            listIdEvents.add(0L);
        Event.listID = listIdEvents;
    }

    static void addDay() {
        String date = DairyPrintStream.getDate();
        boolean isWorkingDay = DairyPrintStream.getIsWorkingDay();
        Day newDay = new Day(date, isWorkingDay);
        Dairy.getInstance().dairy.put(newDay.id, newDay);
    }

    static void changeDay() {
        int id = DairyPrintStream.getDayOnID();
        if (Dairy.getInstance().dairy.containsKey(id)) {
            Day newDay = Dairy.getInstance().dairy.get(id);
            System.out.println(newDay.toString());
            int field = DairyPrintStream.getFieldToChangeDay();
            switch (field) {
                case DairyPrintStream.FIELD_DAY_DATE:
                    newDay.date = DairyPrintStream.getDate();
                    Dairy.getInstance().dairy.put(newDay.id, newDay);
                    break;
                case DairyPrintStream.FIELD_DAY_ISWORKING:
                    newDay.isWorkday = DairyPrintStream.getIsWorkingDay();
                    Dairy.getInstance().dairy.put(newDay.id, newDay);
                    break;
            }
        } else
            DairyPrintStream.printDayIDIsNotExist();
    }

    static void findDay() {
        int field = DairyPrintStream.getFieldToFindDay();
        switch (field) {
            case DairyPrintStream.FIELD_DAY_ID:
                int id = DairyPrintStream.getDayOnID();
                if (Dairy.getInstance().dairy.containsKey(id))
                    System.out.println(Dairy.getInstance().dairy.get(id).toString());
                else
                    DairyPrintStream.printDayIDIsNotExist();
                break;
            case DairyPrintStream.FIELD_DAY_DATE:
                String date = DairyPrintStream.getDate();
                for (Day day : Dairy.getInstance().dairy.values())
                    if (day.date.equals(date))
                        System.out.println(day.toString());
                break;
            case DairyPrintStream.FIELD_DAY_ISWORKING:
                boolean isWorkday = DairyPrintStream.getIsWorkingDay();
                for (Day day : Dairy.getInstance().dairy.values())
                    if (day.isWorkday == isWorkday)
                        System.out.println(day.toString());
                break;
        }
    }

    static void deleteDay() {
        int id = DairyPrintStream.getDayOnID();
        if (Dairy.getInstance().dairy.containsKey(id))
            Dairy.getInstance().dairy.remove(id);
        else
            DairyPrintStream.printDayIDIsNotExist();
    }

    static void addEvent(Day day) {
        Pattern patternType = Pattern.compile("^[1-3]$");
        System.out.print("Введите тип события(1 - запланированное, 2 - незапланированное, 3 - ежедневное) -> ");
        int type = Integer.parseInt(DairyPrintStream.getValidField(patternType));
        String category = DairyPrintStream.getCategoryEvent();
        int productivity = DairyPrintStream.getRatingProductivity();
        String whatIsDone = DairyPrintStream.getWhatIsDone();
        String comment = DairyPrintStream.getComment();
        int realTime;
        String whatNeededToBeDone;
        switch (type) {
            case 1:
                int plannedTime = DairyPrintStream.getPlannedTime();
                realTime = DairyPrintStream.getRealTime();
                whatNeededToBeDone = DairyPrintStream.getWhatNeededToBeDone();
                Dairy.getInstance().dairy.get(day.id).listOfPlannedEvent.add(new PlannedEvent(category, comment, productivity, whatIsDone, plannedTime, realTime, whatNeededToBeDone));
                break;
            case 2:
                realTime = DairyPrintStream.getRealTime();
                String whatHappens = DairyPrintStream.getWhatHappens();
                Dairy.getInstance().dairy.get(day.id).listOfUnplannedEvent.add(new UnplannedEvent(category, comment, productivity, whatIsDone, realTime, whatHappens));
                break;
            case 3:
                whatNeededToBeDone = DairyPrintStream.getWhatNeededToBeDone();
                Dairy.getInstance().dairy.get(day.id).listOfDailyEvent.add(new DailyEvent(category, comment, productivity, whatIsDone, whatNeededToBeDone));
                break;
        }
    }

    static void changeEvent(Day day) {
        int id = DairyPrintStream.getEventOnID();
        for (Event e : day.getListAllEvents())
            if (e.id == id) {
                Event newEvent = e;
                System.out.println(e.toString());
                int field = DairyPrintStream.getFieldToChangeEvent(newEvent);
                switch (field) {
                    case 1:
                        newEvent.category = DairyPrintStream.getCategoryEvent();
                        break;
                    case 2:
                        newEvent.productivityRating = DairyPrintStream.getRatingProductivity();
                        break;
                    case 3:
                        newEvent.whatIsDone = DairyPrintStream.getWhatIsDone();
                        break;
                    case 4:
                        newEvent.comment = DairyPrintStream.getComment();
                        break;
                    case 5:
                        if (newEvent instanceof PlannedEvent) {
                            ((PlannedEvent) newEvent).plannedTime = DairyPrintStream.getPlannedTime();
                        } else if (newEvent instanceof UnplannedEvent) {
                            ((UnplannedEvent) newEvent).realTime = DairyPrintStream.getRealTime();
                        } else if (newEvent instanceof DailyEvent) {
                            ((DailyEvent) newEvent).whatNeededToBeDone = DairyPrintStream.getWhatNeededToBeDone();
                        }
                        break;
                    case 6:
                        if (newEvent instanceof PlannedEvent) {
                            ((PlannedEvent) newEvent).realTime = DairyPrintStream.getRealTime();
                        } else if (newEvent instanceof UnplannedEvent) {
                            ((UnplannedEvent) newEvent).whatHappens = DairyPrintStream.getWhatHappens();
                        }
                        break;
                    case 7:
                        ((PlannedEvent) newEvent).whatNeededToBeDone = DairyPrintStream.getWhatNeededToBeDone();
                        break;
                }
                if (newEvent instanceof PlannedEvent) {
                    Dairy.getInstance().dairy.get(day.id).listOfPlannedEvent.remove(e);
                    Dairy.getInstance().dairy.get(day.id).listOfPlannedEvent.add((PlannedEvent) newEvent);
                } else if (newEvent instanceof UnplannedEvent) {
                    Dairy.getInstance().dairy.get(day.id).listOfUnplannedEvent.remove(e);
                    Dairy.getInstance().dairy.get(day.id).listOfUnplannedEvent.add((UnplannedEvent) newEvent);
                } else if (newEvent instanceof DailyEvent) {
                    Dairy.getInstance().dairy.get(day.id).listOfDailyEvent.remove(e);
                    Dairy.getInstance().dairy.get(day.id).listOfDailyEvent.add((DailyEvent) newEvent);
                }
                return;
            }
        DairyPrintStream.printEventIDIsNotExist();
    }

    static void findEvent(Day day) {
        int field = DairyPrintStream.getFieldToFindEvent();
        switch (field) {
            case DairyPrintStream.FIELD_EVENT_ID:
                int id = DairyPrintStream.getEventOnID();
                for (Event e : day.getListAllEvents())
                    if (e.id == id) {
                        System.out.println(e.toString());
                        return;
                    }
                DairyPrintStream.printEventIDIsNotExist();
                break;
            case DairyPrintStream.FIELD_EVENT_CATEGORY:
                String category = DairyPrintStream.getCategoryEvent();
                for (Event e : day.getListAllEvents())
                    if (e.category.equals(category))
                        System.out.println(e.toString());
                break;
            case DairyPrintStream.FIELD_EVENT_RATING_PRODUCTIVITY:
                int productivityRating = DairyPrintStream.getRatingProductivity();
                for (Event e : day.getListAllEvents())
                    if (e.productivityRating == productivityRating)
                        System.out.println(e.toString());
                break;
            case DairyPrintStream.FIELD_EVENT_WHAT_IS_DONE:
                String whatIsDone = DairyPrintStream.getWhatIsDone();
                for (Event e : day.getListAllEvents())
                    if (e.whatIsDone.equals(whatIsDone))
                        System.out.println(e.toString());
                break;
            case DairyPrintStream.FIELD_EVENT_COMMENT:
                String comment = DairyPrintStream.getComment();
                for (Event e : day.getListAllEvents())
                    if (e.category.equals(comment))
                        System.out.println(e.toString());
                break;
            case DairyPrintStream.FIELD_EVENT_PLANNED_TIME:
                int plannedTime = DairyPrintStream.getPlannedTime();
                for (PlannedEvent e : day.listOfPlannedEvent)
                    if (e.plannedTime == plannedTime)
                        System.out.println(e.toString());
                break;
            case DairyPrintStream.FIELD_EVENT_REAL_TIME:
                int realTime = DairyPrintStream.getRealTime();
                for (PlannedEvent e : day.listOfPlannedEvent)
                    if (e.realTime == realTime)
                        System.out.println(e.toString());
                for (UnplannedEvent e : day.listOfUnplannedEvent)
                    if (e.realTime == realTime)
                        System.out.println(e.toString());
                break;
            case DairyPrintStream.FIELD_EVENT_WHAT_NEEDED_TO_BE_DONE:
                String whatNeededToBeDone = DairyPrintStream.getWhatNeededToBeDone();
                for (PlannedEvent e : day.listOfPlannedEvent)
                    if (e.whatNeededToBeDone.equals(whatNeededToBeDone))
                        System.out.println(e.toString());
                for (DailyEvent e : day.listOfDailyEvent)
                    if (e.whatNeededToBeDone.equals(whatNeededToBeDone))
                        System.out.println(e.toString());
                break;
            case DairyPrintStream.FIELD_EVENT_WHAT_HAPPENS:
                String whatHappens = DairyPrintStream.getWhatHappens();
                for (UnplannedEvent e : day.listOfUnplannedEvent)
                    if (e.whatHappens.equals(whatHappens))
                        System.out.println(e.toString());
        }

    }

    static void deleteEvent(Day day) {
        int id = DairyPrintStream.getEventOnID();
        for (Event e : day.getListAllEvents())
            if (e.id == id) {
                if (e instanceof PlannedEvent)
                    Dairy.getInstance().dairy.get(day.id).listOfPlannedEvent.remove(e);
                else if (e instanceof UnplannedEvent)
                    Dairy.getInstance().dairy.get(day.id).listOfUnplannedEvent.remove(e);
                else if (e instanceof DailyEvent)
                    Dairy.getInstance().dairy.get(day.id).listOfDailyEvent.remove(e);
                return;
            }
        DairyPrintStream.printEventIDIsNotExist();
    }

    static Day chooseDay() {
        int id = DairyPrintStream.getDayOnID();
        Day day = Dairy.getInstance().dairy.get(id);
        return day;
    }

    static int getProductivity() {
        int sum = 0;
        for (Day day : Dairy.getInstance().dairy.values())
            for (Event e : day.getListAllEvents())
                sum += e.productivityRating;
        return sum;
    }

    static HashMap<String, Pair<Integer, Double>> getProductivityOnCategory() {
        HashMap<String, Pair<Integer, Double>> mapProductivity = new HashMap<>();
        for (Event e : getListAllEvents()) {
            if (mapProductivity.containsKey(e.category)) {
                int count = mapProductivity.get(e.category).getKey();
                double prod = mapProductivity.get(e.category).getValue();
                mapProductivity.put(e.category, new Pair(count + 1, prod + e.productivityRating));
            } else
                mapProductivity.put(e.category, new Pair(1, (double) e.productivityRating));
        }
        return mapProductivity;
    }

    static ArrayList<Event> getListAllEvents() {
        ArrayList<Event> list = new ArrayList<>();
        for (Day d : Dairy.getInstance().dairy.values())
            list.addAll(d.getListAllEvents());
        return list;
    }

    static boolean isDataSavedToFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String s;
            while ((s = reader.readLine()) != null) {
                stringBuilder.append(s);
            }
            if (objectMapper.writeValueAsString(Dairy.getInstance().dairy.values()).equals(stringBuilder.toString()))
                return true;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return false;
    }

    static void saveDataToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(objectMapper.writeValueAsString(Dairy.getInstance().dairy.values()));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
