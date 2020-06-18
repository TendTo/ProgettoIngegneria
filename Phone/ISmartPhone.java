package Phone;

/** Interfaccia di un generico telefono */
public interface ISmartPhone {
    /** Usato per fare il boot del telefono la prima volta */
    public void bootPhone();

    /** Usato per comunicare il prossimo comando al telefono */
    public void inputCommand(String c);
}