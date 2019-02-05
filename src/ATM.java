public class ATM {
    int numberOf100Banknotes;
    int numberOf50Banknotes;
    int numberOf20Banknotes;

    public ATM() {
    }

    public ATM(int numberOf100Banknotes, int numberOf50Banknotes, int numberOf20Banknotes) {
        this.numberOf100Banknotes = numberOf100Banknotes;
        this.numberOf50Banknotes = numberOf50Banknotes;
        this.numberOf20Banknotes = numberOf20Banknotes;
    }

    public boolean add(int summ) {
        if (summ < 20 || summ % 100 == 10 || summ % 100 == 30 || summ % 10 != 0)
            return false;
        int[] temp = breakIntoBanknotes(summ);
        numberOf100Banknotes += temp[0];
        numberOf50Banknotes += temp[1];
        numberOf20Banknotes += temp[2];
        return true;
    }

    public String cash(int summ) {
        if (summ < 20 || summ % 100 == 10 || summ % 100 == 30 || summ % 10 != 0)
            return "Невозможно выдать!";
        else {
            int[] temp = breakIntoBanknotes(summ);
            numberOf100Banknotes -= temp[0];
            numberOf50Banknotes -= temp[1];
            numberOf20Banknotes -= temp[2];
            return summ + " = 100 * " + breakIntoBanknotes(summ)[0] + " + 50 * " + breakIntoBanknotes(summ)[1] + " + 20 * " + breakIntoBanknotes(summ)[2];
        }
    }

    private int[] breakIntoBanknotes(int summ) {
        int [] mas = new int[3];
        while(summ >= 100) {
            summ -= 100;
            mas[0]++;
        }
        if(summ % 30 != 0 && summ >=50) {
            summ -= 50;
            mas[1]++;
            if(summ >= 20){
                mas[2] += summ / 20;
            }
        }
        else
            mas[2] += summ / 20;
        return mas;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-10d%n  %-10s%-10d%n  %-10s%-10d", "100", numberOf100Banknotes, "50" , numberOf50Banknotes, "20", numberOf20Banknotes);
    }

    public void print() {
        System.out.printf("%-10s%-10s", "Купюра:", "Кол-во:\n");
        System.out.printf("%-10s%-10d%n  %-10s%-10d%n  %-10s%-10d%n", "100", numberOf100Banknotes, "50" , numberOf50Banknotes, "20", numberOf20Banknotes);
    }
}
