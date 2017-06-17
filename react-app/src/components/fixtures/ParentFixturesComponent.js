import React, {Component} from "react";
import SingleMatchForm from "./forms/SingleMatchFormInput";
import Fixtures from "./ChildFixturesContainer";
import moment from "moment";
require('react-datepicker/dist/react-datepicker.css');
const uuid = require('uuid/v1');
class ParentFixturesComponent extends Component {

    constructor() {
        super();
        this.state = {
            numChildren: 0,
            startDate: [],
        };
    }

    changeDate(i, newDate) {
        let startDate = this.state.startDate.slice();
        startDate[i] = newDate;
        this.setState({
            startDate
        });
    }

    onAddMatch() {
        this.setState({
            numChildren: this.state.numChildren + 1
        });
    }

    render() {
        const children = [];
        for (let i = 0; i < this.state.numChildren; i += 1) {
            children.push(
                <SingleMatchForm startDate={this.state.startDate[i] || moment()}
                                 key={uuid()}
                                 changeDate={this.changeDate.bind(this, i)}
            />)
        }

        return (
            <Fixtures addMatch={this.onAddMatch.bind(this)}>
                {children}
            </Fixtures>
        );
    }

}

export default ParentFixturesComponent;