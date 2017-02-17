import React, {Component} from "react";
import {postDataTest} from "../actions/postData";
import axios from "axios";

class Team extends Component {

    constructor(props) {
        super(props);
        // This binding is necessary to make `this` work in the callback
        this.handlePost = this.handlePost.bind(this);
        this.state = {
            teams: []
        };
    }

    componentDidMount() {
        axios.get("/api/teams")
            .then(res => {
                const teams = res.data;
                this.setState({teams});
                console.log(teams);
            });
    }

    handlePost(event) {
        var obj = {};
        obj.id = "0000-0000-0000-0001";
        obj.name = "Shire Soldiers";
        console.log(obj);
        event.preventDefault();
        console.log('The link was clicked.');
        postDataTest(obj);
    }

    render() {
        return (
            <div className="App">
                <h2>Teams</h2>
                <p>A list of teams currently on the system, pulled in via ajax from Ratpack</p>
                <ul>
                    {this.state.teams.map(singleTeam =>
                        <li key={singleTeam.id}>{singleTeam.team.name}</li>
                    )}
                    <button onClick={this.handlePost}>delete</button>
                </ul>

            </div>
        );
    }

}

export default Team;