# PPS-22-energy-mng

## Introduzione

Questo report è relativo allo sviluppo dell'applicazione Energy Management.
L'applicazione consente il monitoraggio dei consumi energetici e del costo ad essi associato(energia idrica, energia termica ed energia elettrica) da parte degli utenti, siano essi utenti private o aziende.
Mediante questa applicazione l'utente può monitorare i consumi e il conseguente impatto economico che ne deriva sia in misura personale e sia per una specifica città o una specifica regione.
L'applicazione consente anche di effettuare delle previsioni sulla base dell'andamento storico dei consumi e dei costi in modo da fornire all'utente una panoramica più ampia di quella che potrà essere la situazione futura.

## Processo di sviluppo adottato
Il processo di sviluppo adottato per questo sistema è guidato dal framework SCRUM per la gestione di progetti. In particolar modo, con riferimento a SCRUM, l’organizzazione è basata su sprint settimanali ripetuti.

**Sprint settimanali**:

- Sprint planning: Lunedì mattina
- Durata Sprint: 20 ore
- Daily Sprint
- Sprint Review: Venerdì pomeriggio

Supporto a questo processo di sviluppo è fornito dal software Trello, utilizzato per l’organizzazione strutturata del lavoro.

Come strumento di build è stato scelto **sbt**, come strumento di test si è scelto di adottare la libreria **ScalaTest** mentre come strumento di continuous integration si è scelto utilizzare **GitHub**

## Requisiti

### Requisiti di business

| ID | Requisito |
| -- | --------- |
| 1.1 | Il software Energy Management consente di monitorare l’andamento dei consumi energetici ed i costi connessi ai consumi di un singolo utente, di una specifica città, di una specifica regione, dell’intero paese. |
| 1.2 | Il software Energy Management consente di effettuare previsioni sull’andamento futuro di consumi energetici e dei costi associati ai consumi di un singolo utente, di una specifica città o di una specifica regione. |
| 1.3 | Il software Energy Management consente di effettuare il confronto tra i consumi energetici e i costi associati ai consumi  tra due città o tra due regioni. |
| 1.4 | Il software Energy Management fa fronte alla difficoltà che gli utenti hanno di avere una visione completa ed approfondita dei propri consumi energetici. |
| 1.5 | Il software Energy Management può essere utilizzato da utenti privati per il monitoraggio dei propri consumi energetici domestici e dei costi associati ad essi. |
| 1.6 | Il software Energy Management può essere utilizzato da aziende per il monitoraggio dei consumi energetici dell’azienda stessa e dei costi associati ad essi. |
| 1.7 | Il software Energy Management fornisce dati sui consumi energetici e sui costi associati ad essi con un alto livello di affidabilità. |

### Requisiti utente

| ID | Requisito |
| -- | --------- |
| 2.1 | L’utente può essere un utente privato o un’azienda. |
| 2.2 | L’utente è presente nel sistema con un identificativo univoco. |
| 2.3 | L’utente deve risiedere in una specifica città e in una specifica regione. La regione e la città devono essere esistenti e la città deve essere presente nella regione. |
| 2.4 | L’utente visualizzerà i propri consumi energetici e i costi ad essi associati. |
| 2.5.0 | L’utente visualizzerà i consumi energetici relativi alla città in cui risiede ed i costi ad essi associati. |
| 2.5.1 | Nel caso di utente privato l’utente visualizzerà una media dei consumi energetici e dei costi ad essi associati di tutti gli utenti privati presenti nella città in cui risiede. |
| 2.5.2 | Nel caso di azienda l’utente visualizzerà una media dei consumi energetici e dei costi ad essi associati di tutte le aziende presenti nella città in cui risiede. |
| 2.6.0 | L’utente visualizzerà i consumi energetici relativi alla regione in cui risiede ed i costi ad essi associati. |
| 2.6.1 | Nel caso di utente privato l’utente visualizzerà una media dei consumi energetici e dei costi ad essi associati di tutti gli utenti privati presenti nella regione in cui risiede. |
| 2.6.2 | Nel caso di utente privato l’utente visualizzerà una media dei consumi energetici e dei costi ad essi associati di tutti gli utenti privati presenti nella regione in cui risiede. |
| 2.7 | L’utente può monitorare i consumi di energia termica, idrici ed energia termica relativi ad un’unica linea intestata. |

### Requisiti funzionali

