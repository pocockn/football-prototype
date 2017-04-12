package handlebars.helpers

import com.github.jknack.handlebars.Options
import ratpack.handlebars.NamedHelper
import service.persistance_service.PlayerStoreServiceImpl

import javax.inject.Inject

class PlayerIdIntoNameHelper implements NamedHelper<String> {

    final String name = "idToName"

    @Inject
    private PlayerStoreServiceImpl playerStoreService

    @Inject
    PlayerIdIntoNameHelper(PlayerStoreServiceImpl playerStoreService) {
        this.playerStoreService = playerStoreService
    }

    @Override
    CharSequence apply(String id, Options options) throws IOException {
        def playerName
        playerStoreService.fetchById(id).then { player ->
            playerName = player.name
        }
        playerName
    }
}
