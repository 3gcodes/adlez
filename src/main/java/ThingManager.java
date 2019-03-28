import java.util.ArrayList;
import java.util.List;

public class ThingManager {

    private List<Thing> things = new ArrayList<>();

    public void addThing(Thing thing) {
        things.add(thing);
    }

    public void removeThing(Thing thing) {
        things.remove(thing);
    }

    public List<Thing> getThings() {
        return this.things;
    }
}
