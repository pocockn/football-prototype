import config.HikariConfigModule
import handlers.HighChartHandler
import models.Player
import models.Team
import persistance.DataMigrationRatpackModule
import persistance.DataMigrationService
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.service.Service
import ratpack.service.StartEvent
import ratpack.groovy.sql.SqlModule
import service.TeamPersistanceService.TeamStoreService
import service.TeamPersistanceService.TeamStoreServiceImpl

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
        bind HighChartHandler
        bind Player
        bind Team
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

        prefix('api') {
            path 'highchart', new HighChartHandler()
        }

        files { dir "public" }
    }
}
