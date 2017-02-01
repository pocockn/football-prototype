import "isomorphic-fetch";

export function postDataTest(id) {
    console.log(id);
    fetch("/api/teams", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(id)

    }).then(response => console.log(response));
}
