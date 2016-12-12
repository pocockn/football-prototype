package service.playerServices

import models.Player
import ratpack.exec.Promise

interface TeamContent {

    Promise<Map<String, Double>> findHighestAverageRating(List<Player> players)

    Promise<Map<String, Double>> findPlayerWithMostMotms(List<Player> players)

    Promise<Map<String, Double>> findMostCleanSheets(List<Player> players)

}
