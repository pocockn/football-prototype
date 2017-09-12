import "isomorphic-fetch";

export function deleteResource(endpoint, object) {
    fetch(endpoint, {
        method: "DELETE",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(object)

    }).then(response => console.log(response));
}
