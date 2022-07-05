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

## Design architetturale

### Architettura generale del sistema

Elemento centrale dell’architettura di Energy Management è l’utente (sia esso utente privato piuttosto che azienda).
Dall’utente partono due flussi operazionali differenti:

- Registrazione: con la registrazione l’utente memorizza le sue informazioni all’interno del database. Successivamente queste informazioni sono prese da un generatore di consumi che genera i consumi, come specificato nel requisito funzionale 3.5. Questi consumi generati vengono memorizzati all’interno del DB e saranno poi recuperati da un generatore di bollette relative che, partendo dai consumi recuperati dal database, costruisce le bollette in modo opportuno di modo che queste possano essere gestite dal componente predisposto alle operazioni. Tale componente recupera le bollette costruite e sulla base di queste esegue delle operazioni, queste operazioni saranno poi utilizzate per visualizzare i dati specifici richiesti dall’utente dopo il login.
- Login: con il login l’utente ottiene accesso al sistema ottenendo la possibilità di scegliere quali informazioni visualizzare, relative a quali consumi a quale città. Con il login l’utente ottiene la possibilità di operare ed interfacciarsi con le varie operazioni fornite dal sistema.

Operazioni centrali del sistema sono le seguenti:

- Costruzione delle bollette: questa operazione è cruciale dal momento che recupera in maniera continuativa dati dal database relativo ai consumi e costruisce le relative bollette. La costruzione di tali bollette è un’operazione complessa dal punto di visto di interfacciamento con il DB in quanto risulta fondamentale per il soddisfacimento del requisito non funzionale 4.2.
- Operazioni sulle bollette costruite: queste operazioni risultano cruciali per poter permettere all’utente di visualizzare i dati richiesti. Si tratta quindi di operazioni di computazione che espongono i risultati in modo che questi siano visibili all’utente.

Di seguito è riportata l'architettura generale del sistema:

