import handlers.HighChartHandler
import models.Player
import models.Team
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule

import static ratpack.groovy.Groovy.ratpack
import static ratpack.groovy.Groovy.groovyMarkupTemplate


ratpack {
    bindings {
        module MarkupTemplateModule
        module HandlebarsModule
        bind HighChartHandler
        bind Player
        bind Team
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
