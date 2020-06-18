package Phone.Rubric;

import java.util.Optional;

public class ProRubricApp extends RubricApp {

    @Override
    public String search(String s) {
        return records.stream().filter(r -> r.getName().toLowerCase().contains(s.toLowerCase()))
                .map(r -> r.getInfo() + "\n").reduce("", (s1, s2) -> s1 += s2);
    }

    @Override
    public String show() {
        return records.stream().map(r -> r.getInfo() + "\n").reduce("", (s1, s2) -> s1 += s2);
    }

    @Override
    public boolean remove(String name) {
        Optional<RubricRecord> o = records.stream().filter(r -> r.getName().equals(name)).findAny();
        if (o.isPresent()) {
            records.remove(o.get());
            return true;
        } else
            return false;
    }

    @Override
    public String getNumber(String name) {
        return records.stream().filter(r -> r.getName().equals(name)).map(r -> r.getNumber()).findAny().orElse(null);
    }

    @Override
    public String getName(String number) {
        return records.stream().filter(r -> r.getNumber().equals(number)).map(r -> r.getName()).findAny().orElse(null);
    }
}