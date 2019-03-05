package com.brianaubry.helpdesk.models.data;

import com.brianaubry.helpdesk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User,Integer>{
}
