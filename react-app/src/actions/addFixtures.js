import "isomorphic-fetch";
//TODO make a generic post function that takes an endpoint and object
export function addFixtures(fixturesList) {
    console.log(fixturesList);
    fetch("/api/fixtures/00001/save-fixtures", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(fixturesList)

    }).then(response => console.log(response));
}
