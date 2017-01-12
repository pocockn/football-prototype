package models

import com.fasterxml.jackson.annotation.JsonProperty

class Player {
    String id
    String name
    String teamName
    @JsonProperty("data")
    ArrayList<Integer> goals
    ArrayList<Integer> ratings
    Integer assists
    Integer manOfTheMatches
    Integer cleanSheets
    Integer totalGoals

}
