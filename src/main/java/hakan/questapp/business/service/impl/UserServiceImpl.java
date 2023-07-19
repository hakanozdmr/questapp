package hakan.questapp.business.service.impl;

import hakan.questapp.business.responses.GetAllUsersResponse;
import hakan.questapp.business.rules.UserBusinessRules;
import hakan.questapp.business.requests.CreateUserRequest;
import hakan.questapp.business.requests.UpdateUserRequest;
import hakan.questapp.business.requests.UserDto;
import hakan.questapp.business.service.DtoConverterService;
import hakan.questapp.business.service.UserService;
import hakan.questapp.entities.User;
import hakan.questapp.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBusinessRules userBusinessRules;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private DtoConverterService dtoConverterService;

    public UserServiceImpl(UserRepository userRepository, UserBusinessRules userBusinessRules, PasswordEncoder passwordEncoder, DtoConverterService dtoConverterService) {
        this.userRepository = userRepository;
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    public List<GetAllUsersResponse> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        List<User> brands = userRepository.findAll(sort);
        List<GetAllUsersResponse> listDto = brands.stream().map(brand -> dtoConverterService.entityToDto(brand,GetAllUsersResponse.class)).collect(Collectors.toList());
        return  listDto;
    }

    @Override
    public UserDto getById(Long id) {
        this.userBusinessRules.checkIfUserExist(id);
        User user = userRepository.findById(id).orElseThrow();
        UserDto userDto = dtoConverterService.entityToDto(user, UserDto.class);
        return userDto;
    }

    @Override
    public void add(@RequestBody CreateUserRequest createUserRequest) {
        this.userBusinessRules.checkIfUserNameExists(createUserRequest.getUserName());
        User user = dtoConverterService.dtoToEntity(createUserRequest,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {
        this.userBusinessRules.checkIfUserExist(updateUserRequest.getId());
        User updatedUser = userRepository.findById(updateUserRequest.getId()).get();
        updatedUser.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        userRepository.save(updatedUser);

    }

    @Override
    public void delete(Long id) {
        this.userBusinessRules.checkIfUserExist(id);
        userRepository.deleteById(id);
    }
}
