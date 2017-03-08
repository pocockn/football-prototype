import {postDataTest} from "../actions/postData";
import TeamSelectBox from "./TeamSelectBox";
import React, {Component} from "react";
import Dropzone from "react-dropzone";
import request from "superagent";
import axios from "axios";

const CLOUDINARY_UPLOAD_PRESET = 'profile_images';
const CLOUDINARY_UPLOAD_URL = 'https://api.cloudinary.com/v1_1/fiveaside-stats/upload';


class EditPlayerForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: '',
            name: '',
            teamName: '',
            bio: '',
            teamId: '',
            rating: '',
            ratings: [],
            seasonGoals: [],
            totalGoals: '',
            manOfTheMatches: '',
            cleanSheets: '',
            assists: '',
            uploadedFileCloudinaryUrl: ''
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    componentDidMount() {
        this.findPlayerById(this.props.params.id)
            .then(res => {
                const player = res.data;
                this.setState({
                    id: player.id,
                    name: player.name,
                    teamName: player.teamName,
                    bio: player.bio,
                    teamId: player.teamId,
                    seasonGoals: player.data,
                    totalGoals: player.totalGoals,
                    ratings: player.ratings,
                    manOfTheMatches: player.manOfTheMatches,
                    cleanSheets: player.cleanSheets,
                    assists: player.assists,
                    uploadedFileCloudinaryUrl: player.profileImageUrl
                });
            });
    }

    findPlayerById(playerId) {
        return axios.get("/api/player/" + playerId)
    }

    onImageDrop(files) {
        this.setState({
            uploadedFile: files[0]
        });

        this.handleImageUpload(files[0])
    }

    handleImageUpload(file) {
        let upload = request.post(CLOUDINARY_UPLOAD_URL)
            .field('upload_preset', CLOUDINARY_UPLOAD_PRESET)
            .field('file', file);

        upload.end((err, response) => {
            if (err) {
                console.log(err);
            }

            if (response.body.secure_url !== '') {
                this.setState({
                    uploadedFileCloudinaryUrl: response.body.secure_url
                });
                console.log(this.state.uploadedFileCloudinaryUrl);
            }
        });
    }

    handleChange(name, event) {
        this.setState({[name]: event.target.value}, function () {
            console.log(this.state);
        }.bind(this));
    }

    addMatchGoalsToTotalSeasonGoals(matchGoals) {
        if (matchGoals > this.state.seasonGoals) {
            if (this.state.seasonGoals == null) {
                this.state.seasonGoals = [];
            }
            this.state.seasonGoals.push(matchGoals);
        }
        return this.state.seasonGoals;
    }


    addRatingToSeason(rating) {
        if (rating) {
            if (this.state.ratings == null) {
                this.state.ratings = []
            }
            this.state.ratings.push(rating);
        }
        return this.state.ratings;
    }

    handleSubmit(e) {
        e.preventDefault();
        // Get values via this.refs
        var player = {
            id: this.state.id,
            name: this.state.name,
            teamName: this.state.teamName,
            bio: this.state.bio,
            teamId: this.state.teamId,
            data: this.addMatchGoalsToTotalSeasonGoals(this.state.totalGoals),
            totalGoals: this.state.totalGoals,
            ratings: this.addRatingToSeason(this.state.rating),
            manOfTheMatches: this.state.manOfTheMatches,
            cleanSheets: this.state.cleanSheets,
            assists: this.state.assists,
            profileImageUrl: this.state.uploadedFileCloudinaryUrl
        };

        postDataTest(player);

    }

    render() {
        return (
            <div className="tray tray-center">
                <div className="row">
                    <div className="col-md-8">
                        <div className="panel mb25 mt5">
                            <div className="panel-heading">
                                <span className="panel-title">Add New Player</span>
                            </div>
                            <div className="panel-body p20 pb10">
                                <div className="form-horizontal">
                                    <div className="form-group">
                                        <label className="control-label">Name</label>
                                        <input type="text" className="form-control" ref="name"
                                               value={this.state.name}
                                               onChange={this.handleChange.bind(this, 'name')}/>
                                    </div>
                                    <div className="form-group">
                                        <label className="control-label">Team Name</label>
                                        <input type="text" className="form-control" ref="teamName"
                                               value={this.state.teamName}
                                               onChange={this.handleChange.bind(this, 'teamName')}/>
                                    </div>
                                    <TeamSelectBox state={this.state.teamId}
                                                   onChange={this.handleChange.bind(this, 'teamId')}/>
                                    <div className="form-group">
                                        <label className="control-label">Bio</label>
                                        <textarea rows="10" cols="10" className="form-control" ref="bio"
                                                  value={this.state.bio}
                                                  onChange={this.handleChange.bind(this, 'bio')}/>
                                    </div>
                                    <div className="form-group">
                                        <label className="control-label">Goals</label>
                                        <input id="intNumber" className="form-control" type="number" min="1" max="100"
                                               value={this.state.totalGoals}
                                               onChange={this.handleChange.bind(this, 'totalGoals')}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label className="control-label">Man Of The Matches</label>
                                        <input id="intNumber" className="form-control" type="number" min="1" max="100"
                                               value={this.state.manOfTheMatches}
                                               onChange={this.handleChange.bind(this, 'manOfTheMatches')}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label className="control-label">Clean Sheets</label>
                                        <input id="intNumber" className="form-control" type="number" min="1" max="100"
                                               value={this.state.cleanSheets}
                                               onChange={this.handleChange.bind(this, 'cleanSheets')}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label className="control-label">Assists</label>
                                        <input id="intNumber" className="form-control" type="number" min="1" max="100"
                                               value={this.state.assists}
                                               onChange={this.handleChange.bind(this, 'assists')}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <label className="control-label">Match Rating</label>
                                        <input id="intNumber" className="form-control" type="number" min="1" max="100"
                                               value={this.state.rating}
                                               onChange={this.handleChange.bind(this, 'rating')}
                                        />
                                    </div>
                                    <div className="form-group">
                                        <Dropzone
                                            multiple={false}
                                            className="dropzone"
                                            accept="image/*"
                                            onDrop={this.onImageDrop.bind(this)}>
                                            <p>Drop an image or click to select a file to upload.</p>
                                        </Dropzone>
                                    </div>
                                    <div className="form-group">
                                        <div>
                                            {this.state.uploadedFileCloudinaryUrl === '' ? null :
                                                <div>
                                                    <p>Preview</p>

                                                    <img className="img-responsive" alt="profile preview"
                                                         src={this.state.uploadedFileCloudinaryUrl}/>
                                                </div>}
                                        </div>
                                    </div>
                                    <div className="bs-component">
                                        <button className="btn btn-md btn-default btn-block"
                                                onClick={this.handleSubmit}>Save &amp; Continue
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

module.exports = EditPlayerForm;