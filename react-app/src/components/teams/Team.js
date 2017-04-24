import React, {Component} from "react";
import axios from "axios";


class Team extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.findTeamById(this.props.params.id)
            .then(res => {
                const team = res.data;
                console.log(team);
            });
    }

    findTeamById(teamId) {
        return axios.get("/api/team/" + teamId)
    }


    handleSubmit(e) {
        e.preventDefault();
        // Get values via this.ref

    }

    render() {
        return (
            <div className="tray tray-center">
                <div className="row">
                    <div className="col-md-8">
                        <div className="panel mb25 mt5">
                            <div className="panel-heading">
                                <span className="panel-title">Team Single</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

module.exports = Team;