package Phone;

import Phone.Rubric.PaccoRubricApp;
import Phone.Rubric.ProRubricApp;

/** Classe factory di telefoni */
public class PhoneFactory {

    private static final String PROPHONE = "prophone";
    private static final String PACCOPHONE = "paccophone";

    /**
     * Ottieni il telefono dalla classe factory in maniera statica
     * 
     * @param model modello del telefono richiesto
     * @return istanza dell'oggetto
     */
    public static ISmartPhone getPhone(String model) {
        switch (model) {
            case PROPHONE:
                return new ProPhone(new ProRubricApp());
            case PACCOPHONE:
                return new PaccoPhone(new PaccoRubricApp());
            default:
                return null;
        }
    }
}