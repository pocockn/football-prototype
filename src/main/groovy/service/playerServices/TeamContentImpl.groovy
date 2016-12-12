package service.playerServices

import models.Player
import ratpack.exec.Promise

class TeamContentImpl implements TeamContent {

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

    @Override
    Promise<Map<String, Integer>> findMostCleanSheets(List<Player> players) {
        Map<String, Integer> mostCleanSheets = [:]
        Integer CurrentHighestValue = 0
        returnPlayerWithMostCleanSheets(players, mostCleanSheets, CurrentHighestValue)
        return Promise.value(mostCleanSheets)
    }

    private
    static List<Player> returnPlayerWithMostCleanSheets(List<Player> players, Map<String, Integer> mostCleanSheets, Integer CurrentHighestValue) {
        players.each {
            if (it.cleanSheets > CurrentHighestValue) {
                mostCleanSheets.clear()
                mostCleanSheets.put(it.name, it.cleanSheets)
            }
        }

    }
}