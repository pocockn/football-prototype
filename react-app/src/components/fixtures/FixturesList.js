import React, {Component} from "react";
import {postToApi} from "../../actions/postToApi";
import {Link} from "react-router";
import axios from "axios";

class FixturesList extends Component {

    constructor(props) {
        super(props);
        // This binding is necessary to make `this` work in the callback
        this.handleDelete = this.handleDelete.bind(this);
        this.state = {
            fixtures: []
        };
    }

    componentDidMount() {
        axios.get("/api/fixtures/0000-0000-0000-0001/all")
        .then(res => {
            const fixtures = res.matches;
            this.setState({fixtures});
        });
        console.log(this.state.fixtures);
    }

    handleDelete(event) {
        console.log(event.target.id);
        event.preventDefault();
        postToApi("/api/players/removePlayer", event.target.id);
        window.location.reload();
    }

    render() {
        return (
            <div className="tray tray-center">
                <div className="row">
                    <div className="col-md-8">
                        <div className="panel mb25 mt5">
                            <div className="panel-heading">
                                <span className="panel-title">All Fixtures</span>
                            </div>
                            <div className="panel-body p20 pb10">
                                <ul>{this.state.fixtures.map(singleMatch =>
                                        <li>{singleMatch}</li>
                                    // <li>{moment(singleMatch.start).format('MM/DD/YYYY')}</li>
                                )}
                                </ul>
                            </div>

                        </div>
                    </div>
                </div>
                <Link to="/new-player">
                    <button className="btn btn-primary">Add Matches</button>
                </Link>
            </div>
        );
    }

}

export default FixturesList;