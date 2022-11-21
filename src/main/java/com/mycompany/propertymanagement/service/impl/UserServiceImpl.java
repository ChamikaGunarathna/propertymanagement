package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.AddressEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.AddressRepository;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<UserEntity> OptUE = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if(OptUE.isPresent()){
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel error = new ErrorModel();
            error.setCode("Registration Failed");
            error.setErrorMessage("This email is already registered as a user");
            errorModelList.add(error);

            throw new BusinessException(errorModelList);
        }


        UserEntity userEntity = userConverter.userDTOToUserEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNo(userDTO.getHouseNo());
        addressEntity.setStreet(userDTO.getStreet());
        addressEntity.setCity(userDTO.getCity());
        addressEntity.setCountry(userDTO.getCountry());
        addressEntity.setPostalCode(userDTO.getPostalCode());
        addressEntity.setUserEntity(userEntity);

        addressRepository.save(addressEntity);
        userDTO = userConverter.userEntityToUserDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO =null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email,password);
        if(optionalUserEntity.isPresent()){
            userDTO = userConverter.userEntityToUserDTO(optionalUserEntity.get());
        }else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel error = new ErrorModel();
            error.setCode("Invalid Input");
            error.setErrorMessage("Incorrect Email or Password");
            errorModelList.add(error);

            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }
}
