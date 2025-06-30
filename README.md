# ARCHITETTURA

## Design architetturale

L'architettura è composta da 4 microservizi: receivermanager per la gestione dei destinatari, requestmanager per la gestione delle richieste d'invio, deliverytracker per il tracciamento dei cambi di stato delle richieste di invio e l'internalnotificator per la gesitione delle notifiche.

Il flusso si compone così:
- All'inserimento/modifica di un destinatario su DB mySQL viene triggerato un evento verso un topic di tracking, al quale un'altra componente del sistema può attingere. In caso di fallimento dell'invio, il dto viene persistito insieme al codice dell'errore su una collection MongoDB.
- All'inserimento di una richiesta di invio su DB mySQL,  viene inviato un evento su un topic kafka, al quale attingono il  deliverytracker e l'internalnotificator per essere notificati degli inserimenti/cambiamenti di stato delle richieste di invio. Si suppone che un eventuale servizio che avanza lo stato delle richieste scriva sullo stesso topic. In caso di fallimento in fase di scrittura, si persiste l'evento su MongoDB. Prima dell'inserimento requestmanager effettua una chiamata rest a receivermanager per controllare la validità dei destinatari.
- Alla lettura di un evento sul topic di uscita di requestmanager, il deliverytracker salva la richiesta nel suo database, al fine di renderne disponibile lo stato tramite API rest. In caso di errori durante la transazione, l'evento viene salvato su mongo.
- Alla lettura di un evento sul topic di uscita di requestmanager, l'internalnotificator produce e salva la notifica su database al fine di tracciamento, succesivamente la invia su un topic dedicato.

Si ipotizza un API Gateway a monte dell'architettura per esporre solo le API ad uso esterno.

## Design dei microservizi

Il modello architetturale scelto per i microservizi e quello dell'architettura esagonale.
Il dominio e indipendente dalle tecnologie utilizzate per la persistenza e l'invio/ricezione di eventi, grazie all'utilizzo di interfacce definite "porte". I servizi del dominio dipendono esclusivamente da esse, mentre agli adattatori è demandata l'implementazione delle porte.
Gli adattatori sono iniettati nei servizi del dominio secondo il principio dell'inversione del controllo, grazie all'IoC container di Spring.
Ad ogni porta "di ingresso" è associato un facade denominato handler, al quale è demandato il compito di orchestrazione del flusso.
I service fungono da validator e da proxy verso i servizi di persistenza. Un'eventuale logica di business sarebbe implementata a livello dei service.
Vengono impiegati factory e mapper per tradurre l'oggetto degli adattatori in DTO di dominio.
Tale modello grantisce riutilizzabilità, espandibilità e scalabilità grazie all'elevato grado di disaccoppiamento dei modelli.
Dominio ed adattatori sono nello stesso modulo al fine di non violare eccessivamente il principio KISS, essendo il progetto di scope limitato.

Di seguito il flusso generico dei microservizi:

RESTController/KafkaConsumer -> Factory -> RequestHandler/EventHandler -> (Service -> PersistenceService)/NoSqlService/MessageProducer.

# SCELTE PROGETTUALI

Di seguito gli assunti e le scelte progettuali raggruppati per microservizio.

## receivermanager

È stato scelto il codice fiscale come identificativo univoco per i destinatari, in quanto essi sono stati intesi come persone piuttosto che come meri indirizzi. 
Da questo assunto la necessità di legare i destinatari agli utenti con una relazione molti a molti. Per prelevare i destinatari correttamente è necessario inserire in richiesta uno username(identificativo univoco dell'utente richiedente).
I destinatari, possono variare nell'indirizzo o nella validità solo grazie ad un servizio esterno che richiama l'api, da qui la necessità di loggare su un topic le variazione al fine di allineare gli ipotetici sistemi che si servono di tale informazione.

## requestmanager

Il servizio ha il solo scopo di inserire una richiesta e tenere traccia degli inserimenti. Esso, insieme al deliverytracker forma una sorta di CQRS distribuito.
Uno UUID denominato TraceParent ha lo scopo di correlare gli eventi tra i diversi sistemi.

## deliverytracker

Il servizio funge da inquiry per le richieste di spedizione. Al variare di una richiesta il record con il traceparent corrispondente viene sovrascritto. Esso mantiene timestamp di inserimento e timestamp di ultima modifica al fine di monitorare i tempi di evasione di una richiesta.

## internalnotificator

Il servizio persiste ed invia una notifica verso un topic "outbox" ad ogni cambiamento di stato. Se la notifica esiste già per una coppia di traceparent/stato essa non viene duplicata.

# SEMPLIFICAZIONI

- Il serivzio degli Utenti e il servizio per il cambiamento di stato delle richieste esistono solo in via teorica.

- I servizi sono privi di Securizzazione.

- I test sono stati realizzati non allo scopo di raggiungere la coverage ma di effettuare dei test efficaci.

- Gli adattari sono nello stesso modulo del dominio al fine di limitarne la complessità, dato lo scope ridotto del progetto.

# CONSIDERAZIONI FINALI

Il progetto potrebbe risultare legggermente overingegnerizzato data la portata ridotta, tuttavia è stato pensato come un progetto in ambito reale, dunque con uno sguardo verso future evolutive, quali aggiornamenti, cambio tecnologie ecc.


## NOTE
Ogni microservizio presenta nella cartella misc le istruzioni per i test. I database saranno vuoti(fatta eccezione per la tabella USERS), dunque prima di ogni operazione di GET si consiglia di inserire qualche record mediante API/evento.
