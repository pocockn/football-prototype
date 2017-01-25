import React, {Component} from "react";
import axios from "axios";

class Team extends Component {

    constructor(props) {
        super(props);

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

    render() {
        return (
            <div className="App">
                <h2>Teams</h2>
                <p>A list of teams currently on the system, pulled in via ajax from Ratpack</p>
                <ul>
                    {this.state.teams.map(singleTeam =>
                        <li key={singleTeam.id}>{singleTeam.team.name}</li>
                    )}
                    <button>delete</button>
                </ul>

            </div>
        );
    }

}

export default Team;