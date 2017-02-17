package models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName(value = "player")
class Player {
    String id
    String name
    String teamName
    String teamId
    String bio
    @JsonProperty("data")
    ArrayList<Integer> seasonGoals
    ArrayList<Integer> ratings
    Integer assists
    Integer manOfTheMatches
    Integer cleanSheets
    Integer totalGoals
    String profileImageUrl

    void finishSeason() {
        this.seasonGoals.clear()
        this.ratings.clear()
        cleanSheets = 0
        manOfTheMatches = 0
        assists = 0
    }
}
