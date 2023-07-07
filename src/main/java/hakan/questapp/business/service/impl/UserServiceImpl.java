package hakan.questapp.business.service.impl;

import hakan.questapp.business.rules.UserBusinessRules;
import hakan.questapp.business.requests.CreateUserRequest;
import hakan.questapp.business.requests.UpdateUserRequest;
import hakan.questapp.business.responses.UserDto;
import hakan.questapp.business.service.UserService;
import hakan.questapp.entities.User;
import hakan.questapp.exceptions.BusinessException;
import hakan.questapp.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserBusinessRules userBusinessRules;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserBusinessRules userBusinessRules, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userBusinessRules = userBusinessRules;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC,"id");
        List<User> brands = userRepository.findAll(sort);
        List<UserDto> listDto = brands.stream().map(brand -> EntityToDto(brand)).collect(Collectors.toList());
        return  listDto;
    }

    @Override
    public UserDto getById(Long id) {
        this.userBusinessRules.checkIfUserExist(id);
        User user = userRepository.findById(id).orElseThrow();
        UserDto userDto = EntityToDto(user);
        return userDto;
    }

    @Override
    public void add(@RequestBody CreateUserRequest createUserRequest) {
        this.userBusinessRules.checkIfUserNameExists(createUserRequest.getUserName());
        User user = DtoToEntity(createUserRequest);
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

    ////////////////////////////////////
    //Model Mapper Entity ==> Dto
    @Override
    public <T> UserDto  EntityToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    //Model Mapper Dto  ==> Entity
    @Override
    public <T> User DtoToEntity(T dto) {
        User user = modelMapper.map(dto, User.class);
        return user;
    }
}
