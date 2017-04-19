import "isomorphic-fetch";

export function addTeam(team) {
    console.log(team);
    fetch("/api/addTeam", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(team)
    }).then(response => console.log(response));
}
