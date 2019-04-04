public class UnplannedEvent extends Event{

    public int realTime;
    public String whatHappens;

    public UnplannedEvent() {
    }

    public UnplannedEvent(String category, String comment, int productivityRating, String whatIsDone, int realTime, String whatHappens) {
        super(category, comment, productivityRating, whatIsDone);
        this.realTime = realTime;
        this.whatHappens = whatHappens;
    }

    @Override
    public String toString() {
        return super.toString() + "; Реальное время: " + String.format("%02d:%02d", realTime / 60, realTime % 60) + "; Что произошло: " + whatHappens;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        UnplannedEvent u = (UnplannedEvent) obj;
        return super.equalsAs(obj) && realTime == u.realTime && (whatHappens != null && whatHappens.equals(u.whatHappens));
    }

}
