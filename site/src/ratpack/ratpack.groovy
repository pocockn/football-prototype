import api.PlayerCreateHandler
import api.fixtures.SingleTeamFixturesHandlerApi
import config.HikariConfigModule
import groovy.json.JsonSlurper
import handlebars.HandlebarsHelperModule
import handlers.AllPlayersHandler
import handlers.DashboardHandler
import handlers.FixturesHandler
import handlers.team_handlers.TeamPlayersHandler
import handlers.team_handlers.TeamSinglePlayerHandler
import league.ImportClient
import models.Fixtures
import models.LeagueTable
import models.Player
import persistance.DataMigrationRatpackModule
import ratpack.groovy.sql.SqlModule
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.service.Service
import ratpack.service.StartEvent
import ratpack.session.SessionModule
import ratpack_modules.LoginModule
import ratpack_modules.TeamModule
import ratpack_modules.UserAccountModule
import service.LeagueTableHelpers
import service.persistance_service.PlayerStoreService
import service.persistance_service.PlayerStoreServiceImpl
import service.persistance_service.TeamStoreService
import service.persistance_service.TeamStoreServiceImpl
import service.player_services.FindPropertyStatistics
import service.player_services.FindPropetyStatisticsImpl
import service.team.service.TeamContainerService
import service.user_service.UserAccountService
import service.user_service.UserStorageService
import service.user_service.UserStorageServiceImplementation

import java.util.logging.Logger

import static ratpack.groovy.Groovy.ratpack

//TODO speed up the time it takes to load the application
ratpack {

    bindings {
        module MarkupTemplateModule
        module HandlebarsHelperModule
        module SqlModule
        module HikariModule
        module HikariConfigModule
        module DataMigrationRatpackModule
        module HandlebarsModule
        module SessionModule
        module(UserAccountModule)
        module(LoginModule)
        module(TeamModule)
        bind DashboardHandler
        bind FixturesHandler
        bind TeamContainerService
        bind AllPlayersHandler
        bind TeamPlayersHandler
        bind TeamSinglePlayerHandler
        bind SingleTeamFixturesHandlerApi
        bind Player
        bind Fixtures

        bind ImportClient
        bind JsonSlurper
        bind LeagueTable
        bind LeagueTableHelpers

        bind UserAccountService
        bind PlayerCreateHandler
        bind UserStorageService, UserStorageServiceImplementation
        bindInstance TeamStoreService, new TeamStoreServiceImpl()
        bindInstance FindPropertyStatistics, new FindPropetyStatisticsImpl()
        bindInstance PlayerStoreService, new PlayerStoreServiceImpl()
        bindInstance new Service() {
            void onStart(StartEvent e) throws Exception {
                Logger logger = Logger.getLogger("")
                logger.info("Initialising 5-a-Side Stats")
            }
        }
    }

    handlers {
        insert new MainRatpackHandlers()
    }
}

