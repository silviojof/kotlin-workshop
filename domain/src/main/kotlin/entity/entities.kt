package entity

import java.time.LocalDateTime

data class Corporation(val id: String, val name: String, val document: String) {
    lateinit var contact: String
    lateinit var phone: String
    lateinit var email: String
    var active: Boolean = false
    lateinit var createdAt: LocalDateTime
    lateinit var updatedAt: LocalDateTime
    var relationship: Relationship? = null
}

data class Relationship(val id: String, val name: String, val createdAt: String )
