import React, {Component} from "react";
import axios from "axios";

class Fixtures extends Component {

    render() {
        return(
            <div className="App">
                <h2>Fixtures</h2>
                <p>A list of fixtures currently on the system, pulled in via ajax from Ratpack</p>
            </div>
        );
    }

}

export default Fixtures;