| ID | Requisito |
| -- | --------- |
| 3.1.0 | L’utente sceglie se registrarsi al sistema come utente privato o come azienda. |
| 3.1.1 | L'utente inserisce i seguenti dati: UserID, Password, Tipologia utente, Regione, Città. |
| 3.1.2.1 |  Il sistema verifica che la regione inserita sia valida. |
| 3.1.2.2 | Il sistema verifica che la città sia valida. |
| 3.1.2.3 | Il sistema verifica che la città inserita si trovi nella regione inserita. |
| 3.1.2.4 | Il sistema verifica che lo UserID inserito non sia già presente all'interno del sistema. |
| 3.1.3 | Dopo la verifica dei dati, nel caso di verifiche concluse con successo, il sistema consente all'utente di registrarsi. |
| 3.1.4 | Nel caso di verifica non conclusa con successo il sistema non consente all'utente di registrarsi e lo notifica con opportuno messaggio d'errore. |
| 3.1.5 | Il sistema deve memorizzare l'hash code associato alla password inserita dall'utente | 
| 3.2.0 | Per la fase di accesso al sistema, dopo la registrazione, l'utente inserisce lo userID e la password. |
| 3.2.1 | Il sistema verifica la correttezza formale dei dati inseriti e che l'UserID effettivamente esista nel sistema con ad esso associato l'hash code relativo alla password inserita dall'utente. |
| 3.2.2 | Nel caso di verifica conclusa con successo l'utente ottiene accesso al sistema |
| 3.2.3 | Nel caso di verifiche non concluse con successo l'utente non ottiene accesso al sistema e viene notificato della natura dell'errore |
| 3.3.0 | Il sistema deve consentire la visualizzazione dei dati richiesti dall’utente. |
| 3.3.1 | Il sistema deve recuperare i dati sui consumi energetici  con annessi costi relativi ad un utente. |
| 3.3.2 | Il sistema deve mostrare i dati all’utente relativi alle diverse tipologie di consumi e ai costi connessi a ciascun consumo. |
| 3.3.3 | Il sistema deve consentire all’utente di visualizzare i dati relativi ai consumi della propria città (come specificato in 2.5.0). |
| 3.3.4 | Il sistema deve consentire all’utente di visualizzare i dati relativi ai consumi della propria regione (come specificato in 2.6.0). | 
| 3.3.5 | Il sistema deve consentire una visualizzazione dei dati su base mensile, raggruppando e calcolando la media di consumi per città/regione inerenti allo stesso mese. | 
| 3.3.6 | L’utente può scegliere una città o una regione diversa dalla propria per la quale visualizzare i dati. |
| 3.3.7 | Il sistema deve mostrare all’utente consumi e costi relativi alle diverse tipologie di consumi, con i costi associati ad essi, rispetto alla città/regione scelta dall’utente. | 
| 3.3.8.0 | Il sistema consente di visualizzare previsioni sull’andamento dei consumi. |
| 3.3.8.1 | Il sistema consente all’utente di scegliere la tipologia di consumo e l’anno su cui effettuare la previsione. Il sistema sulla base di questa scelta e sulla base dei consumi storici dell’utente mostra le previsioni per l’anno selezionato. | 
| 3.3.8.2 | Il sistema consente all’utente di scegliere la città, la tipologia di consumo e l’anno su cui effettuare la previsione. Il sistema sulla base di questa scelta e sulla base dei consumi storici per quella specifica città mostra all’utente le previsioni per l’anno selezionato. |
| 3.3.8.3 | Il sistema consente all’utente di scegliere la regione, la tipologia di consumo e l’anno su cui effettuare la previsione. Il sistema sulla base di questa scelta e sulla base dei consumi storici per quella specifica regione mostra all’utente le previsioni per l’anno selezionato. | 
| 3.3.9 | Il sistema deve consentire all’utente di visualizzare dati, tipologia di consumi, e previsioni relative ad un certo anno dell’intero paese. |
| 3.4 | Il sistema deve presentare i consumi di ciascun utente, di modo che sia possibile tenere traccia della tipologia di consumi, del consumo effettivo, del costo relativo al consumo, la data di riferimento, la regione, la città e l’UserID a cui fa riferimento. |
| 3.5 | Il sistema utilizza i dati relativi alla registrazione di ciascun utente per generare in maniera automatica i consumi relativi all’utente stesso. |

### Requisiti non funzionale

| ID | Requisito | 
| -- | --------- |
| 4.1 | Il sistema deve memorizzare i dati in maniera consistente. |
| 4.2 | Il sistema deve ridurre al minimo le situazioni in cui i dati visualizzati dall’utente non corrispondano effettivamente ai dati visualizzati o generati per l’utente stesso. |
| 4.3 | Il sistema deve generare i dati relativi ai consumi con intervalli temporali ben definiti. |
| 4.4 | Il sistema deve essere in grado di gestire molteplici utenti. |
| 4.5 | I dati devono essere memorizzati e presentati agli utenti in un formato leggibile e con le opportune unità di misura. |

### Requisiti di implementazione

| ID | Requisito |
| -- | --------- |
| 5.1 | Il sistema deve essere in grado di girare su di una generica JVM, quindi con una forte indipendenza rispetto alla macchina sottostante. |
| 5.2 | Per la memorizzazione il sistema deve utilizzare un database NoSQL dal momento che risulta più appropriata la lavorazione di dati di questa tipologia con un database basato sui documenti. |
| 5.3 | Per l’implementazione si utilizza l’IDE IntelliJ di modo da poter sfruttare tutte le peculiarità di un ambiente integrato. |
| 5.4 | Il linguaggio di programmazione da utilizzare per l'implementazione è **Scala** |

## Design architetturale

### Architettura generale del sistema

Elemento centrale dell’architettura di Energy Management è l’utente (sia esso utente privato piuttosto che azienda).
Dall’utente partono due flussi operazionali differenti:

