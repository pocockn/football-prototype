const uuid = require('uuid/v1');
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
            name: '',
            teamName: '',
            bio: '',
            teamId: '',
            uploadedFileCloudinaryUrl: ''
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount() {
        this.setState({
            player: this.findPlayerById(this.props.params.playerId)
        })
    }

    findPlayerById(playerId) {
        axios.get("/api/player/" + playerId)
            .then(res => {
                const player = res.data;
                console.log(player);
                return player;
            });
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

    handleSubmit(e) {
        e.preventDefault();
        // Get values via this.refs
        var player = {
            id: uuid(),
            name: this.state.name,
            teamName: this.state.teamName,
            bio: this.state.bio,
            teamId: this.state.teamId,
            profileImageUrl: this.state.uploadedFileCloudinaryUrl
        };

        postDataTest(player);

        window.location.href = "/players";
    }

    render() {
        return (
            <div class="tray tray-center">
                <div className="row">
                    <div className="col-md-8">
                        <div className="panel mb25 mt5">
                            <div className="panel-heading">
                                <span class="panel-title">Add New Player</span>
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
                                        <label className="control-label">Team Name</label>
                                        <input type="text" className="form-control" ref="teamName"
                                               defaultValue={this.state.teamName}
                                               onChange={this.handleChange.bind(this, 'teamName')}/>
                                    </div>
                                    <TeamSelectBox state={this.state.teamId}
                                                   onChange={this.handleChange.bind(this, 'teamId')}/>
                                    <div className="form-group">
                                        <label className="control-label">Bio</label>
                                        <input type="textarea" className="form-control" ref="bio"
                                               defaultValue={this.state.bio}
                                               onChange={this.handleChange.bind(this, 'bio')}/>
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
                                                    <p>{this.state.uploadedFile.name}</p>
                                                    <img className="img-responsive" alt="profile image preview"
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