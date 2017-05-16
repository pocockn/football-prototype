import "isomorphic-fetch";

export function postToApi(endpoint, object) {
    fetch(endpoint, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(object)

    }).then(response => console.log(response));
}
