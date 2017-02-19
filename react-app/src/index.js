import React from "react";
import ReactDOM from "react-dom";
import {Router, Route, hashHistory, IndexRoute} from "react-router";
import AppFrame from "./components/AppFrame";
import Team from "./components/Team";
import Fixtures from "./components/Fixtures";
import Players from "./components/Players";
import PlayersForm from "./components/PlayersForm";
import EditPlayerForm from "./components/EditPlayerForm";
import "./assets/theme.css";
global.jQuery = require('jquery');
global.Tether = require('tether');
require('bootstrap');


ReactDOM.render(
    <Router history={hashHistory}>
        <Route path="/" component={AppFrame}>
            <IndexRoute component={Team}/>
            <Route path="fixtures" component={Fixtures}/>
            <Route path="players" component={Players}/>
                <Route path="new-player" component={PlayersForm}/>
                <Route path="edit-player/:id" component={EditPlayerForm}/>
        </Route>
    </Router>,
    document.getElementById('root')
);