package com.brianaubry.helpdesk.repository;


import com.brianaubry.helpdesk.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("updateRepository")
@Transactional
public interface UpdateRepository extends JpaRepository<Status, Integer> {
}
