import React, {Component} from "react";
var PlayerForm = require('./PlayerForm');

class Players extends Component {

    render() {
        return(
            <div className="App">
                <h2>Players</h2>
                <p>A list of players currently on the system, pulled in via ajax from Ratpack</p>
                <PlayerForm />
            </div>
        );
    }

}

export default Players;