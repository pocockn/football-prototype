import React, {Component} from "react";
import {postToApi} from "../../actions/postToApi";

class Fixtures extends Component {

    onSubmit(e) {
        e.preventDefault();
        var fixtures = {
            matches: []
        };
        this.props.children.forEach(function (element) {
            var match = {
                id: element.key,
                title: element.key,
                start: element.props.startDate._d,
                end: element.props.startDate._d
            };
            fixtures.matches.push(match);
        });
        postToApi("/api/fixtures/00001/save-fixtures", fixtures);
    }

    render() {
        return (
            <div className="tray tray-center">
                <div className="row">
                    <div className="col-md-8">
                        <div className="panel mb25 mt5">
                            <div className="panel-heading">
                                <span className="panel-title">Fixtures</span>
                            </div>
                            <div className="panel-body p20 pb10">
                                <div id="fixture-parent">
                                    {this.props.children}
                                </div>
                            </div>
                            <div className="section-divider mb40" id="spy1"></div>
                            <button className="btn btn-primary tm-tag" onClick={this.props.addMatch}>Add Match</button>
                            <button className="btn btn-alert tm-tag" onClick={this.onSubmit.bind(this)}>Save</button>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}

export default Fixtures;