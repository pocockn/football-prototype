package persistance

import com.google.inject.AbstractModule

class DataMigrationRatpackModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(DataMigrationService)
    }
}
