package Phone;

import Phone.Connetcion.NetworkCard;
import Phone.Rubric.ProRubricApp;

public class TestPhone extends SmartPhone {

    public String lastResult;

    public TestPhone() {
        super("Test-phone");
        rubric = new ProRubricApp();
    }

    public void setNetworkCard(NetworkCard nCard) {
        this.nC = nCard;
    }

    public String getNumber() {
        return nC.getNumber();
    }

    @Override
    public void searchRubric(String s) {
        printOnConsole("Risultato della ricerca su \"" + s + "\" :");
        System.out.print(rubric.search(s));
    }

    @Override
    public void showRubric() {
        printOnConsole("Contenuto della rubrica:");
        System.out.println(rubric.show());
    }

    @Override
    public void sendMessage(String inputDes, String message) {
        String rubricDes = rubric.getNumber(inputDes);

        if (null == rubricDes)
            nC.sendMessage(inputDes, message);
        else
            nC.sendMessage(rubricDes, message);
    }

    @Override
    public void unknownCommand() {
    }

    @Override
    public void printOnConsole(String r) {
    }

    @Override
    public void showPrompt() {
    }

    @Override
    public void reciveMessage(String src, String message) {
        String srcToDisplay = rubric.getName(src);
        lastResult = (null == srcToDisplay ? src : srcToDisplay) + message;
    }

    @Override
    public void connect() {
        nC.connect();
    }

    @Override
    public void disconnect() {
        nC.disconnect();
    }

    @Override
    public void bootPhone() {
    }

    @Override
    public void showInfo() {
    }

    @Override
    public void addRubric(String name, String number) {
        rubric.add(name, number);
    }

    @Override
    public void removeRubric(String name) {
        rubric.remove(name);
    }

    @Override
    protected void showConnected() {
    }

}