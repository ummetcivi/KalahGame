# Kalah Game
Implementation of [Kalah](https://en.wikipedia.org/wiki/Kalah) game with Spring Boot.

REST Client
-------------------------
### Usage

1. First player creates a new game. Saves gameId and playerId.
2. Opponent joins the game with given gameId. Saves the playerId.
3. First player makes a move using play endpoint with putting the given playerId into header.
4. Second player makes a move, just like the first player.
5. Repeat 3,4 until the game ends.

### Resource
All endpoints returns following JSON resource:
  
    {
      "board": [6,6,6,6,6,6],
      "gameStatus": "TURN_A",
      "id": "string",
      "opponentBoard": [6,6,6,6,6,6],
      "opponentScore": 0,
      "playerId": "string",
      "score": 0
    }
    
    Possible statuses: TURN_A, TURN_B, WIN_A, WIN_B, WAITING_OPPONENT

### Create Game
This endpoint creates new game.

`POST /api/game`

### Get Game
This endpoint gets GameResource.

    GET /api/game
    Headers: Player-Id={playerId}
   
### Join Game
This endpoint joins the game if available.

`PUT /api/game/{gameId}/join`

### Play
This endpoint makes a move in given pit

    PUT /api/game/play?pit={pit}
    Headers: Player-Id={playerId}
 
 
Run Project
------
In order to run project, just run command below under root folder:

        ./mvnw spring-boot:run -Dserver.port=9080

Then you can access to the endpoints from `http://localhost:9080`

Swagger UI
----------
In order to access Swagger UI, just open `http://localhost:9080/swagger-ui.html` from your browser.
