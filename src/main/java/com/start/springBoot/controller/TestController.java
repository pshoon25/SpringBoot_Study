package com.start.springBoot.controller;

import com.start.springBoot.dto.TestDTO;
import com.start.springBoot.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
    @Autowired
    private final TestService testService;

    public TestController(TestService testService){
        this.testService = testService;
    }
    @GetMapping
    public List<TestDTO> getTest(){
        return testService.getAllTest();
    }

    @PostMapping
    public ResponseEntity<TestDTO> postTest(@RequestBody TestDTO dto){
        TestDTO insertDTO = testService.insertTest(dto);
        return new ResponseEntity<TestDTO>(insertDTO, HttpStatus.CREATED);
    }


    @GetMapping(value="/{id}")
    public TestDTO findById(@PathVariable("id") Integer id) {
        return testService.findById(id);
        }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Objects> deleteData(@PathVariable("id") Integer id){
        ResponseEntity<Objects> responseEntity;
        if(testService.deleteData(id)){
            responseEntity = new ResponseEntity<>(HttpStatus.ACCEPTED);
            return responseEntity;
        }
        responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return responseEntity;
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<TestDTO> updateData(@PathVariable("id") Integer id,
                                    @RequestBody TestDTO dto){
        ResponseEntity<TestDTO> responseEntity;
        TestDTO afterData = testService.updateData(id, dto);
        if(afterData == null){
            responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else {
            responseEntity = new ResponseEntity<TestDTO>(afterData, HttpStatus.OK);
        }
        return responseEntity;
    }

    @PostMapping(value = "/init")
    public boolean initTestData(){
        return testService.initData();
    }
}
