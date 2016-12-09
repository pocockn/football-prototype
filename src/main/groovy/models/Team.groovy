package models

import com.fasterxml.jackson.annotation.JsonProperty

class Team {
    String id
    @JsonProperty("series")
    ArrayList<Player> players = []
}
