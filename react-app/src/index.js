import React from "react";
import ReactDOM from "react-dom";
import App from "./App";
import { Router, Route, hashHistory } from 'react-router'
import Sidebar from "./components/Sidebar.js";
import { browserHistory } from 'react-router'
import "./index.css";

ReactDOM.render(
    <Router history={browserHistory}>
        <Route path="/" component={App}/>
    </Router>,
    document.body
);
