package service

import entity.Corporation
import model.CorporationViewModel
import repository.CorporationRepository
import java.time.LocalDateTime
import java.util.*

class CorporationService: CorporationRepository {

    fun searchCorporationByDocument(document: String): Corporation {
        return CorporationRepository.fetchByDocument(document)
    }

    fun searchCorporationById(id: String): Corporation {
        return CorporationRepository.fetchById(id)
    }

    fun createCorporation(corporation: CorporatioViewModel) {
        if(searchCorporationByDocument(corporation.document) != null) {
            throw Exception("A corporation with that document already exists")
        }

        val newCorporation = Corporation(corporation.id, corporation.name, corporation.document);

        newCorporation.isActive = true
        newCorporation.contact = corporation.contact
        newCorporation.phone = corporation.phone
        newCorporation.email = corporation.email
        newCorporation.createdAt = LocalDateTime.now()

        CorporationRepository.save(newCorporation)
    }

    fun editCorporation(corporation: CorporationViewModel) {
        val currentCorporation = searchCorporationByDocument(corporation.document)

        if (currentCorporation == null) {
             throw Exception("This corporation does not exist")
        }

        if (!currentCorporation.isActive && corporation.relationship != null ) {
             throw Exception("A relationship cannot be added to inactive corporations")
        }

        if (currentCorporation.relationship && !corporation.isActive ) {
             throw Exception("A corporation with relationship cannot be set inactive")
        }

        val updatedCorporation = Corporation(currentCorporation.id, corporation.name, corporation.document);

        updatedCorporation.isActive = corporation.isActive
        updatedCorporation.contact = corporation.contact
        updatedCorporation.phone = corporation.phone
        updatedCorporation.email = corporation.email
        updatedCorporation.updatedAt = LocalDateTime.now();

        CorporationRepository.update(updatedCorporation)
    }


    fun deleteCorporation(id: String) {
        val currentCorporation = searchCorporationById(id)

        if (currentCorporation == null) {
            throw Exception("This corporation does not exist")
        }

        if (currentCorporation.relationship != null) {
            throw Exception("A corporation with relationship cannot be deleted")
        }

        CorporationRepository.remove(id)
    }

}