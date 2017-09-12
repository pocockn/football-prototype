import "isomorphic-fetch";
import {browserHistory} from "react-router";

export function postToApi(endpoint, object, redirectPath) {
    fetch(endpoint, {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(object)

    }).then(
        browserHistory.push(redirectPath),
        window.location.reload()
    );
}
