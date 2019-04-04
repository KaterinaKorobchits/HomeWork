public class DailyEvent extends Event{

    public String whatNeededToBeDone;

    public DailyEvent() {
    }

    public DailyEvent(String category, String comment, int productivityRating, String whatIsDone, String whatNeededToBeDone) {
        super(category, comment, productivityRating, whatIsDone);
        this.whatNeededToBeDone = whatNeededToBeDone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        DailyEvent d = (DailyEvent) obj;
        return super.equalsAs(obj) && (whatNeededToBeDone != null && whatNeededToBeDone.equals(d.whatNeededToBeDone));
    }

    @Override
    public String toString() {
        return super.toString() + "; Что необходимо было сделать: " + whatNeededToBeDone;
    }
}
