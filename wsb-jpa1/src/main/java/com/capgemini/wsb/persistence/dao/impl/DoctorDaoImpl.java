package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {
    @Override
    public List<DoctorEntity> findBySpecialization(Specialization specialization) {
        Query query = entityManager.createQuery("SELECT d FROM DoctorEntity d " +
                        "WHERE d.specialization = :spec")
                .setParameter("spec", specialization);
        return query.getResultList();
    }


    @Override
    public long countNumOfVisitsWithPatient(String docFirstName, String docLastName, String patientFirstName, String patientLastName) {
        Query query = entityManager.createQuery("SELECT d FROM DoctorEntity d " +
                        "JOIN d.visits v " +
                        "WHERE d.firstName = :docFirstName AND d.lastName = :docLastName " +
                        "AND v.patient.firstName = :patientFirstName AND v.patient.lastName = :patientLastName")
                .setParameter("docFirstName", docFirstName)
                .setParameter("docLastName", docLastName)
                .setParameter("patientFirstName", patientFirstName)
                .setParameter("patientLastName", patientLastName);
        return query.getResultList().size();
    }

}
