package com.mycompany.propertymanagement.service;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PropertyService {

    PropertyDTO saveProperty(PropertyDTO propertyDTO);
    List<PropertyDTO> getAllProperties();
    PropertyDTO updateProperty(PropertyDTO propertyDTO,Long propertyID);
    PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long id);
    PropertyDTO updatePropertyTitle(PropertyDTO propertyDTO, Long id);
    //PropertyDTO updatePropertyOwnerName( PropertyDTO propertyDTO, Long id);
    //PropertyDTO updatePropertyOwnerEmail(PropertyDTO propertyDTO, Long id);
    PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO,  Long id);
    PropertyDTO updatePropertyAddress(PropertyDTO propertyDTO, Long id);
    void deleteProperty(Long propertyId);
}
