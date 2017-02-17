import React, {Component} from "react";
import AllPlayers from "./AllPlayers";
var PlayerForm = require('./PlayerForm');


class Players extends Component {

    render() {
        return (
            <div className="App">
                <AllPlayers />
            </div>
        );
    }

}

export default Players;