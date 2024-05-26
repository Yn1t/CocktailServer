package com.example.cocktailserver.controllers;

import com.example.cocktailserver.controllers.exceptions.StyleAlreadyExistException;
import com.example.cocktailserver.controllers.models.StyleRequest;
import com.example.cocktailserver.database.entities.Style;
import com.example.cocktailserver.services.StyleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/style")
public class StyleController {

    private final StyleService styleService;

    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @PostMapping("/add")
    public String addStyle(@RequestBody StyleRequest request) throws StyleAlreadyExistException {
        return styleService.addStyle(request);
    }


    @PostMapping("/add_styles")
    public @ResponseBody Map<String, List<String>> addStyles(@RequestBody Map<String, List<StyleRequest>> stylesRequest)
            throws StyleAlreadyExistException {
        return styleService.addStyles(stylesRequest.get("styles"));
    }
}
