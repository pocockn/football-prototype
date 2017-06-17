var expect = require('chai').expect;
var fetchTeams = require('src/components/teams/Teams.js').fetchTeams;

describe('GET teams', function () {
    it('returns teams', function (done) {
        // increase default timeout for test
        // if tests take longer it will fail
        this.timeout(3000);

        fetchTeams(function (err, teams) {
            // should return array object
            expect(Array.isArray(teams)).to.equal(true);
            // ensure at least one team is in array
            expect(teams).to.have.length.above(1);
            // Each of items in array should be an array
            teams.forEach(function (team) {
                expect(follower).to.be.a('Array');
            });
            done();
        });
    });
});