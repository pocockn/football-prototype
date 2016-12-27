package models

import com.fasterxml.jackson.annotation.JsonProperty

class Player {
    String id
    String name
    @JsonProperty("data")
    ArrayList<Integer> goals
    ArrayList<Integer> ratings
    Integer assists
    Integer manOfTheMatches
    Integer cleanSheets
}
