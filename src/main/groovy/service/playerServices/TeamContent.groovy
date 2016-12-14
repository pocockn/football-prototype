package service.playerServices

import models.Player
import ratpack.exec.Promise

interface TeamContent {

    Promise<Map<String, Double>> findHighestAverageRating(List<Player> players)

    Promise<Map<String, ?>> findObjectwithLargestSpecificProperty(List<?> objects, String key)

}
