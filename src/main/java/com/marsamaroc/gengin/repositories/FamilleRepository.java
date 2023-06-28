package com.marsamaroc.gengin.repositories;

import com.marsamaroc.gengin.models.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilleRepository extends JpaRepository<Famille, Long> {

}
