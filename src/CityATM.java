public class CityATM extends ATM implements AllMoney {

    private static final int BANKNOTE100 = 0;
    private static final int BANKNOTE50 = 1;
    private static final int BANKNOTE20 = 2;
    private static final int NumberOFBANKNOTES = 3;
    private int[] arrayOfBanknotes;

    public CityATM(String manufacturer) {
        this(0,0,0, manufacturer);
    }

    public CityATM(int numberOf100Banknotes, int numberOf50Banknotes, int numberOf20Banknotes, String manufacturer) {
        super(numberOf100Banknotes, numberOf50Banknotes, numberOf20Banknotes, "CityATM Bank", manufacturer);
    }

    @Override
    public int allMoney() {
        return numberOf100Banknotes * 100 + numberOf50Banknotes * 50 + numberOf20Banknotes * 20;
    }

    @Override
    public int[] withdrawal(int money) {
        if (money < 20 || allMoney() < money || money % 100 == 30 || money % 10 != 0)
            return null;
        else {
            int[] temp = new int[NumberOFBANKNOTES];
            while(money >= 100 && numberOf100Banknotes > 0) {
                money -= 100;
                temp[BANKNOTE100]++;
            }
            while((money == 50 || money > 60) && numberOf50Banknotes > 0) {
                money -= 50;
                temp[BANKNOTE50]++;
            }
            if(money % 20 != 0 || numberOf20Banknotes < money / 20)
                return null;
            else {
                numberOf20Banknotes -= money / 20;
                numberOf100Banknotes -= temp[BANKNOTE100];
                numberOf50Banknotes -= temp[BANKNOTE50];
                temp[BANKNOTE20] += money / 20;
            }
            return temp;
        }
    }

    @Override
    public void printWithdrawal(int[] array) {
        if(array == null)
            System.out.println("Невозможно выдать деньги!");
        else
            System.out.println("Деньги выданы: 100 * " + array[BANKNOTE100] + " + 50 * " + array[BANKNOTE50] + " + 20 * " + array[BANKNOTE20]);
    }

    @Override
    public boolean add(int money) {
        if (money < 20 || money % 100 == 30 || money % 10 != 0)
            return false;
        while(money >= 100) {
            money -= 100;
            numberOf100Banknotes++;
        }
        if(money % 20 != 0 && money >= 50) {
            money -= 50;
            numberOf50Banknotes++;
            if(money >= 20){
                numberOf20Banknotes += money / 20;
            }
        }
        else
            numberOf20Banknotes += money / 20;
        return true;
    }
}
