package service.playerServices

import models.Player
import ratpack.exec.Promise

class PlayerManOfTheMatchServiceImpl implements PlayerManOfTheMatchService {

    @Override
    Promise<Map<String, Double>> findPlayerWithMostMotms(List<Player> players) {
        Map<String, Integer> mostManOfTheMatches = [:]
        Integer currentlyMostManOfTheMatches = 0
        returnPlayerWithHighestAverageRating(players, mostManOfTheMatches, currentlyMostManOfTheMatches)
        return Promise.value(mostManOfTheMatches)
    }

    private
    static List<Player> returnPlayerWithHighestAverageRating(List<Player> players, Map<String, Integer> mostManOfTheMatches, Integer currentlyMostManOfTheMatches) {
        players.each {
            if (it.manOfTheMatches > currentlyMostManOfTheMatches) {
                mostManOfTheMatches.clear()
                mostManOfTheMatches.put(it.name, it.manOfTheMatches)
            }
        }

    }
}
