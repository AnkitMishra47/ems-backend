package com.ems.controller;

import com.ems.model.types.Gender;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/svc/api/")
public class EnumController {

    @GetMapping("/genders")
    public List<Gender> getAllGenders(@RequestParam("IsDisabled") Boolean isDisabled){
        return  Gender.getAllGenders();
    }
}
