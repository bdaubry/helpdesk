package com.brianaubry.helpdesk.models.data;

import com.brianaubry.helpdesk.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
