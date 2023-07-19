package hakan.questapp.business.service;

import hakan.questapp.business.requests.CreateUserRequest;
import hakan.questapp.business.requests.UpdateUserRequest;
import hakan.questapp.business.requests.UserDto;
import hakan.questapp.business.responses.GetAllUsersResponse;
import hakan.questapp.entities.User;

import java.util.List;

public interface UserService {

    List<GetAllUsersResponse> getAll();

    UserDto getById(Long id);
    void add(CreateUserRequest createUserRequest);

    void update(UpdateUserRequest updateUserRequest);

    void delete(Long id);

}
