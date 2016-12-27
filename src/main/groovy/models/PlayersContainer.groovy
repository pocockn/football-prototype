package models

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by pocockn on 27/12/16.
 */
class PlayersContainer {
    ArrayList<Player> players = []

    @JsonProperty("series")
    public void setPlayers(ArrayList<Player> players) {
        this.players = players
    }

    @JsonProperty("players")
    public ArrayList<Player> getPlayer() {
        return this.players
    }


}
