import React, {Component} from "react";
import axios from "axios";

class TeamSelectBox extends Component {

    constructor(props) {
        super(props);
        this.state = {
            teams: []
        };
    }


    componentDidMount() {
        axios.get("/api/teams")
            .then(response => {
                const teams = response.data;
                this.setState({teams});
                console.log(teams);
            });
    }

    render() {
        return (
            <div className="form-group">
                <label for="inputSelect" className="control-label">Select Team</label>
                <div className="bs-component">
                    <select value={this.props.state.teamId} onChange={this.props.onChange} className="form-control">
                        {this.state.teams.map(singleTeam =>
                            <option value={singleTeam.id}>{singleTeam.team.name} </option>
                        )}
                    </select>
                </div>
            </div>
        );
    }
}

export default TeamSelectBox;