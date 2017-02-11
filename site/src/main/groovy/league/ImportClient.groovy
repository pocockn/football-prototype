package league

import wslite.rest.RESTClient
import wslite.rest.Response

class ImportClient {

    private final RESTClient restClient

    private static
    final apiKey = "69a83e34898b45339048596e1f159a40c592b94ce3940fe814cbdbdbda2972839aa29a8f2eb60c899a834704a2581d5fd83cea293bc25310dd3e634c4a263d214d254f29ae50affcf20b1eecae0c8e6b"

    private static final userId = "72dcd0af-06dd-4f17-809d-52d0800f5ec6"

    ImportClient() {
        restClient = new RESTClient("https://extraction.import.io/")
    }

    Response leagueDataExtractor() {
        restClient.get(path: "query/extractor/72dcd0af-06dd-4f17-809d-52d0800f5ec6?_apikey=69a83e34898b45339048596e1f159a40c592b94ce3940fe814cbdbdbda2972839aa29a8f2eb60c899a834704a2581d5fd83cea293bc25310dd3e634c4a263d214d254f29ae50affcf20b1eecae0c8e6b&url=http%3A%2F%2Flondon5aside.spawtz.com%2FSpawtzSkin%2FFixtures%2FStandings.aspx%3FLeagueId%3D28%26SeasonId%3D61")
    }
}

