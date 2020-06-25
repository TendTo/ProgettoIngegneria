package Phone;

import Phone.Rubric.RubricApp;

/** Modello di telefono Pacco-phone */
public class PaccoPhone extends SmartPhone {
    /** Usato per colorare di viola la scrittura nella console */
    private final String PURPLE_BOLD = "\033[1;35m";
    /** Set di istruzioni */
    private final String COMMANDS = "Comandi:\n| Info  |  AggiungiContatto \"Nome contatto\" numero |\n| EliminaContatto \"Nome contatto\"  |  MostraRubrica |\n| CercaRubrica pattern  |  MostraConnessi |\n| InviaMessaggio [\"Nome contatto\", numero] messaggio  |  ImpostaNome Nome |\n| Connetti  |  Disconnetti |";

    /**
     * Costruttore del PaccoPhone
     * @param rubric app di rubrica che si sceglie di utilizzare
     */
    public PaccoPhone(RubricApp rubric) {
        super("Pacco-phone");
        this.rubric = rubric;
    }

    @Override
    public void bootPhone() {
        printOnConsole("Questo e' un Pacco-phone. Complimenti");
        printOnConsole("Imposta il nome utente.");
        printOnConsole(COMMANDS);
        showPrompt();

    }

    @Override
    protected void showInfo() {
        printOnConsole("Info: Nome utente: " + ownerName);
        String number = nC.getNumber();
        System.out.println("| Numero: " + number + " | Stato: "
                + (number == "invalidNumber" ? "Non connesso" : "Connesso") + " |");
    }

    @Override
    protected void searchRubric(String s) {
        printOnConsole("Risultati contententi \"" + s + "\" :");
        System.out.println(rubric.search(s));
    }

    @Override
    protected void showRubric() {
        printOnConsole("Lista dei contatti:");
        System.out.println(rubric.show());
    }

    @Override
    protected void addRubric(String name, String number) {
        if (rubric.add(name, number))
            printOnConsole("Contatto aggiunto");
        else
            printOnConsole("Contatto non aggiunto");
    }

    @Override
    protected void removeRubric(String name) {
        if (rubric.remove(name))
            printOnConsole("Contatto rimosso");
        else
            printOnConsole("Il contatto non esiste");
    }

    @Override
    protected void unknownCommand() {
        printOnConsole("Il comando non e' fra quelli disponibili");
        printOnConsole(COMMANDS);
    }

    @Override
    protected void printOnConsole(String r) {
        System.out.println(PURPLE_BOLD + model + ": " + RESET + r);
    }

    @Override
    protected void showPrompt() {
        System.out.print(ownerName + "@" + model + "# ");
        System.out.flush();
    }

    @Override
    protected void sendMessage(String inputDes, String message) {
        boolean result;
        String des = rubric.getNumber(inputDes);

        if (null == des)
            result = nC.sendMessage(inputDes, message);
        else
            result = nC.sendMessage(des, message);

        if (!result) {
            printOnConsole("Si e' verificato un errore");
            printOnConsole(
                    "Controlla la connessione (comando \"Info\") e che il numero o il nome digitati siano corretti");
        }
    }

    @Override
    public void reciveMessage(String networkId, String src, String message) {
        String srcToDisplay = rubric.getName(src);
        printOnConsole("Messaggio proveniente da " + networkId);
        printOnConsole("Mittente: " + (null == srcToDisplay ? src : srcToDisplay));
        printOnConsole(message);
    }

    @Override
    protected void connect() {
        if (nC.connect())
            printOnConsole("Ti sei connesso");
        else
            printOnConsole("Si e' verificato un problema, riprova piu' tardi");
    }

    @Override
    protected void disconnect() {
        nC.disconnect();
        printOnConsole("Ti sei disconnesso");
    }

    @Override
    protected void showConnected() {
        String connected = nC.showConnected();
        if (null == connected)
            printOnConsole("Rete irraggiungibile. Controlla la connessione");
        else {
            printOnConsole("Dispositivi attualmente connessi:");
            System.out.println(connected);
        }
    }

}