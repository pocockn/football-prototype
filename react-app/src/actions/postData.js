import "isomorphic-fetch";

export function postDataTest(id) {
    fetch("/api/teams", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: {
            data: JSON.stringify(id),
            contentType: 'application/json',
            dataType: 'text'
        }
    }).then(response => console.log(response));
}
