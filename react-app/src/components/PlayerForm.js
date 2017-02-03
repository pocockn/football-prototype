var React = require('react');
var ReactDom = require('react-dom');
const uuid = require('uuid/v1');
import {postDataTest} from "../actions/postData";
var PlayerForm = React.createClass({

    fieldValues: {
        name: null,
        email: null,
        password: null,
        age: null,
        colors: []
    },

    render: function () {
        return (
            <ul className="form-fields">
                <li>
                    <label>Name</label>
                    <input type="text" ref="name" defaultValue={this.fieldValues.name}/>
                </li>
                <li>
                    <label>Team Name</label>
                    <input type="text" ref="teamName" defaultValue={this.fieldValues.teamName}/>
                </li>
                <li>
                    <label>Bio</label>
                    <input type="text" ref="bio" defaultValue={this.fieldValues.bio}/>
                </li>
                <li className="form-footer">
                    <button className="btn -primary pull-right" onClick={this.nextStep}>Save &amp; Continue</button>
                </li>
            </ul>
        )
    },
    nextStep: function (e) {
        e.preventDefault();
        // Get values via this.refs
        var player = {
            id: uuid(),
            name: ReactDom.findDOMNode(this.refs.name).value,
            teamName: ReactDom.findDOMNode(this.refs.teamName).value,
            bio: ReactDom.findDOMNode(this.refs.bio).value,
        };
        postDataTest(player);
    }


});

module.exports = PlayerForm;