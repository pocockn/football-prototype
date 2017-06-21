package fixture.builder

import models.Player
import ratpack.groovy.internal.ClosureUtil

class PlayerBuilder {

    @Delegate
    Player player = new Player()

    static build(@DelegatesTo(value = PlayerBuilder, strategy = Closure.DELEGATE_FIRST) Closure specification) {
        def player = ClosureUtil.configureDelegateFirstAndReturn(new PlayerBuilder(), specification).player
        player
    }
}
