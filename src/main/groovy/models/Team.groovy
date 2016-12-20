package models

import com.fasterxml.jackson.annotation.JsonProperty

class Team {
    String id
    String name
    @JsonProperty("series")
    ArrayList<Player> players = []
    Fixtures fixtures = new Fixtures()
}
