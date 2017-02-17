import "isomorphic-fetch";

export function postDataTest(player) {
    console.log(player);
    fetch("/api/players/addGetPlayers", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(player)

    }).then(response => console.log(response));
}
