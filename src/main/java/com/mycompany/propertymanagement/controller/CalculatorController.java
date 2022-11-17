package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {
    //http://localhost:8080/api/v1/calculator/add?num1=3.67&num2=8.86
    @GetMapping("/add")
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2){
        return num1+num2;
    }

    //http://localhost:8080/api/v1/calculator/subtraction/8/-13.6
    @GetMapping("/subtraction/{num1}/{num2}")
    public Double subtract(@PathVariable("num1") Double num1,@PathVariable("num2") Double num2){
        if(num1>num2) {
            return num1-num2;
        }
        else{
            return num2-num1;
        }
    }

    //http://localhost:8080/api/v1/calculator/add/1?num1=13.6&num2=5.78
    @GetMapping("/add/{num3}")
    public Double addThree(@RequestParam("num1") Double num1,@RequestParam("num2") Double num2,@PathVariable("num3") Double num3){
        return num1+num2+num3;
    }

    @PostMapping("/mul")
    public ResponseEntity multiply(@RequestBody CalculatorDTO calculatorDTO){
        Double result = null;
        result = calculatorDTO.getNum1() * calculatorDTO.getNum2()*calculatorDTO.getNum3()*calculatorDTO.getNum4();
        ResponseEntity<Double> responseEntity = new ResponseEntity<Double>(result, HttpStatus.CREATED);
        return responseEntity;
    }
}
