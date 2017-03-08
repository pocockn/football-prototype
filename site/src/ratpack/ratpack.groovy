import api.PlayerFetchUpdateHandler
import api.PlayerRemoveHandlerApi
import api.SinglePlayerApiHandler
import api.TeamGetHandlerApi
import config.HikariConfigModule
import groovy.json.JsonSlurper
import handlers.*
import league.ImportClient
import models.Fixtures
import models.LeagueTable
import models.Player
import org.pac4j.http.client.indirect.IndirectBasicAuthClient
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator
import org.pac4j.oauth.client.FacebookClient
import org.pac4j.oauth.client.TwitterClient
import persistance.DataMigrationRatpackModule
import ratpack.file.MimeTypes
import ratpack.groovy.sql.SqlModule
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.handlebars.HandlebarsModule
import ratpack.hikari.HikariModule
import ratpack.pac4j.RatpackPac4j
import ratpack.service.Service
import ratpack.service.StartEvent
import ratpack.session.SessionModule
import ratpack_modules.LoginModule
import ratpack_modules.TeamModule
import ratpack_modules.UserAccountModule
import service.LeagueTableHelpers
import service.persistance_service.PlayerStoreService
import service.persistance_service.PlayerStoreServiceImpl
import service.persistance_service.TeamStoreService
import service.persistance_service.TeamStoreServiceImpl
import service.player_services.FindPropertyStatistics
import service.player_services.FindPropetyStatisticsImpl
import service.user_service.UserAccountService
import service.user_service.UserStorageService
import service.user_service.UserStorageServiceImplementation

import java.util.logging.Logger

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack
import static ratpack.handlebars.Template.handlebarsTemplate

ratpack {

    bindings {
        module MarkupTemplateModule
        module SqlModule
        module HikariModule
        module HikariConfigModule
        module DataMigrationRatpackModule
        module HandlebarsModule
        module SessionModule
        module(UserAccountModule)
        module(LoginModule)
        module(TeamModule)
        bind DashboardHandler
        bind FixturesHandler
        bind AllPlayersHandler
        bind Player
        bind Fixtures

        bind ImportClient
        bind JsonSlurper
        bind LeagueTable
        bind LeagueTableHelpers

        bind UserAccountService
        bind PlayerFetchUpdateHandler
        bind UserStorageService, UserStorageServiceImplementation
        bindInstance TeamStoreService, new TeamStoreServiceImpl()
        bindInstance FindPropertyStatistics, new FindPropetyStatisticsImpl()
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
            path "player/:id", new SinglePlayerApiHandler()
            prefix("players") {
                path "addGetPlayers", new PlayerFetchUpdateHandler()
                path "removePlayer", new PlayerRemoveHandlerApi()
            }

        }

        get {
            redirect(302, 'dashboard')
        }

        path("test") {
            render handlebarsTemplate("index.html")
        }

        prefix("admin") {
            get('static/:type/:id') { context ->
                def path = "/static/${context.pathTokens['type']}/${context.pathTokens['id']}"
                InputStream resourceStream = getClass().getResourceAsStream(path)
                if (resourceStream) {
                    def contentType = context.get(MimeTypes).getContentType(path)
                    context.response.send(contentType, resourceStream.bytes)
                } else {
                    context.next()
                }
            }
            all {
                render handlebarsTemplate("index.html")
            }
        }

        path 'dashboard', new DashboardHandler()

        path "fixtures", new FixturesHandler()

        path "league", new LeagueTableHandler()

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