- Registrazione: con la registrazione l’utente memorizza le sue informazioni all’interno del database. Tale componente si occupa di effettuare tutti i controlli del caso.
- Successivamente queste informazioni (che corrispondono ai dati forniti dall'utente all'atto di registrazione) sono prese da un generatore di consumi che genera i consumi stessi, come specificato nel requisito funzionale 3.5. Questi consumi generati vengono memorizzati all’interno del DB e saranno poi recuperati da un generatore di bollette relative che, partendo dai consumi recuperati dal database, costruisce le bollette in modo opportuno di modo che queste possano essere gestite dal componente predisposto alle operazioni. Tale componente recupera le bollette costruite e sulla base di queste esegue delle operazioni, queste operazioni saranno poi utilizzate per visualizzare i dati specifici richiesti dall’utente dopo il login.
- Login: con il login l’utente ottiene accesso al sistema ottenendo la possibilità di scegliere quali informazioni visualizzare, relative a quali consumi a quale città. Con il login l’utente ottiene la possibilità di operare ed interfacciarsi con le varie operazioni fornite dal sistema.

Operazioni centrali del sistema sono le seguenti:

- Costruzione delle bollette: questa operazione è cruciale dal momento che recupera in maniera continuativa dati dal database relativo ai consumi e costruisce le relative bollette. La costruzione di tali bollette è un’operazione complessa dal punto di visto di interfacciamento con il DB in quanto risulta fondamentale per il soddisfacimento del requisito non funzionale 4.2.
- Operazioni sulle bollette costruite: queste operazioni risultano cruciali per poter permettere all’utente di visualizzare i dati richiesti. Si tratta quindi di operazioni di computazione che espongono i risultati in modo che questi siano visibili all’utente.

Di seguito è riportata l'architettura generale del sistema:

