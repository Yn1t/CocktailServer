package com.example.cocktailserver.controllers;

import com.example.cocktailserver.controllers.exceptions.BaseAlreadyExistsException;
import com.example.cocktailserver.controllers.models.BaseRequest;
import com.example.cocktailserver.services.BaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/base")
public class BaseController {

    private final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    @PostMapping("/add")
    public String addBase(@RequestBody BaseRequest request) throws BaseAlreadyExistsException {
        return baseService.addBase(request);
    }

    @PostMapping("/add_bases")
    public @ResponseBody Map<String,List<String>> addBases(@RequestBody Map<String,List<String>> names)
            throws BaseAlreadyExistsException {
        names.put("bases", baseService.addBases(names.get("bases")));
        return names;
    }
}
