import java.io.*;

public class Main {

    private static final String FILE_NAME_FOR_SAVE = "save.txt";

    public static void main(String[] args) {
        DownloaderData downloaderData = new DownloaderData.Builder().fromServer("https://vk.com/doc223322919_496966388").build();
        Thread t1 = new Thread(new DownloaderData.Builder().fromFile(new File("222.txt")).build());
        Thread t2 = new Thread(downloaderData);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }

        Logic.combineDataFromFileAndServer();
        Logic.getLastIDForDaysAndEvents();

        while (true) {
            DairyPrintStream.showMenu();
            String nextLine = DairyPrintStream.in.nextLine();
            if (!nextLine.matches("\\d+"))
                continue;
            int value = Integer.parseInt(nextLine);
            if (value == DairyPrintStream.MENU_EXIT) {
                if (Logic.isDataSavedToFile(FILE_NAME_FOR_SAVE) || DairyPrintStream.getExitWithoutSave())
                    return;
            } else
                performMenuValue(value);
            System.out.println("Нажмите Enter для продолжения");
            DairyPrintStream.in.nextLine();

        }
    }

    private static void performMenuValue(int value) {
        switch (value) {
            case DairyPrintStream.MENU_SHOW_DAIRY_ALL_DAYS:
                DairyPrintStream.showDairyAllDays();
                break;
            case DairyPrintStream.MENU_SHOW_DAIRY_ALL_EVENTS:
                DairyPrintStream.showDairyAllEvents();
                break;
            case DairyPrintStream.MENU_SHOW_SORT_DAYS_BY:
                DairyPrintStream.showDaysSortedBy();
                break;
            case DairyPrintStream.MENU_ADD_DAY:
                Logic.addDay();
                break;
            case DairyPrintStream.MENU_CHANGE_DAY:
                Logic.changeDay();
                break;
            case DairyPrintStream.MENU_DELETE_DAY:
                Logic.deleteDay();
                break;
            case DairyPrintStream.MENU_FIND_DAY:
                Logic.findDay();
                break;
            case DairyPrintStream.MENU_CHOOSE_DAY:
                Day day = Logic.chooseDay();
                if (day == null) {
                    DairyPrintStream.printDayIDIsNotExist();
                    break;
                }
                else
                    performSubMenu(day);
                break;
            case DairyPrintStream.MENU_SAVE_TO_FILE:
                Logic.saveDataToFile(FILE_NAME_FOR_SAVE);
                break;
            case DairyPrintStream.MENU_HTML_REPORT:
                break;
            case DairyPrintStream.MENU_SHOW_STATISTICS:
                DairyPrintStream.showStatistics();
                break;
            default:
                System.out.println("*** Неверный ввод меню! ***");
                break;
        }
    }

    private static void performSubMenu(Day day) {
        while (true) {
            System.out.println(day.toString());
            DairyPrintStream.showSubMenu();
            String nextLine = DairyPrintStream.in.nextLine();
            if (!nextLine.matches("\\d+"))
                continue;
            int value = Integer.parseInt(nextLine);
            if (value == DairyPrintStream.MENU_BACK_TO_MAIN_MENU)
                return;
            else {
                performSubMenuValue(value, day);
                System.out.println("Нажмите Enter для продолжения");
                DairyPrintStream.in.nextLine();
            }
        }
    }

    private static void performSubMenuValue(int value, Day day) {
        switch (value) {
            case DairyPrintStream.MENU_SHOW_STATISTICS_DAY:
                DairyPrintStream.showStatisticsDay(day);
                break;
            case DairyPrintStream.MENU_SHOW_ALL_EVENTS_DAY_SORTED_BY:
                DairyPrintStream.showDayEventsSortedBy(day);
                break;
            case DairyPrintStream.MENU_ADD_EVENT:
                Logic.addEvent(day);
                break;
            case DairyPrintStream.MENU_CHANGE_EVENT:
                Logic.changeEvent(day);
                break;
            case DairyPrintStream.MENU_FIND_EVENT:
                Logic.findEvent(day);
                break;
            case DairyPrintStream.MENU_DELETE_EVENT:
                Logic.deleteEvent(day);
                break;
            case DairyPrintStream.MENU_SHOW_ALL_EVENTS_DAY:
                DairyPrintStream.showAllEventsDay(day);
                break;
            default:
                System.out.println("*** Неверный ввод меню! ***");
                break;
        }
    }
}
