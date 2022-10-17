package com.axioma.axiomatrainee.model.projections;

import com.axioma.axiomatrainee.model.Group;
import com.axioma.axiomatrainee.model.user.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(
        name = "group_view",
        types = Group.class
)
public interface GroupView {
    Long getId();
    String getName();
    Set<User> getUsers();
}
