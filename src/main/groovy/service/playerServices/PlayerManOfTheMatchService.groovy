package service.playerServices

import models.Player
import ratpack.exec.Promise

/**
 * Created by pocockn on 09/12/16.
 */
interface PlayerManOfTheMatchService {
    Promise<Map<String, Double>> findPlayerWithMostMotms(List<Player> players)
}
