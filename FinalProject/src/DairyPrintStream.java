import javafx.util.Pair;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class DairyPrintStream {

    public static final int MENU_BACK_TO_MAIN_MENU = 0;
    public static final int MENU_SHOW_DAIRY_ALL_DAYS = 1;
    public static final int MENU_SHOW_DAIRY_ALL_EVENTS = 2;
    public static final int MENU_SHOW_SORT_DAYS_BY = 3;
    public static final int MENU_ADD_DAY = 4;
    public static final int MENU_CHANGE_DAY = 5;
    public static final int MENU_DELETE_DAY = 6;
    public static final int MENU_FIND_DAY = 7;
    public static final int MENU_CHOOSE_DAY = 8;
    public static final int MENU_SAVE_TO_FILE = 10;
    public static final int MENU_HTML_REPORT = 11;
    public static final int MENU_SHOW_STATISTICS = 12;
    public static final int MENU_EXIT = 13;
    public static final int MENU_SHOW_ALL_EVENTS_DAY = 14;
    public static final int MENU_SHOW_ALL_EVENTS_DAY_SORTED_BY = 15;
    public static final int MENU_ADD_EVENT = 16;
    public static final int MENU_CHANGE_EVENT = 17;
    public static final int MENU_DELETE_EVENT = 18;
    public static final int MENU_FIND_EVENT = 19;
    public static final int MENU_SHOW_STATISTICS_DAY = 20;

    public static final int FIELD_DAY_ID = 0;
    public static final int FIELD_DAY_DATE = 1;
    public static final int FIELD_DAY_ISWORKING = 2;
    public static final int FIELD_EVENT_ID = 0;
    public static final int FIELD_EVENT_CATEGORY = 1;
    public static final int FIELD_EVENT_RATING_PRODUCTIVITY = 2;
    public static final int FIELD_EVENT_WHAT_IS_DONE = 3;
    public static final int FIELD_EVENT_COMMENT = 4;
    public static final int FIELD_EVENT_PLANNED_TIME = 5;
    public static final int FIELD_EVENT_REAL_TIME = 6;
    public static final int FIELD_EVENT_WHAT_NEEDED_TO_BE_DONE = 7;
    public static final int FIELD_EVENT_WHAT_HAPPENS = 8;

    private static final Pattern patternProductivity = Pattern.compile("^([0-9]|10)$");
    private static final Pattern patternTime = Pattern.compile("^(([0-1][0-9])|([2][0-4])):([0-5][0-9])$");

    static Scanner in = new Scanner(System.in);

    static void showMenu() {
        String format = "%-3d%s%n";
        System.out.println("============== ГЛАВНОЕ МЕНЮ ==============");
        System.out.printf(format, MENU_SHOW_DAIRY_ALL_DAYS, "-> Вывести все записи ежедневника");
        System.out.printf(format, MENU_SHOW_DAIRY_ALL_EVENTS, "-> Вывести все события ежедневника");
        System.out.printf(format, MENU_SHOW_SORT_DAYS_BY, "-> Вывести все дни ежедневника (сортировка по указанному полю)");
        System.out.printf(format, MENU_ADD_DAY, "-> Создать день");
        System.out.printf(format, MENU_CHANGE_DAY, "-> Изменить день");
        System.out.printf(format, MENU_DELETE_DAY, "-> Удалить день по ID");
        System.out.printf(format, MENU_FIND_DAY, "-> Найти день(и)");
        System.out.printf("\033[32;1m%-3d%s%n", MENU_CHOOSE_DAY, "-> Выбрать день");
        System.out.printf("\033[36;1m%-3d%s%n", MENU_SAVE_TO_FILE, "-> Сохранить в файл");
        System.out.printf(format, MENU_HTML_REPORT, "-> Сформировать html отчет");
        System.out.printf(format, MENU_SHOW_STATISTICS, "-> Вывести статистику");
        System.out.printf("\033[31m%-3d%s%n", MENU_EXIT, "-> Выйти из программы");
        System.out.println("\033[0;39m==========================================");
    }

    static void showSubMenu() {
        String format = "%-3d%s%n";
        System.out.println("================= МЕНЮ =================");
        System.out.printf(format, MENU_SHOW_ALL_EVENTS_DAY, "-> Вывести все события дня");
        System.out.printf(format, MENU_SHOW_ALL_EVENTS_DAY_SORTED_BY, "-> Вывести все собитя дня (сортировка по указанному полю)");
        System.out.printf(format, MENU_ADD_EVENT, "-> Создать событие");
        System.out.printf(format, MENU_CHANGE_EVENT, "-> Изменить событие");
        System.out.printf(format, MENU_DELETE_EVENT, "-> Удалить событие по ID");
        System.out.printf(format, MENU_FIND_EVENT, "-> Найти событие(я)");
        System.out.printf("\033[36;1m%-3d%s%n", MENU_SHOW_STATISTICS_DAY, "-> Вывести статистику");
        System.out.printf("\033[31m%-3d%s%n", MENU_BACK_TO_MAIN_MENU, "-> Вернуться в главное меню");
        System.out.println("\033[0;39m=========================================");
    }

    static void showDairyAllDays() {
        if (Dairy.getInstance().dairy.size() == 0)
            System.out.println("*** Ежедневник пуст! ***");
        else {
            String format = "%-10s %-10s %-9s %-16s %-17s %-15s\n";
            System.out.printf(format, "ID", "Дата", "Рабочий/", "Кол-во заплани-", "Кол-во незаплани-", "Кол-во ежеднев-");
            System.out.printf(format, "", "", "Нерабочий", "рованных событий", "рованных событий", "ных событий");
            System.out.println("---------- ---------- --------- ---------------- ----------------- ---------------");
            Map<Integer, Day> sorted = new TreeMap<>(Dairy.getInstance().dairy);
            for (Day d : sorted.values())
                d.infoShort();
        }
    }

    static void showDairyAllEvents() {
        List<Event> listAllEvents = Logic.getListAllEvents();
        Collections.sort(listAllEvents, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return Long.compare(o1.id, o2.id);
            }
        });
        if (listAllEvents.size() == 0)
            System.out.println("*** В ежедневнике нет событий! ***");
        else
            for (Event e : listAllEvents)
                System.out.println(e.toString());

    }

    static void showDaysSortedBy() {
        int field = getFieldToFindDay();
        if (Dairy.getInstance().dairy.size() == 0)
            System.out.println("*** Ежедневник пуст! ***");
        else {
            ArrayList<Day> list = new ArrayList<>();
            for(Day day : Dairy.getInstance().dairy.values())
                list.add(day);
            switch (field) {
                case DairyPrintStream.FIELD_DAY_ID:
                    Collections.sort(list, new Comparator<Day>() {
                        public int compare(Day o1, Day o2) {
                            return Integer.compare(o1.id, o2.id);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_DAY_DATE:
                    Collections.sort(list, new Comparator<Day>() {
                        public int compare(Day o1, Day o2) {
                            return o1.date.compareTo(o2.date);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_DAY_ISWORKING:
                    Collections.sort(list, new Comparator<Day>() {
                        public int compare(Day o1, Day o2) {
                            return Boolean.compare(o1.isWorkday, o2.isWorkday);
                        }
                    });
                break;
            }
            for(Day d : list)
                System.out.println(d.toString());
        }
    }

    static void showDayEventsSortedBy(Day day) {
        if(Logic.getListAllEvents().size() == 0)
            System.out.println("*** Список событий пуст! ***");
        else {
            int field = getFieldToFindEvent();
            ArrayList<Event> list = day.getListAllEvents();
            switch (field){
                case DairyPrintStream.FIELD_EVENT_ID:
                    Collections.sort(list, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            return Long.compare(o1.id, o2.id);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_EVENT_CATEGORY:
                    Collections.sort(list, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            return o1.category.compareTo(o2.category);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_EVENT_RATING_PRODUCTIVITY:
                    Collections.sort(list, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            return Integer.compare(o1.productivityRating, o2.productivityRating);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_EVENT_WHAT_IS_DONE:
                    Collections.sort(list, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            return o1.whatIsDone.compareTo(o2.whatIsDone);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_EVENT_COMMENT:
                    Collections.sort(list, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            return o1.comment.compareTo(o2.comment);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_EVENT_PLANNED_TIME:
                    Collections.sort(list, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            int time1, time2;
                            if (o1 instanceof PlannedEvent)
                                time1 = ((PlannedEvent) o1).plannedTime;
                            else
                                return 0;
                            if (o2 instanceof PlannedEvent)
                                time2 = ((PlannedEvent) o2).plannedTime;
                            else  return -1;
                            return Integer.compare(time1, time2);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_EVENT_REAL_TIME:
                    Collections.sort(list, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            int time1, time2;
                            if (o1 instanceof PlannedEvent)
                                time1 = ((PlannedEvent) o1).realTime;
                            else if (o1 instanceof UnplannedEvent)
                                time1 = ((UnplannedEvent) o1).realTime;
                            else
                                return 0;
                            if (o2 instanceof PlannedEvent)
                                time2 = ((PlannedEvent) o2).realTime;
                            else if (o2 instanceof UnplannedEvent)
                                time2 = ((UnplannedEvent) o2).realTime;
                            else  return -1;
                            return Integer.compare(time1, time2);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_EVENT_WHAT_NEEDED_TO_BE_DONE:
                    Collections.sort(list, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            String str1, str2;
                            if (o1 instanceof PlannedEvent)
                                str1 = ((PlannedEvent) o1).whatNeededToBeDone;
                            else if (o1 instanceof DailyEvent)
                                str1 = ((DailyEvent) o1).whatNeededToBeDone;
                            else
                                return 0;
                            if (o2 instanceof PlannedEvent)
                                str2 = ((PlannedEvent) o2).whatNeededToBeDone;
                            else if (o2 instanceof DailyEvent)
                                str2 = ((DailyEvent) o2).whatNeededToBeDone;
                            else  return -1;
                            return str1.compareTo(str2);
                        }
                    });
                    break;
                case DairyPrintStream.FIELD_EVENT_WHAT_HAPPENS:
                    Collections.sort(list, new Comparator<Event>() {
                        @Override
                        public int compare(Event o1, Event o2) {
                            String str1, str2;
                            if (o1 instanceof UnplannedEvent)
                                str1 = ((UnplannedEvent) o1).whatHappens;
                            else
                                return 0;
                            if (o2 instanceof UnplannedEvent)
                                str2 = ((UnplannedEvent) o2).whatHappens;
                            else  return -1;
                            return str1.compareTo(str2);
                        }
                    });
                    break;
            }
            for(Event e : list)
                System.out.println(e.toString());
        }
    }

    static void showStatistics() {
        System.out.println("- Суммарное количество событий: " + Logic.getListAllEvents().size() +
                "\n- Суммарная продуктивность: " + Logic.getProductivity() +
                "\n- Средняя продуктивность: " + ((double) Logic.getProductivity() / Logic.getListAllEvents().size()) +
                "\n- Сумарная продуктивность по категориям: ");
        for (Map.Entry<String, Pair<Integer, Double>> entry : Logic.getProductivityOnCategory().entrySet())
            System.out.println("\t" + entry.getKey() + " - " + entry.getValue().getValue());
        System.out.println("- Средняя продуктивность по категориям: ");
        for (Map.Entry<String, Pair<Integer, Double>> entry : Logic.getProductivityOnCategory().entrySet())
            System.out.println("\t" + entry.getKey() + " - " + entry.getValue().getValue() / entry.getValue().getKey());
    }

    static void showStatisticsDay(Day day) {
        System.out.println("- Суммарное количество событий: " + day.getListAllEvents().size() +
                "\n- Суммарная продуктивность: " + day.getSummaryProductivity() +
                "\n- Средняя продуктивность: " + ((double) day.getSummaryProductivity() / day.getListAllEvents().size()) +
                "\n- Сумарная продуктивность по категориям: ");
        for (Map.Entry<String, Pair<Integer, Double>> entry : day.getProductivityOnCategory().entrySet())
            System.out.println("\t" + entry.getKey() + " - " + entry.getValue().getValue());
        System.out.println("- Средняя продуктивность по категориям: ");
        for (Map.Entry<String, Pair<Integer, Double>> entry : day.getProductivityOnCategory().entrySet())
            System.out.println("\t" + entry.getKey() + " - " + entry.getValue().getValue() / entry.getValue().getKey());
    }

    static void showAllEventsDay(Day day) {
        if(day.getListAllEvents().size() == 0)
            System.out.println("*** Список событйи пуст! ***");
        else {
            System.out.println("-> Список запланированных событий: ");
            for (Event e : day.listOfPlannedEvent)
                System.out.println("\t" + e.toString());
            System.out.println("-> Список незапланированных событий: ");
            for (Event e : day.listOfUnplannedEvent)
                System.out.println("\t" + e.toString());
            System.out.println("-> Список ежедневных событий: ");
            for (Event e : day.listOfDailyEvent)
                System.out.println("\t" + e.toString());
        }
    }

    static int getDayOnID() {
        System.out.print("Введите ID дня -> ");
        while (true) {
            String nextLine = in.nextLine();
            if (nextLine.matches("\\d+"))
                return Integer.parseInt(nextLine);
            System.out.print("*** Неверный формат! Попробуйте еще раз -> ");
        }
    }

    static int getEventOnID() {
        System.out.print("Введите ID события -> ");
        while (true) {
            String nextLine = in.nextLine();
            if (nextLine.matches("\\d+"))
                return Integer.parseInt(nextLine);
            System.out.print("*** Неверный формат! Попробуйте еще раз -> ");
        }
    }

    static void printDayIDIsNotExist() {
        System.out.println("*** В ежедневнике нет дня с таким ID! ***");
    }

    static void printEventIDIsNotExist() {
        System.out.println("*** В ежедневнике в выбранном дне нет события с таким ID! ***");
    }

    static int getFieldToFindDay() {
        String pattern = FIELD_DAY_ID + "|" + FIELD_DAY_DATE + "|" + FIELD_DAY_ISWORKING;
        System.out.print("По какому полю? (" + FIELD_DAY_ID + " - ID, " + FIELD_DAY_DATE + " - Дата, " +
                FIELD_DAY_ISWORKING + " - Рабочий/нерабочий) -> ");
        while (true) {
            String nextLine = in.nextLine();
            if (nextLine.matches(pattern))
                return Integer.parseInt(nextLine);
            System.out.print("*** Неверный номер поля! Попробуйте еще раз -> ");
        }
    }

    static int getFieldToChangeDay() {
        String pattern = FIELD_DAY_DATE + "|" + FIELD_DAY_ISWORKING;
        System.out.println("Какое поле изменить? (" + FIELD_DAY_DATE + " - Дата, " + FIELD_DAY_ISWORKING + " - Рабочий/нерабочий) -> ");
        while (true) {
            String nextLine = in.nextLine();
            if (nextLine.matches(pattern))
                return Integer.parseInt(nextLine);
            System.out.print("*** Неверный номер поля! Попробуйте еще раз -> ");
        }
    }

    static int getFieldToChangeEvent(Event newEvent) {
        System.out.println("Какое поле изменить? (1 - Категория, 2 - Рейтинг продуктивности, 3 - Что сделано, 4 - Комментарий");
        if (newEvent instanceof PlannedEvent)
            System.out.print(", 5 - Запланированное время, 6 - Реальное время, 7 - Что необходимо было сделать) -> ");
        else if (newEvent instanceof UnplannedEvent)
            System.out.print(", 5 - Реальное время, 6 - Что произошло) -> ");
        else if (newEvent instanceof DailyEvent)
            System.out.print(", 5 - Что необходимо было сделать) -> ");
        while (true) {
            String next = in.nextLine();
            if (next.matches("\\d")) {
                int field = Integer.parseInt(next);
                if ((newEvent instanceof PlannedEvent && field > 0 && field < 8) ||
                        (newEvent instanceof UnplannedEvent && field > 0 && field < 7) ||
                        (newEvent instanceof DailyEvent && field > 0 && field < 6))
                    return field;
            }
            System.out.print("*** Неверный номер поля! Попробуйте еще раз -> ");
        }
    }

    static int getFieldToFindEvent() {
        System.out.print("По какому полю? \n(0 - ID, 1 - Категория, 2 - Рейтинг продуктивности, 3 - Что сделано, 4 - Комментарий");
        System.out.print(", 5 - Запланированное время, 6 - Реальное время, 7 - Что необходимо было сделать, 8 - Что произошло) -> ");
        while (true) {
            String next = in.nextLine();
            if (next.matches("[0-8]"))
                return Integer.parseInt(next);
            System.out.print("*** Неверный номер поля! Попробуйте еще раз -> ");
        }
    }

    static boolean getExitWithoutSave() {
        System.out.print("*** Данные были изменены. Выйти без сохранения? (Y/N) -> ");
        return in.nextLine().equals("Y") ? true : false;

    }

    static String getValidField(Pattern pattern) {
        while (true) {
            String tempTime = in.nextLine();
            if (pattern.matcher(tempTime).find()) {
                return tempTime;
            } else
                System.out.print("*** Неверный формат ввода! Попробуйте еще раз -> ");
        }
    }

    static String getCategoryEvent() {
        System.out.print("Категория события -> ");
        return in.nextLine();
    }

    static int getRatingProductivity() {
        System.out.print("Рейтинг продуктивности(от 0 до 10) -> ");
        return Integer.parseInt(getValidField(patternProductivity));
    }

    static String getWhatIsDone() {
        System.out.print("Что сделано -> ");
        return in.nextLine();
    }

    static String getComment() {
        System.out.print("Комментарий -> ");
        return in.nextLine();
    }

    static int getPlannedTime() {
        System.out.print("Запланированное время(HH:mm) -> ");
        String temp = getValidField(patternTime);
        return Integer.parseInt(temp.substring(0, 2)) * 60 + Integer.parseInt(temp.substring(3, 5));
    }

    static int getRealTime() {
        System.out.print("Реальное время(HH:mm) -> ");
        String temp = getValidField(patternTime);
        return Integer.parseInt(temp.substring(0, 2)) * 60 + Integer.parseInt(temp.substring(3, 5));
    }

    static String getWhatNeededToBeDone() {
        System.out.print("Что необходимо было сделать -> ");
        return in.nextLine();
    }

    static String getWhatHappens() {
        System.out.print("Что произошло -> ");
        return in.nextLine();
    }

    static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy/MM/dd");
        System.out.print("Введите дату в формате yyyy/MM/dd -> ");
        String date;
        while (true)
            try {
                Date dateFormat = simpleDateFormat.parse(in.nextLine());
                date = simpleDateFormat.format(dateFormat);
                break;
            } catch (ParseException e) {
                System.out.print("*** Неверный формат! Попробуйте еще раз -> ");
            }
        return date;
    }

    static boolean getIsWorkingDay() {
        System.out.print("Рабочий день? (Y/N) -> ");
        return in.nextLine().equals("Y") ? true : false;
    }
}
