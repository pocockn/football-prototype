package persistance

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j

import javax.inject.Inject

/**
 * Created by pocockn on 29/07/16.
 */

@Slf4j
class JsonObjectMapper<T> {

    @Inject
    ObjectMapper objectMapper

    String mapObjectToJson(T object) {
        String jsonObject = objectMapper.writeValueAsString(object)
        log.info("${jsonObject} mapped to JSON")
        return jsonObject
    }

    T mapJsonToObject(String jsonString) {
        T t = objectMapper.readValue(jsonString, T.class)
        log.info("${t.toString()} mapped to object")
        return t
    }
}
