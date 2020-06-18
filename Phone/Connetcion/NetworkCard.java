package Phone.Connetcion;

import Network.Network;
import Phone.SmartPhone;

/** Scheda di rete usata dai dispositivi */
public class NetworkCard {

    /** Numero del telefono */
    private String phoneNumber;
    /** Reference alla rete interna aziendale */
    private Network network;
    /** Telefono a cui appartiene la scheda di rete */
    private SmartPhone phone;

    /**
     * Construttore della scheda di rete
     * 
     * @param phone telefono a cui appartiene
     */
    public NetworkCard(SmartPhone phone) {
        this.phone = phone;
        this.network = Network.getIstance();
    }

    /**
     * Manda un messaggio al telefono destinatario attraverso la rete
     * 
     * @param des     numero del telefono destinatario
     * @param message messaggio da inviare
     * @return se il messaggio è stato inviato con successo
     */
    public boolean sendMessage(String des, String message) {
        if (null == phoneNumber)
            return false;
        return network.sendMessage(phoneNumber, des, message);
    }

    /**
     * Notifica il telefono di aver ricevuto un messaggio
     * 
     * @param src     numero del telefono mittente
     * @param message messaggio ricevuto
     * @return conferma che il messaggio è stato ricevuto
     */
    public boolean notifyMessage(String src, String message) {
        phone.reciveMessage(src, message);
        return true;
    }

    /**
     * Getter per il numero del telefono (identificativo della scheda)
     * 
     * @return numero del telefono
     */
    public String getNumber() {
        if (null == phoneNumber)
            return "invalidNumber";
        else
            return phoneNumber;
    }

    /**
     * Connetti il dispositivo alla rete aziendale ricevendo l'identificativo
     * univoco necessario per utilizzare la rete
     * 
     * @return se la connessione è avvenuta con successo
     */
    public boolean connect() {
        if (null == phoneNumber)
            phoneNumber = network.getUniqueNumber(this);

        if (null == phoneNumber)
            return false;
        else
            return true;
    }

    /**
     * Disconnetti il dispositivo dalla rete aziendale, restituendo quindi
     * l'identificativo univoco al pool di quelli disponibili
     */
    public void disconnect() {
        phoneNumber = network.realeaseUniqueNumber(this);
    }

    /**
     * Mostra le info pubbliche del dispositivo quando richieste dalla rete
     * 
     * @return info pubbliche del dispositivo
     */
    public String getPhonePublicInfo() {
        return phone.getOwner() + "\t" + phoneNumber;
    }

    /**
     * Chiede alla rete di mostrare i dispositivi attualmente connessi
     * 
     * @return lista delle info dei dispositivi connessi
     */
    public String showConnected() {
        return network.showConnectedUsers();
    }
}