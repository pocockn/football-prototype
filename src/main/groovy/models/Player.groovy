package models

import com.fasterxml.jackson.annotation.JsonProperty

class Player {
    String name
    @JsonProperty("data")
    ArrayList<Integer> goals
    ArrayList<Integer> ratings
}
