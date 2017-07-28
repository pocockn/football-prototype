import React from "react";
import ReactDOM from "react-dom";
import {browserHistory, IndexRoute, Route, Router} from "react-router";
import AppFrame from "./components/AppFrame";
import Teams from "./components/teams/Teams";
import Team from "./components/teams/Team";
import ParentFixturesComponent from "./components/fixtures/ParentFixturesComponent";
import Players from "./components/players/Players";
import PlayersForm from "./components/players/forms/PlayersForm";
import EditPlayerForm from "./components/players/forms/EditPlayerForm";
import FixturesList from "./components/fixtures/FixturesList";
import "./assets/theme.css";

global.jQuery = require('jquery');
global.Tether = require('tether');
require('bootstrap');


ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={AppFrame}>
            <IndexRoute component={Teams}/>
            <Route path="team/:teamId" component={Team}/>

            <Route path="fixtures" component={FixturesList}/>
            <Route path="addFixtures" component={ParentFixturesComponent}/>

            <Route path="players" component={Players}/>
            <Route path="new-player" component={PlayersForm}/>
            <Route path="edit-player/:id" component={EditPlayerForm}/>
        </Route>
    </ Router >,
    document.getElementById('root')
);