import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from 'axios';

class App extends Component {
    constructor(props) {
        super(props);

        this.state = {
            teams : []
        };
    }

    componentDidMount() {
        axios.get("/api/teams")
            .then(res => {
                const teams = res.data;
                this.setState({teams});
                console.log(teams);
            });
    }

  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to React</h2>
        </div>
        <h2>Teams</h2>
        <ul>
            {this.state.teams.map(singleTeam =>
                <li key={singleTeam.id}>{singleTeam.team.name}</li>
            )}
            <button>delete</button>
        </ul>

        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
      </div>
    );
  }
}

export default App;
