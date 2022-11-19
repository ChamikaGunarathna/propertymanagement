package com.mycompany.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldException(MethodArgumentNotValidException manv){
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel error = null;
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();
        for (FieldError fe:fieldErrorList){
            logger.debug("Inside field validation : {}-{}",fe.getField(),fe.getDefaultMessage());
            logger.info("Inside field validation : {}-{}",fe.getField(),fe.getDefaultMessage());
            error = new ErrorModel();
            error.setCode(fe.getField());
            error.setErrorMessage(fe.getDefaultMessage());
            errorModelList.add(error);
        }

        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex){
        for(ErrorModel em: bex.getErrors()){
            logger.debug("Business Exception is thrown -level -debug : {}-{}",em.getCode(),em.getErrorMessage());
            logger.info("Business Exception is thrown -level -info : {}-{}",em.getCode(),em.getErrorMessage());
            logger.warn("Business Exception is thrown -level -warn : {}-{}",em.getCode(),em.getErrorMessage());
            logger.error("Business Exception is thrown -level -error : {}-{}",em.getCode(),em.getErrorMessage());
        }

        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
