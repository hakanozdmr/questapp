package hakan.questapp.business.service;

import hakan.questapp.business.requests.CreateUserRequest;
import hakan.questapp.business.requests.UpdateUserRequest;
import hakan.questapp.business.responses.GetAllUsersResponse;
import hakan.questapp.business.responses.UserDto;
import hakan.questapp.entities.User;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto getById(Long id);
    void add(CreateUserRequest createUserRequest);

    void update(UpdateUserRequest updateUserRequest);

    void delete(Long id);


    //model mapper
    public <T> UserDto EntityToDto(User user);
    public <T> User DtoToEntity(T dto);
}
