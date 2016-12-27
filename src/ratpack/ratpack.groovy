import config.HikariConfigModule
import handlers.AllTeamsHandler
import handlers.DashboardHandler
import handlers.FixturesHandler
import handlers.TeamDetectionHandler
import models.Player
import models.Team
import persistance.DataMigrationRatpackModule
import ratpack.groovy.sql.SqlModule
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.service.Service
import ratpack.service.StartEvent
import service.PersistanceService.StoreService
import service.PersistanceService.TeamStoreServiceImpl
import service.playerServices.FindPropertyStatistics
import service.playerServices.FindPropetyStatisticsImpl
import service.playerServices.TeamContent
import service.playerServices.TeamContentImpl

import java.util.logging.Logger

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {

    bindings {
        module MarkupTemplateModule
        module SqlModule
        module HikariModule
        module HikariConfigModule
        module DataMigrationRatpackModule
        module HandlebarsModule
        bind DashboardHandler
        bind FixturesHandler
        bind Player
        bind Team
        bindInstance StoreService, new TeamStoreServiceImpl()
        // bind Fixtures
        bindInstance FindPropertyStatistics, new FindPropetyStatisticsImpl()
        // bind teamContent
        bindInstance TeamContent, new TeamContentImpl()
        bindInstance new Service() {
            void onStart(StartEvent e) throws Exception {
                Logger logger = Logger.getLogger("")
                logger.info("Initialising Football Prototype")
            }
        }
    }

    handlers {

        all new TeamDetectionHandler()

        get {
            render groovyMarkupTemplate("index.gtpl", title: "My ratpack App")
        }

        path 'dashboard', new DashboardHandler()

        path "fixtures", new FixturesHandler()

        path "teams", new AllTeamsHandler()

        files { dir "public" }
    }
}

