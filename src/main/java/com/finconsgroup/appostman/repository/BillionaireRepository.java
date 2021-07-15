package com.finconsgroup.appostman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finconsgroup.appostman.model.Bilionaire;

@Repository
public interface BillionaireRepository extends JpaRepository<Bilionaire, Long>{

}
