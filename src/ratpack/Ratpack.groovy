import handlers.HighChartHandler
import models.TestDataJson
import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        module MarkupTemplateModule
        bind HighChartHandler
        bind TestDataJson
    }

    handlers {
        get {
            render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
        }

        prefix('api') {
            path'highchart', new HighChartHandler()
        }

        files { dir "public" }
    }
}
