package fixture

import fixture.builder.TeamContentBuilder
import models.TeamContainer

class TeamBootstrapper {

    static TeamContainer teamContainer(
            @DelegatesTo(value = TeamContentBuilder, strategy = Closure.DELEGATE_FIRST) Closure... specifications) {
        def teamContainer = TeamContentBuilder.build('123', specifications)
        teamContainer
    }
}
