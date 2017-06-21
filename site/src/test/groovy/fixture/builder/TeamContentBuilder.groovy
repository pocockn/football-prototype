package fixture.builder

import models.TeamContainer
import ratpack.groovy.internal.ClosureUtil

import static groovy.lang.Closure.DELEGATE_FIRST

class TeamContentBuilder {

    @Delegate
    private final TeamContainer teamContainer

    TeamContentBuilder(String id) {
        teamContainer = new TeamContainer(
                id: id
        )
    }

    void team(@DelegatesTo(value = TeamDetailsBuilder, strategy = DELEGATE_FIRST) Closure teamDetails) {
        teamContainer.team = TeamDetailsBuilder.build(teamDetails)
    }

    void fixtures(@DelegatesTo(value = FixturesBuilder, strategy = DELEGATE_FIRST) Closure fixtures) {
        teamContainer.fixtures = FixturesBuilder.build(fixtures)
    }

    void players(@DelegatesTo(value = PlayersBuilder, strategy = DELEGATE_FIRST) Closure players) {
        teamContainer.playersContainer = PlayersBuilder.build(players)
    }

    static TeamContainer build(String id, @DelegatesTo(value = TeamContentBuilder, strategy = DELEGATE_FIRST)
            Closure... specifications) {
        TeamContentBuilder builder = new TeamContentBuilder(id)
        specifications.each { ClosureUtil.configureDelegateFirst(builder, it) }
        builder.teamContainer

    }

}
