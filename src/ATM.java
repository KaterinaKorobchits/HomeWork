public abstract class ATM implements Withdrawal, AddMoney, GetAtmInfo{

    int numberOf100Banknotes;
    int numberOf50Banknotes;
    int numberOf20Banknotes;
    final String bankName;
    final String manufacturer;

    public ATM(int numberOf100Banknotes, int numberOf50Banknotes, int numberOf20Banknotes, String bankName, String manufacturer) {
        this.numberOf100Banknotes = numberOf100Banknotes;
        this.numberOf50Banknotes = numberOf50Banknotes;
        this.numberOf20Banknotes = numberOf20Banknotes;
        this.bankName = bankName;
        this.manufacturer = manufacturer;
    }

    @Override
    public String getInfoAtm() {
        return "This ATM of " + bankName + ", manufacturer: " + manufacturer + "\n";
    }

    @Override
    public String toString() {
        return String.format("%-10s%-10d%n  %-10s%-10d%n  %-10s%-10d", "100", numberOf100Banknotes, "50" , numberOf50Banknotes, "20", numberOf20Banknotes);
    }

    public void print() {
        System.out.printf("%-10s%-10s", "Купюра:", "Кол-во:\n");
        System.out.println(toString());
    }

    abstract public void printWithdrawal(int[] with);
}
