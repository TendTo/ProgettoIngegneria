package Client;
import Phone.ISmartPhone;
import Phone.PhoneFactory;

/** Client che interagisce con la console */
public class Client {
    /** Dà il via alla simulazione */
    public void startSimulation() {
        String model;
        ISmartPhone phone;
        System.out.println("Quale modello di telefono vuoi simulare?");
        do {
            System.out.println("Scegli tra | ProPhone | PaccoPhone |");
            model = System.console().readLine();
            phone = PhoneFactory.getPhone(model.toLowerCase());
        } while (null == phone);
        startUsingPhone(phone);
    }

    /** Manda il comando inserito in console al telefono */
    private void startUsingPhone(ISmartPhone phone) {
        phone.bootPhone();
        while (true) {
            String command = System.console().readLine();
            phone.inputCommand(command.toLowerCase());
        }
    }
}