import React from "react";
import ReactDOM from "react-dom";
import {Router, Route, browserHistory,IndexRoute} from "react-router";
import AppFrame from "./components/AppFrame";
import Team from "./components/Team"
import Fixtures from "./components/Fixtures";
import Players from "./components/Players";
import "./index.css";
import "./assets/theme.css";

ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={AppFrame}>
            <IndexRoute component={Team}/>
            <Route path="fixtures" component={Fixtures} />
            <Route path="players" component={Players} />
        </Route>
    </Router>,
    document.getElementById('root')
);