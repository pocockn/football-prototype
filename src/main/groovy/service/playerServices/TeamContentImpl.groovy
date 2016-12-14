package service.playerServices

import models.Player
import ratpack.exec.Promise
import ratpack.util.MultiValueMap

class TeamContentImpl implements TeamContent {

    List<String> propertyNames

    TeamContentImpl(List<String> propertyValues) {
        propertyNames = propertyValues
    }

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
    Promise<Map<String, Map<String, ?>>> findObjectwithLargestSpecificProperty(List<?> objects, String key) {
        Integer currentHighestValue = 0
        Map<String, Map<String, ?>> highestKeyValuePair = [:]
        Map<String, String> innerMap = new HashMap<String, String>();
        propertyNames.each { singlePropertyName ->
            returnObjectWithLargestSpecificPropertyValue(objects, currentHighestValue, key, singlePropertyName).each { keySet, value ->
                innerMap.put(keySet.toString(), value.toString())
                highestKeyValuePair.put(singlePropertyName, innerMap)
            }
        }

        return Promise.value(highestKeyValuePair)
    }


    private
    static Map<?, ?> returnObjectWithLargestSpecificPropertyValue(List<?> objects, Integer currentHighestValue, String key, String singlePropertyName) {
        Map currentKeyValue = new HashMap()
        objects.each {
            if (it."${singlePropertyName}" > currentHighestValue) {
                currentKeyValue.clear()
                currentKeyValue.put(it."${key}", it."${singlePropertyName}")
            }
        }
        currentKeyValue
    }
}