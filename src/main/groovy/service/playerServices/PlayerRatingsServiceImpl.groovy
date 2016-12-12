package service.playerServices

import models.Player
import ratpack.exec.Promise

class PlayerRatingsServiceImpl implements PlayerRatingsService {

    @Override
    Promise<Map<String, Double>> findHighestAverageRating(List<Player> players) {
        Map<String, Double> playerWithHighestAverageRating = [:]
        Integer highestRating = 0
        returnPlayerWithHighestAverageRating(players, highestRating, playerWithHighestAverageRating)
        return Promise.value(playerWithHighestAverageRating)
    }

    private
    static List<Player> returnPlayerWithHighestAverageRating(List<Player> players, int highestRated, playerWithHighestAverageRating) {
        players.each { player ->
            Integer rating = player.ratings.sum() as Integer
            if (rating > highestRated) {
                highestRated = rating
                Double averageRating = highestRated / player.ratings.size()
                playerWithHighestAverageRating.clear()
                playerWithHighestAverageRating.put(player.name, averageRating)
            }
        }
    }
}
