package service.playerServices

import models.Player
import ratpack.exec.Promise

interface PlayerRatingsService {

    Promise<Map<String, Double>> findHighestAverageRating(List<Player> players)

}
