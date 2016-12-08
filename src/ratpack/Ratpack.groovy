import handlers.HighChartHandler
import models.Player
import models.Team
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule
import ratpack.service.Service
import ratpack.service.StartEvent

import java.util.logging.Logger

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        module MarkupTemplateModule
        module HandlebarsModule
        bind HighChartHandler
        bind Player
        bind Team
        bindInstance new Service() {
            void onStart(StartEvent e) throws Exception {
                Logger logger = Logger.getLogger("")
                logger.info("Initialising Football Prototype")
            }
        }
    }

    handlers {
        get {
            render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
        }

        prefix('api') {
            path 'highchart', new HighChartHandler()
        }

        files { dir "public" }
    }
}
