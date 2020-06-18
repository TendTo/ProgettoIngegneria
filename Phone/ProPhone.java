package Phone;

import Phone.Rubric.RubricApp;

/** Modello di telefono Pro-phone */
public class ProPhone extends SmartPhone {
    /** Usato per colorare di verde la scrittura nella console */
    private final String GREEN_BOLD = "\033[1;32m";

    private final String COMMANDS = "Lista comandi:\n| Info  |  AggiungiContatto \"Nome contatto\" numero |\n| EliminaContatto \"Nome contatto\"  |  MostraRubrica |\n| CercaRubrica pattern  |  MostraConnessi |\n| InviaMessaggio [\"Nome contatto\", numero] messaggio  |  ImpostaNome Nome |\n| Connetti  |  Disconnetti |";

    /**
     * Costruttore del ProPhone
     * 
     * @param number numero del telefono
     */
    public ProPhone(RubricApp rubric) {
        super("Pro-phone");
        this.rubric = rubric;
    }

    @Override
    public void bootPhone() {
        printOnConsole("Hai appena eseguito l'accesso nel tuo nuovo Pro-Phone di ultima generazione.");
        printOnConsole("Ricorda di impostare il nome utente appena possibile.");
        printOnConsole(COMMANDS);
        showPrompt();
    }

    @Override
    protected void showInfo() {
        printOnConsole("Info del fantastico Pro-phone di " + ownerName + "!!");
        String number = nC.getNumber();
        System.out.println("| Numero: " + number + " | Stato: "
                + (number == "invalidNumber" ? "Non connesso" : "Connesso") + " |");
    }

    @Override
    protected void searchRubric(String s) {
        printOnConsole("Risultato della ricerca su \"" + s + "\" :");
        System.out.println(rubric.search(s));
    }

    @Override
    protected void showRubric() {
        printOnConsole("Contenuto della rubrica:");
        System.out.println(rubric.show());
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
            printOnConsole("C'e' stato un errore nell'invio del messaggio");
            printOnConsole(
                    "Controlla che il telefono sia connesso (comando \"Info\") e che il numero o il nome digitati siano corretti");
        }
    }

    @Override
    protected void unknownCommand() {
        printOnConsole("Comando non riconosciuto");
        printOnConsole(COMMANDS);
    }

    @Override
    protected void printOnConsole(String r) {
        System.out.println(GREEN_BOLD + model + ": " + RESET + r);
    }

    @Override
    protected void showPrompt() {
        System.out.print(ownerName + "@" + model + ">");
        System.out.flush();
    }

    @Override
    public void reciveMessage(String src, String message) {
        String srcToDisplay = rubric.getName(src);
        printOnConsole("Hai appena ricevuto un messaggio da " + (null == srcToDisplay ? src : srcToDisplay));
        printOnConsole(message);
    }

    @Override
    protected void connect() {
        if (nC.connect())
            printOnConsole("Sei connesso alla rete");
        else
            printOnConsole("C'e' stato un problema con la connessione alla rete, ritenta piu' tardi");
    }

    @Override
    protected void disconnect() {
        nC.disconnect();
        printOnConsole("Non sei connesso alla rete");
    }

    @Override
    protected void addRubric(String name, String number) {
        if (rubric.add(name, number))
            printOnConsole("Contatto aggiunto in rubrica");
        else
            printOnConsole("Esiste gia' un contatto con quel nome o con quel numero");

    }

    @Override
    protected void removeRubric(String name) {
        if (rubric.remove(name))
            printOnConsole("Contatto eliminato dalla rubrica");
        else
            printOnConsole("Contatto non trovato in rubrica");

    }

    @Override
    protected void showConnected() {
        if (nC.getNumber() == "invalidNumber")
            printOnConsole("Non sei attualmente connesso alla rete");
        else {
            printOnConsole("I seguenti dipositivi sono attualmente connessi alla rete");
            System.out.println(nC.showConnected());
        }
    }
}