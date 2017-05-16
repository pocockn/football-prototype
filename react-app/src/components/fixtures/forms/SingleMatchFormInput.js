import React, {Component} from "react";
import DatePicker from "react-datepicker";

require('react-datepicker/dist/react-datepicker.css');

class SingleMatchForm extends Component {

    handleChange(params) {
        this.props.changeDate(params);
    }

    render() {
        return (
            <div className="row">
                <div key={this.props.id} className="form-group">
                    <label className="control-label col-md-2">New Match</label>
                    <div className="col-md-6">
                        <DatePicker
                            selected={this.props.startDate}
                            onChange={this.handleChange.bind(this)}/>
                        <div className="section-divider mb40" id="spy1"></div>
                    </div>
                </div>
            </div>
        );
    }

}

export default SingleMatchForm;