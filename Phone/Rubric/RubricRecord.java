package Phone.Rubric;

/** Classe che compone i record della rubrica */
public class RubricRecord {
    private String name;
    private String number;

    public RubricRecord(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return name + "\t" + number;
    }
}