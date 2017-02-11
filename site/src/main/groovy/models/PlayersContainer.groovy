package models

import com.fasterxml.jackson.annotation.JsonProperty

class PlayersContainer {
    ArrayList<Player> players = []

    @JsonProperty("players")
    public void setPlayers(ArrayList<Player> players) {
        this.players = players
    }

    @JsonProperty("series")
    public ArrayList<Player> getPlayers() {
        return this.players
    }


}