![general-architecture](https://user-images.githubusercontent.com/91571686/177432101-7400a2cb-9801-4ca7-ae93-4e8740dd25a1.png)



- **Registrazione**: la registrazione riceve in input i dati dell’utente quando questo vuole registrarsi al sistema. Esegue una serie di controlli di validità sui dati forniti in input come specificato nei requisiti 3.1.22 e in caso di successo registra l’utente al sistema memorizzando i dati all’interno del database degli utenti.
- **Login**: Il login riceve in input lo UserID e la password ed esegue i controlli sul database degli utenti per verificare se lo specifico userID è presente. Nel caso in cui questo sia presente allora verifica la password e nel caso di corrispondenza consente all’utente di accedere al sistema.
- **Generatore consumi**: Il generatore dei consumi recupera i dati memorizzati all’interno del database degli utenti con l’obiettivo di generare, sulla base di questi dati recuperati, dei consumi random. E’ importante, con l’obiettivo di garantire consistenza e nell’ottica di rispettare il requisito non funzionale 4.2 che la richiesta al DB avvenga con una frequenza corretta, di modo da non causare overload del sistema. Il generatore, per ogni utente recuperato ad intervalli regolari genera dei consumi random che vengono successivamente memorizzati all’interno del database dei consumi.
- **Database utenti**: il database utenti memorizza le informazioni relative agli utenti che hanno effettuato la registrazione al sistema.
- **Database consumi**:  il database dei consumi memorizza i consumi generati dal generatore dei consumi relativi agli utenti che sono presenti all’interno del sistema.
- **Costruttore bollette**:  il costruttore delle bollette recupera i dati memorizzati all’interno del database dei consumi e li utilizza per generare le relative bollette. Le bollette hanno una propria struttura ed è opportuno che siano generate in modo conforme alla struttura stessa nell’ottica di poter essere utilizzate in modo ottimale dal componente predisposto alle operazioni. Con l’obiettivo di ridurre al minimo problemi di inconsistenza non generando bollette per consumi effettivamente presenti e nell’ottica di soddisfare quindi il requisito non funzionale 4.2 l’interazione tra il costruttore delle bollette e il database dei consumi avviene con una frequenza elevata.
- **Operazioni bollette**:  le operazioni vengono effettuate sulle bollette costruite a partire dai dati recuperati dal database dei consumi.
- **Dashboard**:  è il componente che consente all’utente di interagire con il sistema. Mediante questo componente l’utente riesce ad esprimere quali sono i dati che intende visualizzare e quali sono le previsioni con relativi parametri che intende eseguire. Questo componente sfrutta, quindi, le operazioni derivanti dal componente **Operazioni bollette**.

### Pattern

Energy Management è un’applicazione con un forte flusso informativo verticale, in cui è possibile individuare quattro livelli. Per questa ragione il pattern architetturale scelto è il **Layered Pattern**.

![pattern](https://user-images.githubusercontent.com/91571686/178061042-19be6ff0-f79a-41d1-9f35-bce0776c4e90.png)





La scelta di questo pattern è stata dettata dalla possibilità fornita dallo stesso di poter posizionare le varie componenti precedentemente elencate in un livello specifico.

- **Data layer**: in questo livello troviamo il database degli utenti, il database dei consumi, il componente relativo alla registrazione,il componente relativo alla gestione degli errori della registrazione e del login, il componente relativo al login  e quello relativo al generatore dei consumi.
- **Business logic layer**: in questo livello troviamo il componente dedito alla costruzione delle bollette.
- **Application logic layer**: in questo layer è posizionato il componente predisposto alle operazioni sulle bollette.
- **Presentation layer**:  in questo livello è posizionato il componente Dashboard ed il componente Main che consente l’interazione con l’utente per le specifiche operazioni.

La rappresentazione del grafico e la direzione del flusso di dati rende ancora più evidente il flusso informativo. L'utente accede solamente attraverso un punto di accesso al sistema mentre tutto le informazioni vengono generate dai dati e successivamente elaborate dai vari livelli sulla base delle operazioni richieste.
Il sistema è stato inoltre pensato per essere un sistema distribuito in cui diversi utenti possono accedere, in maniera simultanea ai dati relativi ad essi, ad una certa città o una certa regione.


## Design di dettaglio

Si presenta ora il design di dettaglio relativo alle varie componenti architetturali precedentemente presentate:

### Utente

L’utente è l’elemento centrale dell’applicazione. Infatti è l’utente che effettua le scelte relative alla tipologia di dati da visualizzare e a quali previsioni effettuare all’interno della Dashboard.
All’interno del sistema sono presenti due tipologie di utenti: l’utente privato e l’azienda. Tuttavia questa distinzione viene effettuata all’atto di registrazione. Al momento del login l’utente viene creato con i dati rilevanti recuperati dal DB in corrispondenza del proprio username e viene istanziato con un campo che riporta la tipologia di utente a cui ci si riferisce.  Quest’ultimo punto è estremamente importante per riportare i consumi relativi ad una certa località con riferimento agli utenti privati o alle aziende.
Il seguente diagramma UML mostra e formalizza quanto detto:

![user](https://user-images.githubusercontent.com/91571686/178055474-73ce61a3-931e-452e-afa2-da2b9cbbd6bc.png)


L’utente si trova nella Dashboard, in particolar modo, come spiegato successivamente, la Dashboard è istanziata ricevendo in ingresso un Utente.
Dal diagramma riportato è possibile notare come un utente venga istanziato tenendo conto del suo userID (univoco all’interno del sistema) la propria città, la propria regione e la tipologia di utente. In particolar modo la tipologia di utente risulta importante sia per effettuare le previsioni relative ad una certa località geografica (le previsioni sull’andamento dei consumi privati o aziendali relativi ad una certa città o regione) e sia per mostrare l’andamento dei consumi in una certa località geografica (andamento dei consumi privati o aziendali relativi ad una certa città o regione)

### Registrazione

La registrazione è la prima operazione che un utente esegue quando vuole interfacciarsi all’applicazione.
L’operazione di registrazione ha uno stretto legame con il database degli utenti già presentato nella fase di architettura, dal momento che è necessario verificare l’unicità dello UserID (che è il modo con cui un utente viene verificato all’interno del sistema).
Il componente registrazione esegue solo due operazioni:

- Ricava l'hash code relativo alla password ricevuta in input
- Invia i dati presentati in input con l’hash code della password al database.


Il seguente diagramma UML mostra e formalizza quando detto:

![registration](https://user-images.githubusercontent.com/91571686/177271294-772b2d8a-2a6d-451e-9df5-cf3c873e635d.png)

Come mostrato da questo diagramma il metodo signUP riporta una stringa contenente il risultato dell’operazione di registrazione. Nel caso di errore riscontrato in violazione delle verifiche esplicitate nel requisito 3.1.2 la stringa riporta anche la motivazione dell’errore.
Dal momento che tale componente prevede l’interazione con il database degli utenti per la verifica dell’unicità dello userID di seguito è riportato il corrispondente diagramma delle attività:

![activity-diagram-registration](https://user-images.githubusercontent.com/91571686/177714692-1ad3c821-7769-4cb7-a27a-ad67dfbfe0d6.png)


### MD5

MD5 è una funzione di hashing che ha il compito di svolgere l’hash function della password e può essere richiamata da altri componenti. Di seguito il diagramma di flusso relativo all’hashing della password.

![MD5-activity-diagram](https://user-images.githubusercontent.com/91571686/177714866-486e67b0-e73e-47f7-83a2-d3f7015cbde8.png)


### Login

L’operazione di login consente all’utente di guadagnare accesso al sistema verificando prima che l’userID inserito dall’utente sia presente all’interno del database degli utenti e successivamente verificando che la password memorizzata per lo specifico userID sia effettivamente quella inserita dall’utente.
Allo scopo di fare questo la password recuperata dal database viene hashata e successivamente viene confrontata con l’hash code relativo alla password inserita dall’utente. 
Nel caso in cui le password combaciassero dal database degli utenti vengono recuperati tutti i dati utili per ritornare l’oggetto utente associato a quello userID.
Il seguente diagramma UML mostra e formalizza quanto detto:

![login](https://user-images.githubusercontent.com/91571686/177271754-b5037ea4-2902-4f62-9edd-070906f4bc74.png)

### Generatore consumi

Questo componente è pensato per essere un componente sempre attivo in grado di generare dei consumi con cadenza periodica. Per poter generare i consumi il generatore recupera la lista di utenti dal database degli utenti. Ad ogni aggiornamento di questa lista vengono generati i consumi per tutti gli utenti e vengono inseriti all’interno del database dei consumi. Il flusso di esecuzione di questo componente è il seguente:
- recupero degli utenti presenti all’interno del database degli utenti.
- generazione dei consumi a partire dalla lista e invio dei consumi al database dei consumi. In particolar modo, si attende il completamente del recupero dei dati e poi si effettua la generazione.
- dopo il termine della generazione dei consumi si attende una definita quantità di tempo e si effettua la richiesta al database per il recupero degli utenti.

Risulta evidente da quanto detto che le operazioni eseguite da questo componente si eseguono ciclicamente.
La generazione avviene per ogni tipologia di consumo.

Ogni generazione, oltre ai dati tipici dei consumi quali consumo effettivo e costo ad esso associato, considera anche i dati recuperati dal database degli utenti come userID, città, regione.
Ulteriore elemento importante è assegnare ad ogni consumo generato un proprio id.
Questo, come successivamente descritto, è importante per la costruzione della lista di bollette realizzata con i dati recuperati dal database dei consumi.
Questo componente espone verso l’esterno una sola operazione, la generazione, la quale prevede contestualmente l’invio dei dati al database dei consumi. 

![generator](https://user-images.githubusercontent.com/91571686/177272162-7b937a52-e2e0-49ea-895a-7328b7b8917d.png)

Aspetto importante per quanto riguarda la generazione dei consumi e che questi devono essere generati con un mese ed un anno definiti.

### Database

Per quanto riguarda il database (sia quello relativo agli utenti sia quello relativo ai consumi) la scelta è stata quella di utilizzare un elemento che potesse effettivamente mediare tra la necessità di:
- recuperare i dati relativi agli utenti e aggiungere un nuovo utente.
- recuperare i dati relativi ai consumi per poter costruire la lista delle bollette sulla quale poter eseguire le operazioni di ricerca.

### Costruttore bollette

Il costruttore delle bollette ritorna la lista delle bollette ricavata interrogando in un certo istante il database dei consumi. 
Il primo elemento da considerare riguarda la frequenza con cui questo componente interroga il database con l’obiettivo di costruire la lista.
Tuttavia, trattandosi di un costruttore di bollette è necessario considerare anche l’entità bolletta.
Il seguente diagramma UML riporta e formalizza la classe relativa alle bollette.

![bill](https://user-images.githubusercontent.com/91571686/177715031-9b994493-e5f8-445c-827b-3d72df989561.png)



Una volta definita l’entità bolletta è opportuno definire il costruttore delle bollette.
Come già riportato tale costruttore interroga periodicamente il database dei consumi, recupera i dati in esso contenuti e sulla base di questi dati costruisce le bollette.
Tutte le bollette vengono salvate, man mano che la costruzione procede, all’interno di una lista.
In virtù della periodicità con cui questo componente opera è da ritenersi un componente sempre attivo.
Questo passaggio è estremamente importante dal momento che il sistema prevede un binding molto forte tra questa lista ed il componente che si occupa di effettuare le operazioni sulle bollette.
Il flusso di attività che il costruttore delle bollette esegue è quindi il seguente:
- recupero dei dati contenuti all’interno del database dei consumi
- costruzione della lista delle bollette a partire dai dati recuperati

Un aspetto importante da rimarcare è che questo è un flusso di attività ciclico. In particolar modo il componente attende di aver recuperato i dati, costruisce le bollette e dopo la costruzione esegue un’altra interrogazione al database.
Il seguente diagramma UML mostra e formalizza il costruttore delle bollette:

![billBuilder](https://user-images.githubusercontent.com/91571686/177272570-c4c3d17a-27a3-47b2-a1e3-c2eddf93ac27.png)

### Operazioni bollette

Il componente preposto alle operazioni sulle bollette si trova nel mezzo tra la Dashboard (che richiama le operazioni su uno specifico utente) e il costruttore delle bollette che fornisce la lista delle bollette aggiornate su cui poter eseguire le operazioni.
Le operazioni che vengono effettuate da tale componente si suddividono in due categorie:
- Operazioni di semplice ritorno e filtraggio rispetto a parametri richiesti:
  - utilizzo su base mensile per una specifica utenza
  - costo su base mensile per una specifica utenza
  - utilizzo su base mensile per una specifica località
  - costo su base mensile per una specifica località
- Operazioni di previsioni sulla base di determinate specifiche:
  - previsioni individuali per una specifica utenza relativamente ad uno specifico anno
  - previsioni per una specifica località relativamente ad uno specifico anno

Le operazioni definite da questo componente trovano applicazione in quelli che sono i metodi che ciascun utente può richiamare per avere una visualizzazione delle informazioni richieste.

Il seguente diagramma UML mostra e formalizza quanto detto.

![bill-operations](https://user-images.githubusercontent.com/91571686/177720355-7a90f78b-eb93-4ea8-ab6e-23446bffb9d9.png)



### Dashboard

La Dashboard è il punto in cui il sistema viene utilizzato dall’utente. In particolar modo si occupa di ricevere gli input dall’utente che ha effettuato il login e di visualizzare i risultati delle richieste ottenute.
All’interno del metodo esposto la dashboard consente l’interazione con l’utente sfruttando i metodi definiti all’interno della classe User.
Il seguente diagramma UML mostra e formalizza quanto detto:

![dashboard](https://user-images.githubusercontent.com/91571686/177273475-55a9dd4e-89ff-47f4-a303-40f32dc75710.png)

## Implementazione

### Organizzazione del codice

Il codice è stato organizzato in package. In particolar modo sono presenti 4 package principali, ognuno dei quali si mappa con quelli che sono i layer definiti nel pattern architetturale utilizzato. All'interno di ciascun package sono presenti altri package relativi ai componenti precedentemente definiti. Questi sotto-package corrispondono ai componenti presenti all'interno dei layer come definito precedentemente.

### Antonio Iannotta

Implementazione dei seguenti componenti:
- **object** MongoDB
- **object** BillOperations
- **class** Bill
- **object** Utils
- **class** MongoDBTest
- **class** BillTest
- **class** UtilsTest

#### MongoDB (In collaborazione con Andrea Catani)

L'implementazione di questo componente, le cui operazioni sono rappresentate nel seguente diagramma UML, regolano
l'interazione del sistema con il supporto scelto per memorizzare i dati. Nel caso in esame si è scelto come supporto di utilizzare
MongoDB come database NoSQL.

![MongoDB](https://user-images.githubusercontent.com/91571686/177780450-2a5a1312-9abb-4a27-919d-9c7403b77ce9.png)


Come è possibile notare le operazioni consentono un'interazione con il database per il recupero delle informazioni memorizzate
sottoforma di documenti in due collezioni: *usages* e *users*. Il recupero delle informazioni consente di creare una
lista di entità già definite nel sistema (User e Bill).
Dal momento che tale componente funge da mediazione tra componenti operazionali e supporto di memorizzazione esponendo semplicemente operazioni per il recupero dei dati si è deciso di modellarlo come *object*
Per l'implementazione di tale componente si è cercato di massimizzare insieme KISS and DRY.
Un aspetto da rimarcare riguarda il fatto che tale componente è strettamente legato all'object Helpers che espone i metodi
per l'elaborazione dei dati ricevuti eseguendo una find su una certa collezione.

#### BillOperations

BillOperations si occupa di eseguire le operazioni sulla lista dei dati raccolti dal database mediante BillBuilder. In particolar modo l'implementazione di questo componente è proceduta di pari passo con l'implementazione della libreria ausiliaria Utils. Entrambi questi componenti sono contenuti all'interno del package applicationLogicLayer.billOperations in modo da rendere ancora più evidente la dipendenza l'uno dall'altro. Dal momento che BillOperations espone i propri metodi all'utente senza la necessità di alcuna istanziazione si è deciso di modellarlo come **object**

#### Utils
La libreria Utils è stata sviluppata per fornire a BillOperations tutti i metodi ausiliari di cui necessitasse per poter eseguire al meglio le operazioni sulla lista delle bollette recuperate dal database. Le operazioni esposte da questa libreria sono le seguenti:

![Utils](https://user-images.githubusercontent.com/91571686/177801860-3f0583d5-7339-4064-8d9c-6e455225e801.png)

Dal momento che tale componente è un singleton che espone soltanto i propri metodi (utilizzati da BillOperations) si è deciso di modellarlo come un **object**

#### Bill
Il componente Bill è stato realizzato come classe per fornire una corretta astrazione del concetto di bolletta e per consentire inoltre di poter eseguire le operazioni sui consumi partendo da tipi di dati strutturati nella maniera più opportuna possibile. Per la Bill si è deciso di utilizzare una **class** e non come una case class dal momento che è ipotizzabile una futura estensione di questa classe in bollette relative solo ad utenti privati con caratteristiche proprie e bollette relative solo ad aziende con caratteristiche proprie

### Andrea Catani
Implementazione dei componenti:
- **object** BillBuilder
- **object** UsageGenerator
- **object** Helpers

#### BillBuilder
Il componente **BillBuilder** è stato realizzato come **object** poiché essendo un'implementazione del design pattern **singleton**, può essere richiamato da altri componenti senza il bisogno di essere istanziato. La funzionalità di **BillBuilder** è quella di ottenere un **ListBuffer** di oggetti di tipo **Bill** estraendoli dal database. La creazione di tale componente deriva dalla necessità di avere un oggetto che potesse rimodellare gli oggetti di tipo **Bill** coerentemente con le specifiche del progetto, all'interno di **BillBuilder** è possibile infatti, sotto un'ottica di modularità e scalabilità, esporre metodi aggiuntivi per modellare il valore di ritorno a fronte di eventuali richieste future. I metodi esposti sono:
- **Build()**: il metodo si occupa di estrarre i dati dal database ed accorparli in un oggetto di tipo **ListBuffer**.

#### UsageGenerator
Il componente **UsageGenerator** è un componente implementato come una classe **Thread**. Il compito di questo componente è quello di avviare una generazione randomica dei dati delle bollette, organizzarli e caricarli sul database. Grazie alle funzionalità della classe **Thread** è possibile eseguire **UsageGenerator** in maniera parallela al resto del progetto così da simulare l'eventualità, all'interno di un sistema distribuito, in cui vengano inseriti più dati in diversi istanti di tempo.
I metodi che espone sono:
- **run()**: è l'override del metodo ereditato dalla classe **Thread**. Questo metodo avvia la generazione dei dati e li carica sul database.
- **getActualMonthOrYear()**: questo metodo è utilizzato per inizializzare i valori delle variabili **month** e **year** in base ai dati già presenti nel database. Le due variabili riguardano il mese e l'anno a cui dovranno corrispondere le bollette create randomicamente dal metodo **run()**.

### Helpers (In collaborazione con Antonio Iannotta)
Il componente **Helpers**, implementato come **object**, è un oggetto ausiliario al componente **MongoDB**. La sua funzionalità è quella di rielaborare le informazioni ottenute dal database.
Attraverso il metodo **results()** chiamato sull'oggetto di tipo **Observable** restituito dalla funzione **find()** è possibile restituire l'elenco dei dati presenti in una certa collezione.
L'oggetto helpers implementa una classe **implicit** **GenericObservable** che estende il **trait** **ImplicitObservable** e si occupa di convertire i parametri in ingresso in String.
La classe **ImplicitObservable** è una tipo di dato astratto **trait** espone i metodi:

- **results()**: Questo metodo ritorna i dati dal database iscrivendosi all'**Observable** ricevuto in input dal costruttore della sua classe. Ritorna un oggetto di tipo **Seq[C]** riempito con i dati memorizzati sul database.
- **results(String)**: Questo metudo computa l'oggetto di tipo **Seq[C]** ricevuto come output dalla funzione results() e converte ogni elemento in **String**.

![implicitObservable](https://user-images.githubusercontent.com/91571686/178297871-64ca2cad-8443-4e49-8d55-dd4eaf43676b.png)



### Demetrio Andriani
Implementazione dei seguenti componenti:
- **object** Registration
- **object** Regions
- **object** RegistrationErrorCodeHandler
- **object** MD5
- **object** Main
- **class** RegistrationChecker
- **class** RegistrationTest
- **class** PasswordTest

#### Registration
Il componente Registration è stato implementato come un **object** in quanto, essendo un’implementazione del design pattern singleton, è stato considerato il tipo di dato astratto più adatto. Al suo interno si avranno i seguenti metodi:
- signUp(): questo metodo riceve in input i dati dello user nei relativi campi e, dopo averne controllato la validità con il RegistrationChecker,  permette di svolgere l’operazione di registrazione generando una connessione al database ed inviando i dati precedentemente validati (userID, hashed password, userType, region, city);

#### RegistrationChecker
Il componente RegistrationChecker è implementato come una classe. Questa classe viene usata per svolgere un controllo su tutti i campi della registrazione. E’ presente una mappa key/value (LinkedHashMap) utilizzata per associare ad ogni città la sua relativa regione.

I metodi che espone sono:
- checkFields(): ritorna una stringa che identifica tutti i possibili errori presenti nei campi di registrazione, caso contrario restituisce un messaggio di corretta validazione di tutti i campi;
- checkUserID(): ritorna una stringa che identifica tutti i possibili errori relativi al campo dello userID(blank, minore di 6 caratteri, superiore a 20 caratteri), caso contrario restituisce un messaggio di corretta validazione;
- checkPassword():  ritorna una stringa che identifica tutti i possibili errori relativi al campo della password (blank, minore di 6 caratteri, superiore a 20 caratteri), caso contrario restituisce il messaggio di validazione;
- checkUserType(): ritorna una stringa identifica se l’utente è private(0) oppure company(1) e restituisce il messaggio di validazione;
- checkCityRegionMatch(): ritorna una stringa che verifica il corretto collegamento tra la città e la sua relativa regione e restituisce il messaggio di validazione
- checkDuplicateUserId: ritorna un boolean che indica se è gia stato utilizzato oppure no l'userID che l'utente sta inserendo in fase di registrazione. 

#### RegistrationErrorCodeHandler
Il componente RegistrationErrorCodeHandler è stato implementato come un **object**, il cui compito è quello di  associare ad ogni possibile errore presente nel RegistrationChecker un ben definito messaggio, per rendere una più chiara individuazione dell’errore. Qualora non ci dovessero essere errori, il RegistrationErrorCodeHandler presenterà un messaggio di corretta validazione del campo.

#### Regions
Il componente Regions è stato implementato come un **object**,  il cui scopo è quello di associare ad ogni città italiana la relativa regione.

#### MD5
Dal momento che Il componente MD5 deve eseguire un’unica operazione è stato scelto di utilizzare un **object** per incapsularne il comportamento. Il metodo esposto dal componente MD5 è:

- md5HashPassword(): questo metodo ha come valore in input una stringa, cioè la password, e restituisce come valore una stringa, vale a dire l’hash code della password.

#### Main
Il componente Main è stato implementato come un **object**,il cui compito è quello di mostrare una lista delle possibili operazioni che l'utente può svolgere. Al suo interno si avranno i seguenti metodi:
- callRegistration(): metodo che ritorna una stringa, che si occupa di prendere i dati inseriti dall'utente relativi alla Registration e successivamente di chiamare la Registration,
- callLogin(): metodo che si occupa di prendere i dati inseriti dall'utente relativi al Login e successivamente di  chiamare il Login.

### Carlo Di Raddo
Implementazione dei seguenti componenti:
- **object** Login
- **object** ChoiceHandler
- **object** CityManager
- **object** RegionManager
- **trait** CityRegionManager
- **class** LoginChecker
- **class** User
- **class** PrintHelper
- **class** Dashboard

#### Login
Il componente Login è stato implementato come un **object** in quanto, essendo un’implementazione del design pattern singleton, è stato considerato il tipo di dato astratto più adatto. Al suo interno si avranno i seguenti metodi:
-signIN(): questo metodo riceve in input i dati dello user nei relativi campi e, dopo averne controllato la validità con il LoginChecker,  permette di svolgere l’operazione di login facendo una retrive con il database, tornando un oggetto di tipo Option[User] che può contenere l'utente in questione (in caso esso sia effettivamente presente all'interno del database) o un empty (nel caso non dovesse essere presente l'utente all'interno del database)

#### LoginChecker
Il componente LoginChecker è implementato come una **class**. Questa classe viene usata per svolgere un controllo su tutti i campi del login. 

I metodi che espone sono:
- checkFields(): ritorna una stringa che identifica tutti i possibili errori presenti nei campi di registrazione, caso contrario restituisce un messaggio di corretta validazione di tutti i campi;
- checkUserID(): ritorna una stringa che restituisce un messaggio di corretta validazione se lo user non è vuto altrimenti un errore che indica che il campo è vuoto.
- checkPassword(): ritorna una stringa che restituisce un messaggio di corretta validazione se lo user non è vuto altrimenti un errore che indica che il campo è vuoto.

#### User
Il componente User è implementato come una **class**. Questa classe viene utilizzate per definire gli attributi dell'utente e specifica i metodi che è possbile invocare per ottenere determinati output. 

I metodi che espone sono:
-printUser()
-getUsageOrCost(): ritorna i costi o gli usi relativi ad un certo userId e ad un certo tipo di utenza.
-makeIndividualPrediction(): ritorna una predizione per un certo tipo di uso relativo ad un determinato anno. 
-makePredictionByCity(): ritorna una predizione relativa ad una determinata città, ad un determinato tipo di utente (azienda o privato) e ad un determinato anno.
-makePredictionByRegion(): ritorna una predizione relativa ad una determinata regione, ad un determinato tipo di utente (azienda o privato) e ad un determinato anno.
-getUsageOrCostByRegionOrCity(): ritorna una LinkedHashMap[Int,Double] che contine i costi o gli usi relativi ad una certa città o relativi una determinata regione e ad un determinato anno.

#### ChoiceHandler
Il componente ChoiceHandler è stato implementato come un **object**.

I metodi che espone sono:
-cityRegionChoiceHandler(): ritorna una LinkedHashMap[Int,Double], richiamando il metodo getUsageOrCostByRegionOrCity() di User dopo aver interagito con l'utente tramite una serie di stampa in modo da capire la location (città o regione) d'interesse.
-individualChoiceHandler(): ritorna una stringa (elettricità, acqua, gas), questo metodo ha una funzione di supporto nella scelta delle utenze

#### CityRegionManager
Il componente è stato implementato come un **trait** in quanto ha un metodo che deve essere implementato in maniera diversa da due oggetti.

#### CityManager
Il componente è stato implementato come un **object** in quanto estende il trait CityRegionManager ed implementa il solo metodo che possiede nel seguente modo:
-manager(): ritorna un Unit contente una predizione riguardante un utenza di una specifica città di uno specifico anno o un messaggio di errore 

#### RegionManager
Il componente è stato implementato come un **object** in quanto estende il trait CityRegionManager ed implementa il solo metodo che possiede nel seguente modo:
-manager(): ritorna un Unit contente una predizione riguardante un utenza di una specifica regione di uno specifico anno o un messaggio di errore


#### PrintHelper
Il componente PrintHelper è stato implementato come una **class** in quanto dispone di una serire di metodi che aiutano lo sviluppatore nella stampa di messaggi che vengono utilizzati più volte all'interno dell'applicazione. In particolar modo tale componente ha l'obiettivo di risolvere le problematiche del Repet Your Self.

#### Dashboard
Il componente Dashboard è stato implementato come una **class**, tale componente rappresenta il punto d'interazione con l'utente in quato permette di visualizzare a video un menù con un serie di voci relative alle operazione che l'utente può effettuare.



 

  


## Retrospettiva


### Metodologia di sviluppo
Lo sviluppo si è diramato su un arco temporale di 70 ore per studente, per un totale di 280 ore di lavoro. Le iterazioni non hanno avuto tutte la stessa durata, in particolar modo si è deciso di adottare un'iterazione con una durata temporale più ampia nel momento esatto in cui il progetto si è capito fosse in fase di terminazione, di modo da non protarlo perdendo il focus senza, tuttavia, sovraccaricare i vari componenti del gruppo. 
Ciascuna iterazione, data anche la presenza di daily sprint ha previsto che ogni giornata fosse caratterizzata da una breve discussione sull'andamento generale del progetto e su eventuali modifiche che potessero essere apportate in corso d'opera sulle parti assegnate. Da questo si evince nettamente come il processo di sviluppo sia stato estremamente flessibile e caratterizzato da un continuo scambio di idee tra i componenti del gruppo.

### Iterazioni

La durata delle singole iterazioni è sempre stata decisa durante il meeting iniziale sulla base del lavoro da effettuare durante la settimana. A questo si deve la differenza di durata dei primi due sprint rispetto al terzo ed ultimo sprint.
Durante le iterazioni non è stato imposto alcun vincolo ai partecipanti del gruppo sul dove eseguire il lavoro e con quali orari, adottando un approccio smart ci si è maggiormente focalizzati sul raggiungimento del risultato nel miglior modo possibile piuttosto che su poca flessibilità per quel che riguarda gli orari.


### Commenti finali

Si è implementato il sistema sempre con l'ottica di sviluppi futuri. In particolar modo l'obiettivo è stato quello di realizzare una versione funzionante del sistema che fosse la migliore possibile restando all'interno del limite delle 60/70 ore a studente. Questo ha portato ad individuare funzionalità e prerogative diverse sulla base di cosa potesse essere o meno fatto. Un esempio è visibile dal non impiego della libreria **scala.rx** dal momento che tale libreria si è pensata perfetta in un contesto di interfaccia grafica in cui i vari grafici vengono aggiornati automaticamente ad ogni aggiunta di dati all'interno del database dei consumi.
Nell'ottica di una metodologia di sviluppo agile quindi si è deciso fin dall'inizio di adottare un insieme di metodologie. Scrum come approggio generale ma una classica metodologia agile per ciascun daily sprint.
