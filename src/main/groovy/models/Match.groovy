package models

import com.fasterxml.jackson.annotation.JsonFormat

import java.time.LocalDateTime
import java.time.ZonedDateTime

class Match {
    String id
    String title
    String start
    String end
    String className = 'fc-event-primary'
    String textColor = 'black'
}