![general-architecture](https://user-images.githubusercontent.com/91571686/177432101-7400a2cb-9801-4ca7-ae93-4e8740dd25a1.png)



- **Registrazione**: la registrazione riceve in input i dati dell’utente quando questo vuole registrarsi al sistema. Esegue una serie di controlli di validità sui dati forniti in input come specificato nei requisiti 3.1.22 e in caso di successo registra l’utente al sistema memorizzando i dati all’interno del database degli utenti.
- **Login**: Il login riceve in input lo UserID e la password ed esegue i controlli sul database degli utenti per verificare se lo specifico userID è presente. Nel caso in cui questo sia presente allora verifica la password e nel caso di corrispondenza consente all’utente di accedere al sistema.
- **Generatore consumi**: Il generatore dei consumi recupera i dati memorizzati all’interno del database degli utenti con l’obiettivo di generare, sulla base di questi dati recuperati, dei consumi random. E’ importante, con l’obiettivo di garantire consistenza e nell’ottica di rispettare il requisito non funzionale 4.2 che la richiesta al DB avvenga con una frequenza sufficientemente elevata. Il generatore, per ogni utente recuperato ad intervalli regolari genera dei consumi random che vengono successivamente memorizzati all’interno del database dei consumi.
- **Database utenti**: il database utenti memorizza le informazioni relative agli utenti che hanno effettuato la registrazione al sistema.
- **Database consumi**:  il database dei consumi memorizza i consumi generati dal generatore dei consumi relativi agli utenti che sono presenti all’interno del sistema.
- **Costruttore bollette**:  il costruttore delle bollette recupera i dati memorizzati all’interno del database dei consumi e li utilizza per generare le relative bollette. Le bollette hanno una propria struttura ed è opportuno che siano generate in modo conforme alla struttura stessa nell’ottica di poter essere utilizzate in modo ottimale dal componente predisposto alle operazioni. Con l’obiettivo di ridurre al minimo problemi di inconsistenza non generando bollette per consumi effettivamente presenti e nell’ottica di soddisfare quindi il requisito non funzionale 4.2 l’interazione tra il costruttore delle bollette e il database dei consumi avviene con una frequenza elevata.
- **Operazioni bollette**:  le operazioni vengono effettuate sulle bollette costruite a partire dai dati recuperati dal database dei consumi.
- **Dashboard**:  è il componente che consente all’utente di interagire con il sistema. Mediante questo componente l’utente riesce ad esprimere quali sono i dati che intende visualizzare e quali sono le previsioni con relativi parametri che intende eseguire. Questo componente sfrutta, quindi, le operazioni derivanti dal componente **Operazioni bollette**.

### Pattern

Energy Management è un’applicazione con un forte flusso informativo, in cui è possibile individuare diversi livelli. Per questa ragione il pattern architetturale scelto è il **Layered Pattern**.

![pattern](https://user-images.githubusercontent.com/91571686/177270038-de09205d-255f-4a59-a87a-87c008163eb7.png)


La scelta di questo pattern è stata dettata dalla possibilità fornita dallo stesso di poter posizionare le varie componenti precedentemente elencate in un livello specifico.

- **Data layer**: in questo livello troviamo il database degli utenti, il database dei consumi, il componente relativo alla registrazione,il componente relativo alla gestione degli errori della registrazione e del login, il componente relativo al login  e quello relativo al generatore dei consumi.
- **Business logic layer**: in questo livello troviamo il componente dedito alla costruzione delle bollette
- **Application logic layer**: in questo layer è posizionato il componente predisposto alle operazioni sulle bollette.
- **Presentation layer**:  in questo livello è posizionato il componente Dashboard ed il componente Main che consente l’interazione con l’utente per le specifiche operazioni.

## Design di dettaglio

Si presenta ora il design di dettaglio relativo alle varie componenti architetturali precedentemente presentate:

### Utente

L’utente è l’elemento centrale dell’applicazione. Infatti è l’utente che effettua le scelte relative alla tipologia di dati da visualizzare e a quali previsioni effettuare all’interno della Dashboard.
All’interno del sistema sono presenti due tipologie di utenti: l’utente privato e l’azienda. Tuttavia questa distinzione viene effettuata all’atto di registrazione. Al momento del login l’utente viene creato con i dati rilevanti recuperati dal DB in corrispondenza del proprio username e viene istanziato con un campo che riporta la tipologia di utente a cui ci si riferisce.  Quest’ultimo punto è estremamente importante per riportare i consumi relativi ad una certa località con riferimento agli utenti privati o alle aziende. E’ per questa ragione che is è scelto di rappresentare l’utente per mezzo di una **classe**.
Il seguente diagramma UML mostra e formalizza quanto detto:

![user](https://user-images.githubusercontent.com/91571686/177270890-02ef360d-27ba-4976-8185-9f78ef9cc408.png)

L’utente si trova nella Dashboard, in particolar modo, come spiegato successivamente, la Dashboard è istanziata ricevendo in ingresso un Utente.
Dal diagramma riportato è possibile notare come un utente venga istanziato tenendo conto del suo userID (univoco all’interno del sistema) la propria città, la propria regione e la tipologia di utente. In particolar modo la tipologia di utente risulta importante sia per effettuare le previsioni relative ad una certa località geografica (le previsioni sull’andamento dei consumi privati o aziendali relativi ad una certa città o regione) e sia per mostrare l’andamento dei consumi in una certa località geografica (andamento dei consumi privati o aziendali relativi ad una certa città o regione)

### Registrazione

La registrazione è la prima operazione che un utente esegue quando vuole interfacciarsi all’applicazione.
L’operazione di registrazione ha uno stretto legame con il database degli utenti già presentato nella fase di architettura, dal momento che è necessario verificare l’unicità dello UserID (che è il modo con cui un utente viene verificato all’interno del sistema).
Il componente registrazione esegue solo due operazioni:

- Ricava l'hash code relativo alla password ricevuta in input
- Invia i dati presentati in input con l’hash code della password al database.

In virtù del fatto che si tratta di un componente che esegue solo un’operazione verso l’esterno si è deciso di realizzarlo come **object**.
Il seguente diagramma UML mostra e formalizza quando detto:

![registration](https://user-images.githubusercontent.com/91571686/177271294-772b2d8a-2a6d-451e-9df5-cf3c873e635d.png)

Come mostrato da questo diagramma il metodo register riporta una stringa contenente il risultato dell’operazione di registrazione. Nel caso di errore riscontrato in violazione delle verifiche esplicitate nel requisito 3.1.2 la stringa riporta anche la motivazione dell’errore.
Dal momento che tale componente prevede l’interazione con il database degli utenti per la verifica dell’unicità dello userID di seguito è riportato il corrispondente diagramma delle attività:

![activity-diagram-registration](https://user-images.githubusercontent.com/91571686/177271444-3c6d6ae0-27d4-40bf-b95f-14a89a20ab8d.png)

### MD5

MD5 è una funzione di hashing che ha il compito di svolgere l’hash function della password e può essere richiamata da altri componenti, per questo è stato rappresentato come un object, di seguito il diagramma di flusso relativo all’hashing della password.

![MD5-activity-diagram](https://user-images.githubusercontent.com/91571686/177271609-1dced690-0868-4faa-a021-da55b283a975.png)

### Login

L’operazione di login consente all’utente di guadagnare accesso al sistema verificando prima che l’userID inserito dall’utente sia presente all’interno del database degli utenti e successivamente verificando che la password memorizzata per lo specifico userID sia effettivamente quella inserita dall’utente.
Allo scopo di fare questo la password recuperata dal database viene hashata e successivamente viene confrontata con l’hash code relativo alla password inserita dall’utente. 
Nel caso in cui le password combaciassero dal database degli utenti vengono recuperati tutti i dati utili per ritornare l’oggetto utente associato a quello userID.
Dal momento che tale componente deve eseguire un’unica operazione è stato scelto di utilizzare un **object** per incapsularne il comportamento.
Il seguente diagramma UML mostra e formalizza quanto detto:

![login](https://user-images.githubusercontent.com/91571686/177271754-b5037ea4-2902-4f62-9edd-070906f4bc74.png)

### Generatore consumi

Questo componente è pensato per essere un componente sempre attivo in grado di generare dei consumi con cadenza periodica. Per poter generare i consumi il generatore recupera la lista di utenti dal database degli utenti. Ad ogni aggiornamento di questa lista vengono generati i consumi per tutti gli utenti e vengono inseriti all’interno del database dei consumi. Il flusso di esecuzione di questo componente è il seguente:
- recupero degli utenti presenti all’interno del database degli utenti
- generazione dei consumi a partire dalla lista e invio dei consumi al database dei consumi. In particolar modo, si attende il completamente del recupero dei dati e poi si effettua la generazione.
- dopo il termine della generazione dei consumi si attende una definita quantità di tempo e si effettua la richiesta al database per il recupero degli utenti.

Risulta evidente da quanto detto che le operazioni eseguite da questo componente si eseguono ciclicamente.
La generazione avviene per ogni tipologia di consumo.

Ogni generazione, oltre ai dati tipici dei consumi quali consumo effettivo e costo ad esso associato, considera anche i dati recuperati dal database degli utenti come userID, città, regione.
Ulteriore elemento importante è assegnare ad ogni consumo generato un proprio id.
Questo, come successivamente descritto, è importante per la costruzione della lista di bollette realizzata con i dati recuperati dal database dei consumi.
Questo componente espone verso l’esterno una sola operazione, la generazione, la quale prevede contestualmente l’invio dei dati al database dei consumi. 
Per questa ragione si è deciso di realizzarlo come **object**.

![generator](https://user-images.githubusercontent.com/91571686/177272162-7b937a52-e2e0-49ea-895a-7328b7b8917d.png)

Aspetto importante per quanto riguarda la generazione dei consumi e che questi devono essere generati con un mese ed un anno definiti.

### Database

Per quanto riguarda il database (sia quello relativo agli utenti sia quello relativo ai consumi) la scelta è stata quella di utilizzare un Object che potesse effettivamente mediare tra la necessità di:
- recuperare i dati relativi agli utenti e aggiungere un nuovo utente
- recuperare i dati relativi ai consumi per poter costruire la lista delle bollette sulla quale poter eseguire le operazioni di ricerca.

### Costruttore bollette

Il costruttore delle bollette ritorna la lista delle bollette ricavata interrogando in un certo istante il database dei consumi. 
Il primo elemento da considerare riguarda la frequenza con cui questo componente interroga il database con l’obiettivo di costruire la lista.
Tuttavia, trattandosi di un costruttore di bollette è necessario considerare anche l’entità bolletta.
Questa entità si è deciso di modellarla come una **classe**, di modo da rendere più robusta e coerente la costruzione della lista da parte del costruttore.
Il seguente diagramma UML riporta e formalizza la classe relativa alle bollette.

![bill](https://user-images.githubusercontent.com/91571686/177272335-d0d4ca2b-09e3-40ac-9588-36dcfc148945.png)


Una volta definita l’entità bolletta è opportuno definire il costruttore delle bollette.
Come già riportato tale costruttore interroga periodicamente il database dei consumi, recupera i dati in esso contenuti e sulla base di questi dati costruisce le bollette.
Tutte le bollette vengono salvate, man mano che la costruzione procede, all’interno di una lista.
In virtù della periodicità con cui questo componente opera è da ritenersi un componente sempre attivo.
Questo passaggio è estremamente importante dal momento che il sistema prevede un binding molto forte tra questa lista ed il componente che si occupa di effettuare le operazioni sulle bollette.
Il flusso di attività che il costruttore delle bollette esegue è quindi il seguente:
- recupero dei dati contenuti all’interno del database dei consumi
- costruzione della lista delle bollette a partire dai dati recuperati

Un aspetto importante da rimarcare è che questo è un flusso di attività ciclico. In particolar modo il componente attende di aver recuperato i dati, costruisce le bollette e dopo la costruzione esegue un’altra interrogazione al database.
Dal momento che il costruttore di bollette esegue un’unica operazione si è deciso di modellarlo come un object.
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
- Operazioni di previsioni sulla base di determinate specifiche.
  - previsioni individuali per una specifica utenza relativamente ad uno specifico anno
  - previsioni per una specifica località relativamente ad uno specifico anno

Le operazioni definite da questo componente trovano applicazione in quelli che sono i metodi che ciascun utente può richiamare per avere una visualizzazione delle informazioni richieste.
Dal momento che questo è un metodo che espone semplicemente operazioni si è deciso di realizzarlo come **object**.

Il seguente diagramma UML mostra e formalizza quanto detto.

![billOperations](https://user-images.githubusercontent.com/91571686/177273269-9ecd1f68-6eb1-432c-a10b-c39aea7ae1c8.png)

### Dashboard

La Dashboard è il punto in cui il sistema viene utilizzato dall’utente. In particolar modo si occupa di ricevere gli input dall’utente che ha effettuato il login e di visualizzare i risultati delle richieste ottenute.
La Dashboard si è scelto di modellarla come un **case class** che prende in ingresso un utente ed espone come unico metodo quello che consente di visualizzare i dati sulla base delle operazioni richieste dall’utente.
All’interno del metodo esposto la dashboard consente l’interazione con l’utente sfruttando i metodi definiti all’interno della classe User.
Il seguente diagramma UML mostra e formalizza quanto detto:

![dashboard](https://user-images.githubusercontent.com/91571686/177273475-55a9dd4e-89ff-47f4-a303-40f32dc75710.png)

## Implementazione

### Organizzazione del codice

Il codice è stato organizzato in package. In particolar modo sono presenti 4 package principali, ognuno dei quali si mappa con quelli che sono i layer definiti nel pattern architetturale utilizzato. All'interno di ciascun package sono presenti altri package relativi ai componenti precedentemente definiti. Questi sotto-package corrispondono ai componenti presenti all'interno dei layer come definito precedentemente.

### Antonio Iannotta

### Andrea Catani

### Demetrio Andriani
Implementazione dei seguenti componenti:
- **object** Registration
- **object** Regions
- **object** ErrorCodeHandler
- **object** MD5
- **object** Main
- **class** RegistrationChecker
- **class** RegistrationTest
- **class** PasswordTest

#### Registration
Il componente Registration è stato implementato come un object in quanto, essendo un’implementazione del design pattern singleton, è stato considerato il tipo di dato astratto più adatto. Al suo interno si avranno i seguenti metodi:
- signUp(): questo metodo riceve in input i dati dello user nei relativi campi e, dopo averne controllato la validità con il RegistrationChecker,  permette di svolgere l’operazione di registrazione generando una connessione al database;
- passwordHash(): questo metodo svolge la funzione di hash della password che lo user vuole utilizzare.

#### RegistrationChecker
l componente RegistrationChecker è implementato come una classe. Questa classe viene usata per svolgere un controllo su tutti i campi della registrazione. E’ presente una mappa key/value (LinkedHashMap) utilizzata per associare ad ogni città la sua relativa regione.

I metodi che espone sono:
- checkFields(): ritorna una stringa che identifica tutti i possibili errori presenti nei campi di registrazione, caso contrario restituisce un messaggio di corretta validazione di tutti i campi;
- checkUserID(): ritorna una stringa che identifica tutti i possibili errori relativi al campo dello userID(blank, minore di 6 caratteri, superiore a 20 caratteri), caso contrario restituisce un messaggio di corretta validazione;
- checkPassword():  ritorna una stringa che identifica tutti i possibili errori relativi al campo della password (blank, minore di 6 caratteri, superiore a 20 caratteri), caso contrario restituisce il messaggio di validazione;
- checkUserType(): ritorna una stringa identifica se l’utente è private(0) oppure company(1) e restituisce il messaggio di validazione;
- checkCityRegionMatch(): ritorna una stringa che verifica il corretto collegamento tra la città e la sua relativa regione e restituisce il messaggio di validazione

#### ErrorCodeHandler
Il componente ErrorCodeHandler è stato implementato come un object, il cui compito è quello di  associare ad ogni possibile errore presente nel RegistrationChecker un ben definito messaggio, per rendere una più chiara individuazione dell’errore. Qualora non ci dovessero essere errori, l’ ErrorCodeHandler presenterà un messaggio di corretta validazione del campo.

#### Regions
Il componente Regions è stato implementato come un object,  il cui obbiettivo è quello di associare ad ogni città italiana la relativa regione.

#### MD5
Dal momento che Il componente MD5 deve eseguire un’unica operazione è stato scelto di utilizzare un **object** per incapsularne il comportamento. Il metodo esposto dal componente MD5 è:

- md5HashPassword(): questo metodo ha come valore in input una stringa, cioè la password, e restituisce come valore una stringa, vale a dire l’hash code della password.

#### Main
Il componente Main è stato implementato come un **object**. Al suo interno si avranno i seguenti metodi:
- callRegistration(): metodo che si occupa di dover chiamare la Registration,
- callLogin(): metodo che si occupa di dover chiamare il Login.



### Carlo Di Raddo

## Retrospettiva
