package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {


    @Override
    public List<PatientEntity> findByDoctor(String firstName, String lastName) {
        Query query = entityManager.createQuery("SELECT p FROM PatientEntity p " +
                        "JOIN p.visits v " +
                        "JOIN v.doctor d " +
                        "WHERE v.doctor.firstName = :firstName AND v.doctor.lastName = :lastName")
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName);
        return query.getResultList();
    }


    @Override
    public List<PatientEntity> findPatientsHavingTreatmentType(TreatmentType treatmentType) {
        Query query = entityManager.createQuery("SELECT distinct p FROM PatientEntity p " +
                        "JOIN p.visits v " +
                        "JOIN v.medicalTreatments mt " +
                        "WHERE mt.type = :treatmentType")
                .setParameter("treatmentType", treatmentType);
        return query.getResultList();
    }


    @Override
    public List<PatientEntity> findPatientsSharingSameLocationWithDoc(String firstName, String lastName) {
        Query query = entityManager.createQuery("SELECT p FROM PatientEntity p " +
                        "JOIN p.addresses a " +
                        "JOIN a.doctors d " +
                        "WHERE d.firstName = :docFirstName AND d.lastName = :docLastName")
                .setParameter("docFirstName", firstName)
                .setParameter("docLastName", lastName);
        return query.getResultList();
    }


    @Override
    public List<PatientEntity> findPatientsWithoutLocation() {
        Query query = entityManager.createQuery("SELECT p FROM PatientEntity p WHERE size(p.addresses) = 0");
        return query.getResultList();
    }

}
