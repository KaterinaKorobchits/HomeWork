public class PlannedEvent extends Event{

    public int plannedTime;
    public int realTime;
    public String whatNeededToBeDone;

    public PlannedEvent() {
    }

    public PlannedEvent(String category, String comment, int productivityRating, String whatIsDone, int plannedTime, int realTime, String whatNeededToBeDone) {
        super(category, comment, productivityRating, whatIsDone);
        this.plannedTime = plannedTime;
        this.realTime = realTime;
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
        PlannedEvent p = (PlannedEvent) obj;
        return super.equalsAs(obj) && plannedTime == p.plannedTime && realTime == p.realTime &&
                (whatNeededToBeDone != null && whatNeededToBeDone.equals(p.whatNeededToBeDone));
    }

    @Override
    public String toString() {
        return super.toString() + "; Запланированное время: " + String.format("%02d:%02d", plannedTime / 60, plannedTime % 60) +
                "; Реальное время: " + String.format("%02d:%02d", realTime / 60, realTime % 60) + "; Что необходимо было сделать: " + whatNeededToBeDone;
    }
}
