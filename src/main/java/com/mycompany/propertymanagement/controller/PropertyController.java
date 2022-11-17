package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Value("${pms.dummy:}")
    private String dummy;

    @Autowired
    private PropertyService propertyService;

    //http://localhost:8080/api/v1/hello
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO){
        propertyDTO= propertyService.saveProperty(propertyDTO);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        System.out.println(dummy);
        List<PropertyDTO> propertyDTOList =  propertyService.getAllProperties();
        ResponseEntity<List<PropertyDTO>> responseEntity= new ResponseEntity<>(propertyDTOList,HttpStatus.CREATED);

        return responseEntity;
    }

    @PutMapping("/properties/{propertyID}")
    public ResponseEntity updateProperty(@RequestBody PropertyDTO propertyDTO,@PathVariable Long propertyID){
        propertyDTO = propertyService.updateProperty(propertyDTO,propertyID);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
        return responseEntity;
    }

    @PatchMapping("/properties/update-owner_name/{id}")
    public ResponseEntity updatePropertyOwnerName(@RequestBody PropertyDTO propertyDTO,@PathVariable Long id){
        propertyDTO = propertyService.updatePropertyOwnerName(propertyDTO,id);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-owner_email/{id}")
    public ResponseEntity updatePropertyOwnerEmail(@RequestBody PropertyDTO propertyDTO,@PathVariable Long id){
        propertyDTO = propertyService.updatePropertyOwnerEmail(propertyDTO,id);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @PatchMapping("/properties/update-address/{id}")
    public ResponseEntity updatePropertyAddress(@RequestBody PropertyDTO propertyDTO,@PathVariable Long id){
        propertyDTO = propertyService.updatePropertyAddress(propertyDTO,id);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }
    @PatchMapping("/properties/update-price/{id}")
    public ResponseEntity updatePropertyPrice(@RequestBody PropertyDTO propertyDTO,@PathVariable Long id){
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO,id);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }
    @PatchMapping("/properties/update-title/{id}")
    public ResponseEntity updatePropertyTitle(@RequestBody PropertyDTO propertyDTO,@PathVariable Long id){
        propertyDTO = propertyService.updatePropertyTitle(propertyDTO,id);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }
    @PatchMapping("/properties/update-description/{id}")
    public ResponseEntity updatePropertyDescription(@RequestBody PropertyDTO propertyDTO,@PathVariable Long id){
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO,id);

        ResponseEntity<PropertyDTO> responseEntity = new ResponseEntity<>(propertyDTO, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity deleteProperty(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);
        ResponseEntity<Void> responseEntity= new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return responseEntity;
    }



}
