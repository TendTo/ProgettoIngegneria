package Network;

/**
 * Interfaccia comune a tutti i dispositivi che sono in grado di interfacciarsi
 * con la rete
 */
public interface NetworkSubscriber {
    /**
     * Getter per il numero univoco che identifica il dispoitivo nella rete
     * 
     * @return numero identificativo
     */
    public String getNumber();

    /**
     * Manda un messaggio al disitivo destinatario attraverso la rete
     * 
     * @param sub     subscriber da cui si riceve il messaggio
     * @param des     numero del dispositivo destinatario
     * @param message messaggio da inviare
     * @return se il messaggio è stato inviato con successo
     */
    public boolean notifyMessage(Object sub, String src, String message);

    /**
     * Mostra le info pubbliche del dispositivo quando richieste dalla rete
     * 
     * @return info pubbliche del dispositivo
     */
    public String getPublicInfo();
}