package com.start.springBoot.service;

import com.start.springBoot.dto.TestDTO;
import com.start.springBoot.repository.TestRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    TestRepository1 testRepository1;

    List<TestDTO> db = new ArrayList<>();

    public List<TestDTO> getAllTest(){
        return testRepository1.findAll();
    }

    public TestDTO insertTest(TestDTO dto){
        int id = db.size();
        dto.setId(id);
        db.add(dto);
        return dto;
    }



    public TestDTO findById(Integer id){
        Optional<TestDTO> findOneById = db.stream().filter(dto -> Objects.equals(dto.getId(), id)).findFirst();
        return findOneById.orElse(null);
    }



    public Boolean deleteData(Integer id){
        try {
            db.remove(id-1);
        }catch (IndexOutOfBoundsException e){
            return false;
//          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return true;
//      return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    public TestDTO updateData( Integer id, TestDTO dto){
        dto.setId(id);
        try {
            db.set(id-1, dto);
        }catch (IndexOutOfBoundsException e){
            return null;
        }
        return dto;
    }

    public boolean initData() {
        Integer size = db.size();
        for(int i=size+1; i<=size+5; i++) {
            TestDTO dto = new TestDTO(i, "park", 10 * i);
            db.add(dto);
        }
        return true;
    }


}
