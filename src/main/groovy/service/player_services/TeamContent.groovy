package service.player_services

import models.Player
import ratpack.exec.Promise

interface TeamContent {
    Promise<Map<String, Double>> findHighestAverageRating(List<Player> players)

    Promise<Double> findAverageRating(List<Player> players)

    Integer findGoalTotal(List<Integer> goals)
}

