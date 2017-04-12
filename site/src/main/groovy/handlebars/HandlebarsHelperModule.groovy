package handlebars

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import handlebars.helpers.PlayerIdIntoNameHelper
import ratpack.handlebars.NamedHelper

class HandlebarsHelperModule extends AbstractModule {

    protected void configure() {
        List<Class<? extends NamedHelper<?>>> customHelpers = [
                PlayerIdIntoNameHelper
        ]

        def namedHelperBinder = Multibinder.newSetBinder(binder(), NamedHelper)

        customHelpers.each { namedHelperBinder.addBinding().to(it) }
    }
}
