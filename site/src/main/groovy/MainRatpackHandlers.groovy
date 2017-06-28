import api.*
import api.fixtures.SingleTeamFixturesHandlerApi
import handlers.*
import handlers.team_handlers.TeamFixturesHandler
import handlers.team_handlers.TeamPlayersHandler
import handlers.team_handlers.TeamSinglePlayerHandler
import org.pac4j.http.client.indirect.IndirectBasicAuthClient
import org.pac4j.http.credentials.authenticator.test.SimpleTestUsernamePasswordAuthenticator
import org.pac4j.oauth.client.FacebookClient
import org.pac4j.oauth.client.TwitterClient
import ratpack.groovy.handling.GroovyChainAction
import ratpack.pac4j.RatpackPac4j

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.handlebars.Template.handlebarsTemplate

class MainRatpackHandlers extends GroovyChainAction {

    @Override
    void execute() throws Exception {
        prefix("api") {
            path "addTeam", new AddTeamHandlerApi()
            path "teams", new TeamGetHandlerApi()
            path "team/:id", new SingleTeamApiHandler()
            prefix("player") {
                prefix(":id") {
                    new SinglePlayerApiHandler()
                }
            }
            prefix("fixtures/:id") {
                path "all", new SingleTeamFixturesHandlerApi()
                path "save-fixtures", new UpdateFixtureApiHandler()
            }
            prefix("players") {
                path "all", new AllPlayersHandlerApi()
                path "add-player", new PlayerCreateHandler()
                path "removePlayer", new PlayerRemoveHandlerApi()
            }

        }

        // react admin area
        path("admin") {
            render handlebarsTemplate("index.html")
        }

        // main overall dashboard
        get {
            redirect(302, 'dashboard')
        }

        path 'dashboard', new DashboardHandler()

        path "teams", new AllTeamsHandler()

        path "players", new AllPlayersHandler()

        path "players/:id", new SinglePlayerHandler()

        // specific team dashboard
        prefix("team/:teamId") {
            all new TeamDetectionHandler()

            path 'dashboard', new DashboardHandler()

            path "players", new TeamPlayersHandler()

            path "players/:id", new TeamSinglePlayerHandler()

            path "fixtures", new TeamFixturesHandler()

            path("league") {
                String teamId = allPathTokens.teamId
                context.render("league ${teamId}")
            }

        }

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
