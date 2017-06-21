package fixture.builder

import models.Team
import ratpack.groovy.internal.ClosureUtil

class TeamDetailsBuilder {

    @Delegate
    private final Team team

    TeamDetailsBuilder() {
        this.team = new Team()
    }

    static build(@DelegatesTo(value = TeamDetailsBuilder, strategy = Closure.DELEGATE_FIRST) Closure specification) {
        def teamDetails = ClosureUtil.configureDelegateFirstAndReturn(new TeamDetailsBuilder(), specification).team
        teamDetails
    }
}
