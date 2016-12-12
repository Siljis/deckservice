# deckservice
REST microservice for creating and shuffling decks of cards.

Usage:

1. Download Wildfly 10.1.0.Final: http://wildfly.org/downloads/

2. Download and install Maven:
    https://maven.apache.org/download.cgi
    https://maven.apache.org/install.html

3. Start Wildlfy container:
    wildfly-10.1.0.Final/bin/standalone.sh (or standalone.bat on Windows)

4. Under your clone of deckservice, run maven command to package and deploy project:
    mvn clean package wildfly:deploy

5. Make sure service is deployed successfully by typing http://localhost:8080/deckservice/ in your browser address bar. You should see a hello message.

6. Example use case:
    6.a Create new deck called "siljis": PUT http://localhost:8080/deckservice/api/decks/siljis/create
    6.b List all decks: GET http://localhost:8080/deckservice/api/decks/list
    6.c Show details of deck "siljis": GET http://localhost:8080/deckservice/api/decks/siljis/show
    6.d Shuffle deck "siljis": POST http://localhost:8080/deckservice/api/decks/siljis/shuffle
    6.e Show details of "siljis" after shuffle:  GET http://localhost:8080/deckservice/api/decks/siljis/show
    6.f Delete deck "siljis": DELETE http://localhost:8080/deckservice/api/decks/siljis/delete
    6.g List remaining decks: GET http://localhost:8080/deckservice/api/decks/list

7. Under your clone of deckservice, run maven command to undeploy project:
    mvn wildlfy:undeploy

8. Stop Wildfly container with Ctrl-C.




