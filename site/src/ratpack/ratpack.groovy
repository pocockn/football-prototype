import api.PlayerGetHandlerApi
import api.TeamGetHandlerApi
import config.HikariConfigModule
import handlers.*
import models.Fixtures
import models.Player
import models.Team
import org.pac4j.http.client.indirect.IndirectBasicAuthClient
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator
import org.pac4j.oauth.client.FacebookClient
import org.pac4j.oauth.client.TwitterClient
import persistance.DataMigrationRatpackModule
import ratpack.groovy.sql.SqlModule
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.pac4j.RatpackPac4j
import ratpack.service.Service
import ratpack.service.StartEvent
import ratpack.session.SessionModule
import service.persistance_service.PlayerStoreService
import service.persistance_service.PlayerStoreServiceImpl
import service.persistance_service.TeamStoreService
import service.persistance_service.TeamTeamStoreServiceImpl
import service.player_services.FindPropertyStatistics
import service.player_services.FindPropetyStatisticsImpl
import service.player_services.TeamContent
import service.player_services.TeamContentImpl
import service.user_service.UserAccountService
import service.user_service.UserStorageService
import service.user_service.UserStorageServiceImplementation
import session_support.UserSession

import java.util.logging.Logger

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack
import static ratpack.handlebars.Template.handlebarsTemplate

ratpack {

    bindings {
        module SqlModule
        module HikariModule
        module HikariConfigModule
        module DataMigrationRatpackModule
        module HandlebarsModule
        module SessionModule
        bind DashboardHandler
        bind FixturesHandler
        bind TwitterLoginHandler
        bind FacebookLoginHandler
        bind AllPlayersHandler
        bind UserSession
        bind Player
        bind Team
        bind Fixtures
        bind UserAccountService
        bind PlayerGetHandlerApi
        bind UserStorageService, UserStorageServiceImplementation
        bindInstance TeamStoreService, new TeamTeamStoreServiceImpl()
        bindInstance FindPropertyStatistics, new FindPropetyStatisticsImpl()
        bindInstance TeamContent, new TeamContentImpl()
        bindInstance PlayerStoreService, new PlayerStoreServiceImpl()
        bindInstance new Service() {
            void onStart(StartEvent e) throws Exception {
                Logger logger = Logger.getLogger("")
                logger.info("Initialising Football Prototype")
            }
        }
    }

    handlers {

        prefix("api") {
            path "teams", new TeamGetHandlerApi()
            path "players", new PlayerGetHandlerApi()
        }

        get {
            redirect(302, 'dashboard')
        }

        get("admin") {
            render handlebarsTemplate("index.html")
        }

        path 'dashboard', new DashboardHandler()

        path "fixtures", new FixturesHandler()

        path "teams", new AllTeamsHandler()

        path "players", new AllPlayersHandler()

        path "players/:id", new SinglePlayerHandler()


        FacebookClient fbClient = new FacebookClient('259923037755292', '10cb6067fb2060ad1c5b8e4ba723a15d')

        fbClient.setFields("id,name,first_name,middle_name,last_name,email")

        all(RatpackPac4j.authenticator(new IndirectBasicAuthClient(new SimpleTestUsernamePasswordAuthenticator()), new TwitterClient("l4Aa0cBgVkNTaLMuYQuxPwASa", "evr77wmQnpWWh3FO1r9gZ8quC7BU1y3LXP7ndfCjZu5MG7rhNX"), fbClient))

        prefix("auth") {
            all({ ctx ->
                RatpackPac4j.userProfile(ctx).then { opUp ->
                    if (opUp.isPresent()) {
                        ctx.next(single(opUp.get()))
                    } else {
                        ctx.redirect(302, '/login')
                    }
                }
            })

            get {
                render groovyMarkupTemplate("index.gtpl", title: "My ratpack App")
            }
        }

        get "facebookLogin", new FacebookLoginHandler()

        path "twitterLogin", new TwitterLoginHandler()

        get("basicLogin") { ctx ->
            RatpackPac4j.login(ctx, IndirectBasicAuthClient).then {
                ctx.redirect(302, "/auth")
            }
        }

        get("login") {
            render handlebarsTemplate("loginForm.html")
        }

        get("logout") {
            RatpackPac4j.logout(context).then {
                context.response.expireCookie("name")
                redirect("/")
            }
        }

        files { dir "public" }
    }
}

