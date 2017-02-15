package changesets

import groovy.sql.Sql

class RemoveSiteContent extends CustomChangeSupport {
    @Override
    void execute(Sql sql) {
        sql.execute("""delete from site_content""")
    }
}
