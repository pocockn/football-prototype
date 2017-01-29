import React, {Component} from "react";
import axios from "axios";

class Players extends Component {

    render() {
        return(
            <div className="App">
                <h2>Players</h2>
                <p>A list of players currently on the system, pulled in via ajax from Ratpack</p>
            </div>
        );
    }

}

export default Players;