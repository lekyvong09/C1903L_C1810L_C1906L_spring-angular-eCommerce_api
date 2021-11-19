package com.ray.ecommerce.dao;

import com.ray.ecommerce.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface StateRepository extends JpaRepository<State, Integer> {
    // http://localhost:8080/api/states/search/findByCountryCode?code=...
    List<State> findByCountryCode(@RequestParam("code") String code);
}
