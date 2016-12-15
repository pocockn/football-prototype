package service

import ratpack.exec.Promise

class FindLargestPropertyValues {

    List<String> propertyNames
    Map<String, Map<String, ?>> largestPropertiesAndValues

    FindLargestPropertyValues(List<String> propertyValues) {
        propertyNames = propertyValues
        largestPropertiesAndValues = [:]
    }

    Promise<Map<String, Map<String, ?>>> findLargestPropertyValues(List<?> objects, String key) {
        Integer currentLargestValue = 0
        createPlayerStatisticsMap(objects, currentLargestValue, key)

        return Promise.value(largestPropertiesAndValues)
    }

    private List<String> createPlayerStatisticsMap(objects, currentHighestValue, key) {
        for (singlePropertyName in propertyNames) {
            returnLargestPropertyValues(objects, currentHighestValue, key, singlePropertyName).each { keySet, value ->
                def innerMap = [:]
                innerMap.put(keySet.toString(), value.toString())
                largestPropertiesAndValues.put(singlePropertyName, innerMap)
                currentHighestValue = 0
            }
        }
    }

    private
    static Map<?, ?> returnLargestPropertyValues(List<?> objects, Integer currentHighestValue, String key, String singlePropertyName) {
        Map currentKeyValue = new HashMap()
        objects.each {
            if (it."${singlePropertyName}" > currentHighestValue) {
                currentKeyValue.clear()
                currentKeyValue.put(it."${key}", it."${singlePropertyName}")
                currentHighestValue = it."${singlePropertyName}"
            }
        }
        currentKeyValue
    }
}
