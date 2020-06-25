package Phone.Connetcion;

import Network.Network;
import Network.NetworkSubscriber;
import Phone.SmartPhone;

/** Scheda di rete usata dai dispositivi */
public class NetworkCard implements NetworkSubscriber {

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
    }

    /**
     * Manda un messaggio al telefono destinatario attraverso la rete
     * 
     * @param des     numero del telefono destinatario
     * @param message messaggio da inviare
     * @return se il messaggio è stato inviato con successo
     */
    public boolean sendMessage(String des, String message) {
        if (null == phoneNumber || null == network)
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
    @Override
    public boolean notifyMessage(Object sub, String src, String message) {
        if (sub instanceof Network) {
            Network n = (Network) sub;
            phone.reciveMessage(n.getNetworkId(), src, message);
            return true;
        } else
            return false;
    }

    /**
     * Getter per il numero del telefono (identificativo della scheda)
     * 
     * @return numero del telefono
     */
    @Override
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
        if (null == network) {
            network = Network.getIstance();
            phoneNumber = network.getUniqueNumber(this);
        }

        if (null == phoneNumber) {
            network = null;
            return false;
        } else
            return true;
    }

    /**
     * Disconnetti il dispositivo dalla rete aziendale, restituendo quindi
     * l'identificativo univoco al pool di quelli disponibili
     */
    public void disconnect() {
        phoneNumber = network.realeaseUniqueNumber(this);
        network = null;
    }

    /**
     * Mostra le info pubbliche del dispositivo quando richieste dalla rete
     * 
     * @return info pubbliche del dispositivo
     */
    @Override
    public String getPublicInfo() {
        return phone.getOwner() + "\t" + phoneNumber;
    }

    /**
     * Chiede alla rete di mostrare i dispositivi attualmente connessi
     * 
     * @return lista delle info dei dispositivi connessi
     */
    public String showConnected() {
        if (null != network)
            return network.showConnectedUsers();
        else
            return null;
    }

}