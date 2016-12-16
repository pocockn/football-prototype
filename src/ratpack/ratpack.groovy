import config.HikariConfigModule
import handlers.DashboardHandler
import handlers.FixturesHandler
import models.Player
import models.PlayerStatistics
import models.Team
import persistance.DataMigrationRatpackModule
import ratpack.groovy.sql.SqlModule
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.service.Service
import ratpack.service.StartEvent
import service.TeamPersistanceService.TeamStoreService
import service.TeamPersistanceService.TeamStoreServiceImpl
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
        bind PlayerStatistics
        bind Player
        bind Team
        // bind Fixtures
        bindInstance TeamContent, new TeamContentImpl()
        bindInstance TeamStoreService, new TeamStoreServiceImpl()
        bindInstance new Service() {
            void onStart(StartEvent e) throws Exception {
                Logger logger = Logger.getLogger("")
                logger.info("Initialising Football Prototype")
            }
        }
    }

    handlers {
        get {
            render groovyMarkupTemplate("index.gtpl", title: "My ratpack App")
        }

        path 'dashboard', new DashboardHandler()

        path "fixtures", new FixturesHandler()


        files { dir "public" }
    }
}

