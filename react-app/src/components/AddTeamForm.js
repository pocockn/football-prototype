const uuid = require('uuid/v1');
import {addTeam} from "../actions/addTeam";
import React, {Component} from "react";

class AddTeamForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(name, event) {
        this.setState({[name]: event.target.value}, function () {
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        // Get values via this.refs
        let name = this.refs.name;
        let team = {
            id: uuid(),
            name: this.state.name,
        };
        name.value = "";
        addTeam(team);
    }

    render() {
        return (
            <div className="panel mb25 mt5">
                <div className="panel-heading">
                    <span class="panel-title">Add New Team</span>
                </div>
                <div className="panel-body p20 pb10">
                    <div className="form-horizontal">
                        <div className="form-group">
                            <label className="control-label">Name</label>
                            <input type="text" className="form-control" ref="name"
                                   defaultValue={this.state.name}
                                   onChange={this.handleChange.bind(this, 'name')}/>
                        </div>
                        <div className="form-group">
                            <div className="bs-component">
                                <button className="btn btn-md btn-default"
                                        onClick={this.handleSubmit}>Add Team
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }
}

module.exports = AddTeamForm;