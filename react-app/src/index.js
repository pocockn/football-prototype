import React from "react";
import ReactDOM from "react-dom";
import {Router, Route, browserHistory} from "react-router";
import AppFrame from "./components/AppFrame";
import "./index.css";
import "./assets/theme.css";

ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={AppFrame}/>
    </Router>,
    document.getElementById('root')
);