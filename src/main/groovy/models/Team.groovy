package models

import com.fasterxml.jackson.annotation.JsonProperty

class Team {
    @JsonProperty("series")
    ArrayList<Player> players = []
}
