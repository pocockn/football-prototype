package service

import groovy.sql.Sql
import models.Player
import models.Team
import org.junit.Ignore
import spock.lang.Specification

class TeamStoreServiceImplSpec extends Specification {


    String dbHost = "127.0.0.1"
    String dbUser = "pocockn"
    String db = "footballprototype"
    String dbPassword = "only8deb"
    Integer port = 5432

    String jdbcUrl = "jdbc:postgresql://${getDbHost()}:${getPort()}/${getDb()}"

@Ignore
    void "Should save and return a team list"() {
        given:
        def sql = Sql.newInstance(jdbcUrl, dbUser, dbPassword)

        when:
        def result = sql.rows("SELECT * FROM site_content")

        then:
        result.contains("Nick Pasty")

    }

    private static Team addPlayersAndReturnTeam() {
        Team team = new Team()
        Player player1 = new Player(name: 'Nick', goals: [0, 2, 3, 5, 6])
        Player player2 = new Player(name: 'Pasty', goals: [0, 5, 7, 9, 10])
        team.players.add(player1)
        team.players.add(player2)
        team
    }
}



