package fixture.builder

import models.Player
import models.PlayersContainer
import ratpack.groovy.internal.ClosureUtil

import static groovy.lang.Closure.DELEGATE_FIRST

class PlayersBuilder {

    @Delegate
    private final PlayersContainer playersContainer = new PlayersContainer()

    void player(@DelegatesTo(value = PlayerBuilder, strategy = DELEGATE_FIRST) Closure playerSpecification) {
        Player player = PlayerBuilder.build(playerSpecification)
        playersContainer.players << player
    }

    static build(@DelegatesTo(value = PlayersBuilder, strategy = DELEGATE_FIRST) Closure specification) {
        def playersContainer = ClosureUtil.configureDelegateFirstAndReturn(new PlayersBuilder(), specification).playersContainer
        playersContainer
    }
}
