package models

import com.fasterxml.jackson.annotation.JsonProperty

class PlayersContainer {
    ArrayList<Player> players = []

    @JsonProperty("series")
    public void setPlayers(ArrayList<Player> players) {
        this.players = players
    }

    @JsonProperty("players")
    public ArrayList<Player> getPlayers() {
        return this.players
    }


}
