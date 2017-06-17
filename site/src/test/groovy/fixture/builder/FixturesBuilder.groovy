package fixture.builder

import models.Fixtures
import models.Match
import ratpack.groovy.internal.ClosureUtil

class FixturesBuilder {

    @Delegate
    private final Fixtures fixtures = new Fixtures()

    void match(String id, String title, String start, String end) {
        fixtures.matches << new Match(id: id, title: title, start: start, end: end)
    }

    static build(@DelegatesTo(value = FixturesBuilder, strategy = Closure.DELEGATE_FIRST) Closure specification) {
        def fixtures = ClosureUtil.configureDelegateFirstAndReturn(new FixturesBuilder(), specification).fixtures
        fixtures
    }
}
