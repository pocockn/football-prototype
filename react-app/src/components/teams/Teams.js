import React, {Component} from "react";
import {postToApi} from "../../actions/postToApi";
import axios from "axios";
import {Link} from "react-router";
var AddTeamForm = require('./forms/AddTeamForm');

class Teams extends Component {

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
        postToApi("/api/players/add-player", obj);
    }

    render() {
        return (
            <div className="App">
                <div className="tray tray-center">
                    <div className="row">
                        <div className="col-md-8">
                            <AddTeamForm />
                            <div className="panel-heading">
                                <span className="panel-title">Teams</span>
                            </div>
                            <div className="panel-body p20 pb10">
                                <ul className="list-unstyled">
                                    {this.state.teams.map(singleTeam =>
                                        <Link to={"team/" + singleTeam.id} key={singleTeam.id}>
                                            <li>{singleTeam.team.name}</li>
                                        </Link>
                                    )}
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default Teams;