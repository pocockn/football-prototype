import "isomorphic-fetch";

export function postPlayerId(playerId) {
    console.log(playerId);
    fetch("/api/players/removePlayer", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(playerId)

    }).then(response => console.log(response));
}
