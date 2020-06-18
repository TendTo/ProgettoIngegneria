package Phone.Rubric;

public class PaccoRubricApp extends RubricApp {

    @Override
    public String search(String s) {
        String result = "";
        for (RubricRecord r : records)
            if (r.getName().toLowerCase().contains(s))
                result += r.getInfo() + "\n";
        return result;
    }

    @Override
    public String show() {
        String result = "";
        for (RubricRecord r : records)
            result += r.getInfo() + "\n";
        return result;
    }

    @Override
    public boolean remove(String name) {
        for (RubricRecord r : records)
            if (r.getName().equals(name)) {
                records.remove(r);
                return true;
            }
        return false;
    }

    @Override
    public String getNumber(String name) {
        String result = null;
        for (RubricRecord r : records)
            if (r.getName().equals(name)) {
                result = r.getNumber();
                return result;
            }
        return result;
    }

    @Override
    public String getName(String number) {
        String result = null;
        for (RubricRecord r : records)
            if (r.getNumber().equals(number)) {
                result = r.getName();
                return result;
            }
        return result;
    }

}