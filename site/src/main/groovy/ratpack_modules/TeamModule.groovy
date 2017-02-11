package ratpack_modules

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import models.Team
import service.persistance_service.TeamStoreService
import service.persistance_service.TeamStoreServiceImpl
import service.player_services.TeamContent
import service.player_services.TeamContentImpl

class TeamModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Team)
        bind(TeamStoreService).to(TeamStoreServiceImpl).in(Scopes.SINGLETON)
        bind(TeamContent).to(TeamContentImpl)
    }
}
