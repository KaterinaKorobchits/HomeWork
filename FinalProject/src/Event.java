import java.util.TreeSet;

abstract public class Event {

    public long id;
    public String category;
    public String comment;
    public int productivityRating;
    public String whatIsDone;
    public static TreeSet<Long> listID = new TreeSet<>();

    public Event() {
    }

    public Event(String category, String comment, int productivityRating, String whatIsDone) {
        id = listID.last() + 1;
        listID.add(id);
        this.category = category;
        this.comment = comment;
        this.productivityRating = productivityRating;
        this.whatIsDone = whatIsDone;
    }

    public boolean equalsAs(Object obj) {
        Event e = (Event) obj;
        return id == e.id && (category != null && category.equals(e.category)) && (comment != null && comment.equals(e.comment)) &&
                productivityRating == e.productivityRating && (whatIsDone != null && whatIsDone.equals(e.whatIsDone));
    }

    @Override
    public String toString() {
        return "ID = " + id + "; Категория: " + category + "; Рейтинг продуктивности: " + productivityRating +
                "; Что сделано: " + whatIsDone + "; Комментарий: " + comment;
    }
}
