package model

import java.time.LocalDateTime

data class CorporationViewModel(val id: String, val name: String, val document: String) {
    lateinit var contact: String
    lateinit var phone: String
    lateinit var email: String
    var isActive: Boolean = true
    var relationship: RelationshipViewModel? = null
}

data class RelationshipViewModel(
    val id: String,
    val name: String,
    val primaryCorporation: CorporationViewModel,
    val secondaryCorporation: CorporationViewModel
)
