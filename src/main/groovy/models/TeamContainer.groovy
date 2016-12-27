package models

class TeamContainer {
    String id
    Team team = new Team()
    Fixtures fixtures = new Fixtures()
    PlayersContainer playersContainer = new PlayersContainer()
}
