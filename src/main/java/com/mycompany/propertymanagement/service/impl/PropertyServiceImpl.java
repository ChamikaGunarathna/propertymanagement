package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Value("${pms.dummy:}")
    private String dummy;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;
    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        propertyDTO = propertyConverter.convertEntityToDTO(pe);
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfProp = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();

        for(PropertyEntity pe:listOfProp){
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }

        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyID) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyID);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setDescription(propertyDTO.getDescription());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyTitle(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setTitle(propertyDTO.getTitle());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }

    /*@Override
    public PropertyDTO updatePropertyOwnerName(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setOwnerName(propertyDTO.getOwnerName());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }*/

    /*@Override
    public PropertyDTO updatePropertyOwnerEmail(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }*/

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setPrice(propertyDTO.getPrice());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyAddress(PropertyDTO propertyDTO, Long id) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(id);
        PropertyDTO dto = null;
        if(optEn.isPresent()){
            PropertyEntity pe = optEn.get();
            pe.setAddress(propertyDTO.getAddress());
            propertyRepository.save(pe);
            dto = propertyConverter.convertEntityToDTO(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
