package com.axioma.axiomatrainee.api;

import com.axioma.axiomatrainee.model.user.User;
import com.axioma.axiomatrainee.requestdto.SaveUserRequestDto;
import com.axioma.axiomatrainee.requestdto.UpdateUserRatingRequestDto;
import com.axioma.axiomatrainee.requestdto.UpdateUserRoleRequestDto;
import com.axioma.axiomatrainee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('teacher')")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user')")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid SaveUserRequestDto request) {
        return userService.save(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @PatchMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public User updateUserRoles(@RequestBody UpdateUserRoleRequestDto dto) {
        return userService.updateUserRole(dto.getUserId(), dto.getRole());
    }

    @PatchMapping("/rating")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('teacher')")
    public void updateUserRating(@RequestBody UpdateUserRatingRequestDto requestDto) {
        userService.incrementRating(
                requestDto.getUserId(),
                requestDto.getRatingIncNumber()
        );
    }


}
