const uuid = require('uuid/v1');
import {postDataTest} from "../actions/postData";
import TeamSelectBox from "./TeamSelectBox";
import React, {Component} from "react";

class PlayerForm extends Component {

    constructor(props) {
            super(props);
            this.state = {
                name: '',
                teamName: '',
                bio: '',
                teamId: ''
            };
            this.handleChange = this.handleChange.bind(this);
            this.handleSubmit = this.handleSubmit.bind(this);
        }

    handleChange(name, event) {
        this.setState({[name]: event.target.value}, function(){
            console.log(this.state);
        }.bind(this));
    }

    handleSubmit(e) {
        e.preventDefault();
        console.log(this.state.name);
        // Get values via this.refs
        var player = {
            id: uuid(),
            name: this.state.name,
            teamName: this.state.teamName,
            bio: this.state.bio,
            teamId: this.state.teamId
         };

         postDataTest(player);
    }

    render() {
        return (
        <div className="row">
            <div className="col-md-6">
                <div className="panel">
                    <div className="panel-heading">
                        <h1>Add Player</h1>
                    </div>
                    <div className="panel-body">
                        <form className="form-horizontal">
                            <div className="form-group">
                                <label className="control-label">Name</label>
                                <input type="text" className="form-control" ref="name" defaultValue={this.state.name} onChange={this.handleChange.bind(this, 'name')}/>
                            </div>
                            <div className="form-group">
                                <label className="control-label">Team Name</label>
                                <input type="text" className="form-control" ref="teamName" defaultValue={this.state.teamName} onChange={this.handleChange.bind(this, 'teamName')}/>
                            </div>
                            <TeamSelectBox state={this.state.teamId} onChange={this.handleChange.bind(this, 'teamId')}/>
                            <div className="form-group">
                                <label className="control-label">Bio</label>
                                <input type="textarea" className="form-control" ref="bio" defaultValue={this.state.bio} onChange={this.handleChange.bind(this, 'bio')}/>
                            </div>
                            <div className="bs-component">
                                 <button className="btn btn-md btn-default btn-block" onClick={this.handleSubmit}>Save &amp; Continue</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        )
    }
}

module.exports = PlayerForm;