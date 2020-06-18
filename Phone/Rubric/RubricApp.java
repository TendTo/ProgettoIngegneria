package Phone.Rubric;

import java.util.ArrayList;
import java.util.List;

/** Interfaccia comune alle implementazioni della rubrica */
public abstract class RubricApp {

    protected final String NOMATCH = "";
    /** Lista di contatti che compone la rubrica */
    protected List<RubricRecord> records = new ArrayList<RubricRecord>();

    /**
     * Cerca i record che hanno un pattern che coincide con quello richiesto
     * 
     * @param s matching pattern
     */
    public abstract String search(String s);

    /** Mostra l'intera rubrica */
    public abstract String show();

    /**
     * Cancella un contatto dalla rubrica, se presente
     * 
     * @param name nome del contatto da eliminare
     * @return se il contatto è stato eliminato o meno
     */
    public abstract boolean remove(String name);

    /**
     * Cerca un contatto il cui nome sia precisamente quello indicato
     * 
     * @param name nome da cercare
     * @return numero che appartenga al contatto il cui nome sia quello indicato
     */
    public abstract String getNumber(String name);

    /**
     * Cerca un contatto il cui numero sia quello indicato
     * 
     * @param number numero da cercare
     * @return nome che appartenga al contatto il cui numero sia quello indicato
     */
    public abstract String getName(String number);

    /**
     * Aggiungi un contatto alla rubrica, se non presente
     * 
     * @param name   nome del record
     * @param number numero del record
     */
    public boolean add(String name, String number) {
        if (null == getNumber(name) && null == getName(number)) {
            records.add(new RubricRecord(name, number));
            return true;
        }
        return false;
    }

}