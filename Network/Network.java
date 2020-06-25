package Network;

import java.util.List;
import java.util.ArrayList;

/** Classe che rappresenta la rete interna aziendale */
public class Network {
    private final String networkId;
    /** Istanza singleton della classe */
    private static Network singleton;
    /** Lista dei numeri univoci disponibili */
    private List<String> availableUniqueNumbers = new ArrayList<>();
    /** Lista delle schede di rete attualmente connessi */
    private List<NetworkSubscriber> connectedNetworkSubscribers = new ArrayList<>();

    /**
     * Costruttore privato della classe Network, inizializza la pool di numeri
     * validi
     */
    private Network() {
        networkId = "Rete aziendale";
        inizializeUniqueNumbers();
    }

    /**
     * Resetta la rete, distruggendo l'instanza singleton
     */
    public void reset() {
        singleton = null;
    }

    /**
     * Getter per il networkId
     * 
     * @return networkId
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * Getter per l'istanza singleton della classe
     * 
     * @return singleton
     */
    public static Network getIstance() {
        if (null == singleton)
            singleton = new Network();

        return singleton;
    }

    /**
     * Connetti la scheda di rete alla rete fornendogli un identificativo univoco
     * 
     * @param nS scheda di rete che richiede la connessione
     * @return identificativo che la scheda userà
     */
    public String getUniqueNumber(NetworkSubscriber nS) {
        if (availableUniqueNumbers.size() <= 0)
            return null;
        else {
            connectedNetworkSubscribers.add(nS);
            return availableUniqueNumbers.remove(0);
        }
    }

    /**
     * Disconnetti la scheda di rete dalla rete resctituendo l'identificativo al
     * pool di quelli disponibili
     * 
     * @param nS scheda di rete che richiede la disconnessione
     * @return null
     */
    public String realeaseUniqueNumber(NetworkSubscriber nS) {
        if (!connectedNetworkSubscribers.contains(nS))
            return null;

        connectedNetworkSubscribers.remove(nS);
        availableUniqueNumbers.add(nS.getNumber());
        return null;
    }

    /** Inizializza la lista di identificatori univoci */
    private void inizializeUniqueNumbers() {
        for (int i = 134; i < 134 + 100; i++) {
            availableUniqueNumbers.add("311" + i);
        }
    }

    /**
     * Manda un messaggio tra due dispositivi connessi alla rete
     * 
     * @param src     dispositivo sorgente
     * @param des     dispositivo destinatario
     * @param message corpo del messaggio
     * @return se il messaggio è stato consegnato con successo
     */
    public boolean sendMessage(String src, String des, String message) {
        for (NetworkSubscriber nS : connectedNetworkSubscribers)
            if (nS.getNumber().equals(des))
                return nS.notifyMessage(this, src, message);
        return false;
    }

    /**
     * Mostra i dispositivi attualmente connessi alla rete, visualizzando nome del
     * proprietario e identificativo
     * 
     * @return lista di dispositivi connessi
     */
    public String showConnectedUsers() {
        String result = "";
        for (NetworkSubscriber nS : connectedNetworkSubscribers) {
            result += nS.getPublicInfo() + "\n";
        }
        return result;
    }
}