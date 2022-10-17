package com.axioma.axiomatrainee.model.projections;

import com.axioma.axiomatrainee.model.user.Role;
import com.axioma.axiomatrainee.model.user.User;
import org.springframework.data.rest.core.config.Projection;

@Projection(
        name = "user_view",
        types = User.class
)
public interface UserView {
    Long getId();
    String getUsername();
    String getFirstname();
    String getLastname();
    Role getRole();
    Integer getRating();
}
