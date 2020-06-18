package Phone;

import java.util.regex.Pattern;

import Phone.Connetcion.NetworkCard;
import Phone.Rubric.RubricApp;

/** Classe generale degli smartphone */
public abstract class SmartPhone implements ISmartPhone {
    private final String INFO = "info";
    private final String CERCA = "cercarubrica";
    private final String CHIAMA = "inviamessaggio";
    private final String RUBRICA = "mostrarubrica";
    private final String ELIMINA = "eliminacontatto";
    private final String CONNETTI = "connetti";
    private final String DISCONNETTI = "disconnetti";
    private final String NOME = "impostanome";
    private final String AGGIUNGI = "aggiungicontatto";
    private final String CONNESSI = "mostraconnessi";
    /** Resetta il colore del testo della console */
    protected final String RESET = "\033[0m";

    /** Nome del proprietario */
    protected String ownerName;
    /** Modello del telefono */
    protected final String model;
    /** Rubrica del telefono */
    protected RubricApp rubric;
    /** Scheda di rete del telefono */
    protected NetworkCard nC;

    /**
     * Costruttore dello SmartPhone
     * 
     * @param number numero del telefono
     */
    protected SmartPhone(String model) {
        this.ownerName = "unknown";
        this.nC = new NetworkCard(this);
        this.model = model;
    }

    /** Mostra le prorpietà del telefono (nome, numero, modello, etc...) */
    protected abstract void showInfo();

    /**
     * Cerca nella rubrica tutte istanze che contengono la stringa richiesta
     * 
     * @param s string che fa da matching pattern
     */
    protected abstract void searchRubric(String s);

    /** Mostra tutti i contatti della rubrica */
    protected abstract void showRubric();

    /**
     * Aggiunge il contatto scelto alla libreria, se non ne esiste già un'altro con
     * lo stesso nome
     * 
     * @param name   nome del contatto
     * @param number numero del contatto
     */
    protected abstract void addRubric(String name, String number);

    /**
     * Rimuove il contatto dalla rubrica, se presente
     * 
     * @param name nome del contatto da rimuovere
     */
    protected abstract void removeRubric(String name);

    /** Chiama se non riconsci il comando */
    protected abstract void unknownCommand();

    /**
     * Mostra un piccolo prompt all'inizio di ogni linea di testo generata dal
     * telefono
     */
    protected abstract void printOnConsole(String r);

    /**
     * Mostra il prompt per indicare la possibilità di inserire un input nella
     * console
     */
    protected abstract void showPrompt();

    /**
     * Manda un messaggio verso un certo numero
     * 
     * @param inputDes numero che si vuole chiamare o nome del contatto salvato in
     *                 rubrica
     * @param message  testo del messaggio
     */
    protected abstract void sendMessage(String inputDes, String message);

    /**
     * Chiamato alla ricezione di un messaggio, si occupa di visualizzarlo
     * 
     * @param src     numero identificativo del mittente del messaggio
     * @param message corpo del messaggio
     */
    public abstract void reciveMessage(String src, String message);

    /** Prova a connetterti alla rete interna aziendale */
    protected abstract void connect();

    /** Disconnettiti dalla rete interna aziendale */
    protected abstract void disconnect();

    /** Mostra i dispositivi connessi */
    protected abstract void showConnected();

    /**
     * Cambia il nome del possessore del telefono
     * 
     * @param owner nome de prorprietario
     */
    private void setOwner(String owner) {
        this.ownerName = owner;
    }

    /**
     * Getter per il proprietario del telefono
     * 
     * @return nome del proprietario
     */
    public String getOwner() {
        return ownerName;
    }

    /**
     * Esegui il comando richiesto, aggiungendo le necessarie informazioni
     * 
     * @param input comando inserito in console
     */
    @Override
    public void inputCommand(String input) {
        String argv[] = parseInput(input);
        int len = argv.length;

        switch (argv[0].toLowerCase()) {
            case CERCA:
                if (len != 2) {
                    unknownCommand();
                    break;
                }
                searchRubric(argv[1]);
                break;
            case RUBRICA:
                showRubric();
                break;
            case CHIAMA:
                if (len != 3) {
                    unknownCommand();
                    break;
                }
                sendMessage(argv[1], argv[2]);
                break;
            case INFO:
                showInfo();
                break;
            case NOME:
                if (len != 2) {
                    unknownCommand();
                    break;
                }
                setOwner(argv[1]);
                break;
            case AGGIUNGI:
                if (len != 3) {
                    unknownCommand();
                    break;
                }
                addRubric(argv[1], argv[2]);
                break;
            case ELIMINA:
                if (len != 2) {
                    unknownCommand();
                    break;
                }
                removeRubric(argv[1]);
                break;
            case CONNETTI:
                connect();
                break;
            case DISCONNETTI:
                disconnect();
                break;
            case CONNESSI:
                showConnected();
                break;
            default:
                unknownCommand();
                break;
        }
        showPrompt();
    }

    /**
     * Fa il parsing della linea letta dall'input, ponendo tutti i parametri nelle
     * corrette posizioni
     * 
     * @param input testo letto dall'input della console
     * @return il parsing dell'input
     */
    private String[] parseInput(String input) {
        Pattern pattern = Pattern.compile("(?<!\"[^ ][^\"]{1,255})\s+(?![^\"]{1,255}[^ ]\")");
        String argv[] = pattern.split(input);
        for (int i = 0; i < argv.length; i++)
            argv[i] = argv[i].replace("\"", "");

        return argv;
    }
}