import java.util.*;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        //firstPart();
        secondPart();
    }

    /*1) Необходимо удалить дубликаты из массива предыдущего задания, там где нужно
    было сделать список и потом удалить буквы “a”.*/
    public static void firstPart() {
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Введите массив строк (для окончания ввода введите EXIT) - >");
        while (true){
            String st = in.nextLine();
            if(st.equals("EXIT"))
                break;
            else
                list.add(st);
        }
        HashSet set = new HashSet(list);
        list.clear();
        list.addAll(set);
        for(int i = 0; i < list.size(); i++){
            list.set(i,list.get(i).replaceAll("a", ""));
        }
        System.out.println(list.toString());
    }

    /*2) Создать объект в котором хранится имя, фамилия и отчество. Дальше создать 2 массива
    с данными с типом этого объекта (ФИО). В одном содержатся мужские ФИО, в другом женские.
    Заполнить их подходящими данными. Все массивы записать в HashMap с ключами "man",  "woman"
    соответственно.                       Дальше пользователь вводить мужское или женское ФИО
    он хочет вывести и в соответствии с этим рандомно вытаскиваем значения из массива который
    хранится в HashMap.              Т. е. на экран должно вывести рандомное ФИО из массива.*/

    public static void secondPart() {
        ArrayList<Person> listManFIO = new ArrayList<>();
        ArrayList<Person> listWomanFIO = new ArrayList<>();
        listManFIO.add(new Person("Ivanov", "Ivan", "Ivamovich"));
        listManFIO.add(new Person("Smirnov", "Oleg", "Petrovich"));
        listManFIO.add(new Person("Petrov", "Vlad", "Leonidovich"));
        listManFIO.add(new Person("Sidorov", "Alex", "Olegovich"));
        listManFIO.add(new Person("Ivamov", "Misha", "Alexandrovich"));
        listManFIO.add(new Person("Takov", "Kolya", "Alekseevich"));
        listWomanFIO.add(new Person("Ivamova", "Ksenia", "Ivanovna"));
        listWomanFIO.add(new Person("Smirnova", "Maria", "Olegovna"));
        listWomanFIO.add(new Person("Petrova", "Virsavia", "Nikolevna"));
        listWomanFIO.add(new Person("Sidorova", "Olga", "Ivanovna"));
        listWomanFIO.add(new Person("Klimenok", "Luna", "Alexandrovna"));
        listWomanFIO.add(new Person("Smith", "Una", "Petrovna"));
        HashMap<String, ArrayList> mapFIO = new HashMap<>();
        mapFIO.put("man", listManFIO);
        mapFIO.put("woman", listWomanFIO);
        System.out.print("Какое ФИО вывести? (man/woman) - > ");
        while (true){
            String st = in.nextLine();
            switch (st) {
                case "man":
                    int sizeMan = mapFIO.get("man").size();
                    System.out.println(mapFIO.get("man").get(new Random().nextInt(sizeMan)).toString());
                    break;
                case "woman":
                    int sizeWoman = mapFIO.get("woman").size();
                    System.out.println(mapFIO.get("woman").get(new Random().nextInt(sizeWoman)).toString());
                    break;
                default:
                    return;
            }
            System.out.print("Продолжаем или выход? (man/woman, любые символы для выхода) - > ");
        }

    }
}


