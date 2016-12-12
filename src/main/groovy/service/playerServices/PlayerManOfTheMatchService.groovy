package service.playerServices

import models.Player
import ratpack.exec.Promise

interface PlayerManOfTheMatchService {
    Promise<Map<String, Double>> findPlayerWithMostMotms(List<Player> players)
}
