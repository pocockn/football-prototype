package service.player_services

import ratpack.exec.Promise

interface FindPropertyStatistics {
    Promise<Map<String, Map<String, ?>>> findLargestPropertyValues(List<?> objects, String key)
}
