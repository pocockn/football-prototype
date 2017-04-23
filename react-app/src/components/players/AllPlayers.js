import React, {Component} from "react";
import {postPlayerId} from "../../actions/postPlayerId";
import {Link} from "react-router";
import axios from "axios";

class AllPlayers extends Component {

    constructor(props) {
        super(props);
        // This binding is necessary to make `this` work in the callback
        this.handleDelete = this.handleDelete.bind(this);
        this.state = {
            players: []
        };
    }

    componentDidMount() {
        axios.get("/api/players/addGetPlayers")
            .then(res => {
                const players = res.data;
                this.setState({players});
                console.log(players);
            });
    }

    handleDelete(event) {
        console.log(event.target.id);
        event.preventDefault();
        postPlayerId(event.target.id);
        window.location.reload();
    }

    render() {
        return (
            <div class="tray tray-center">
                <div className="row">
                    <div className="col-md-8">
                        <div className="panel mb25 mt5">
                            <div className="panel-heading">
                                <span class="panel-title">All Players</span>
                            </div>
                            <div className="panel-body p20 pb10">
                                <ul>
                                    {this.state.players.map(singlePlayer =>
                                        <div><Link to={"edit-player/" + singlePlayer.id}>
                                            <li key={singlePlayer.id}>{singlePlayer.name}</li>
                                        </Link>
                                            <a id={singlePlayer.id} onClick={this.handleDelete.bind(this)}>delete</a>
                                        </div>
                                    )}
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
                <Link to="/new-player">
                    <button className="btn btn-primary">Add Player</button>
                </Link>
            </div>
        );
    }

}

export default AllPlayers;