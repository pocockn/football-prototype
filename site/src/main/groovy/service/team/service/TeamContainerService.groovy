package service.team.service

import models.Team
import models.TeamContainer
import ratpack.handling.Context


class TeamContainerService {

    static Team extractTeamDetails(Context ctx) {
        Team team
        Optional<TeamContainer> teamOptional = ctx.maybeGet(TeamContainer.class)
        if (teamOptional.present) {
            team = teamOptional.get().team
        }
        team
    }
}
