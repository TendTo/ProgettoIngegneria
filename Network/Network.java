package Network;

import Phone.Connetcion.NetworkCard;
import java.util.List;
import java.util.ArrayList;

/** Classe che rappresenta la rete interna aziendale */
public class Network {
    /** Istanza singleton della classe */
    private static Network singleton;
    /** Lista dei numeri univoci disponibili */
    private List<String> availableUniqueNumbers = new ArrayList<>();
    /** Lista delle schede di rete attualmente connessi */
    private List<NetworkCard> connectedNetworkCard = new ArrayList<>();

    private Network() {
        inizializeUniqueNumbers();
    }

    /**
     * Resetta la rete, distruggendo l'instanza singleton
     */
    public void reset() {
        singleton = null;
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
     * @param nC scheda di rete che richiede la connessione
     * @return identificativo che la scheda userà
     */
    public String getUniqueNumber(NetworkCard nC) {
        if (availableUniqueNumbers.size() <= 0)
            return null;
        else {
            connectedNetworkCard.add(nC);
            return availableUniqueNumbers.remove(0);
        }
    }

    /**
     * Disconnetti la scheda di rete dalla rete resctituendo l'identificativo al
     * pool di quelli disponibili
     * 
     * @param nC scheda di rete che richiede la disconnessione
     * @return null
     */
    public String realeaseUniqueNumber(NetworkCard nC) {
        if (!connectedNetworkCard.contains(nC))
            return null;

        connectedNetworkCard.remove(nC);
        availableUniqueNumbers.add(nC.getNumber());
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
        for (NetworkCard nC : connectedNetworkCard)
            if (nC.getNumber().equals(des))
                return nC.notifyMessage(src, message);
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
        for (NetworkCard nCard : connectedNetworkCard) {
            result += nCard.getPhonePublicInfo() + "\n";
        }
        return result;
    }

}