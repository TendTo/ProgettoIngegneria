# PhoneSimulator
 Repository per il codice del progetto di Ingegneria del Software
 
 
 ### Documentazione
 [Documentazione completa](https://docs.google.com/document/d/1Qp8zCO5xep3hJsoPorasWVTrHCBqD0262AY88BYDcUI/edit?usp=sharing)
 
 Sono consentiti (e richiesti) i commenti
 
 
 ### UML software
 ![UML del software](Assets/ProgettoPhone.png)

### Set di istruzioni
Le istruzioni vengono seguono le convenzioni di un normale terminale, ma sono case insensitive<br>
es. AggiungiContatto “Nome del contatto” 311134<br>
  EliminaContatto nome<br>
  cOnNEtTi<br>
| Istruzione | n° parametri | parametri | descrizione |
| ----------- | ----------- | ----------- | ----------- |
| Info | 0 | | Visualizza le info del dispositivo, quali nome utente, numero e stato (“connesso” o “non connesso” |
| ImpostaNome | 1 | nome | Imposta il nome utente del dispositivo |
| AggiungiContatto | 2 | “nome contatto” numero | Aggiungi il contatto in rubrica, se non ne esiste già uno con lo stesso nome o numero |
| EliminaContatto | 1 | “nome contatto” | Rimuovi dalla rubrica il contatto che ha quel nome |
| CercaRubrica | 1 | pattern | Visualizza tutti i contatti della rubrica il cui nome contiene i caratteri inseriti |
| MostraRubrica | 0 | | Visualizza tutti i contatti nella rubrica |
| Connetti | 0 | | Connetti il dispositivo alla rete |
| Disconnetti | 0 | | Disconnetti il dispositivo dalla rete |
| MostraConnessi | 0 | | Se si è connessi, mostra le info di tutti dispositivi attualmente connessi alla rete |
| InviaMessaggio | 2 | [“nome contatto”, numero] mes | Invia un messaggio al dispositivo con il numero scelto. Si può anche inserire il nome di un contatto presente in rubrica. È necessario che entrambi i dispositivi siano connessi alla rete |
