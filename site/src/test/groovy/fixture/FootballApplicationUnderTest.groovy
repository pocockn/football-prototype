package fixture

import groovy.transform.CompileStatic
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.guice.Guice
import ratpack.impose.ImpositionsSpec
import ratpack.impose.UserRegistryImposition
import ratpack.remote.RemoteControl

@CompileStatic
class FootballApplicationUnderTest extends GroovyRatpackMainApplicationUnderTest {

    @Override
    protected void addImpositions(ImpositionsSpec impositionsSpec) {
        impositionsSpec.add(UserRegistryImposition.of(
                Guice.registry {
                    it.bindInstance RemoteControl.handlerDecorator()
                }
        ))
    }
}
