package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @Override
    public List<PatientEntity> findByDoctor(String firstName, String lastName) { // TODO - napisac query
        String jpqlQuery = "SELECT p FROM PatientEntity p JOIN p.visits v JOIN v.doctor d WHERE d.firstName = :firstName AND d.lastName = :lastName";
        return entityManager.createQuery(jpqlQuery, PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsHavingTreatmentType(TreatmentType treatmentType) { // TODO - napisac query
        String jpqlQuery = "SELECT p FROM PatientEntity p JOIN p.visits v JOIN v.medicalTreatments t WHERE t.type = :treatmentType";
        return entityManager.createQuery(jpqlQuery, PatientEntity.class)
                .setParameter("treatmentType", treatmentType)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsSharingSameLocationWithDoc(String firstName, String lastName) { // TODO - napisac query
        String jpqlQuery = "SELECT p FROM PatientEntity p JOIN p.addresses pa JOIN pa.doctors d WHERE d.firstName = :firstName AND d.lastName = :lastName";
        return entityManager.createQuery(jpqlQuery, PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithoutLocation() { // TODO - napisac query
        String jpqlQuery = "SELECT p FROM PatientEntity p WHERE p.addresses IS EMPTY";
        return entityManager.createQuery(jpqlQuery, PatientEntity.class)
                .getResultList();
    }
}
