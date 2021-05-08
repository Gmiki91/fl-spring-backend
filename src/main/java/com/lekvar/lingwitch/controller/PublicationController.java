package com.lekvar.lingwitch.controller;

import com.lekvar.lingwitch.model.Publication;
import com.lekvar.lingwitch.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publications")
@CrossOrigin(origins = "http://localhost:8080")
public class PublicationController {
    @Autowired
    private PublicationRepository publicationRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Publication add(@RequestBody Publication publication){
        return publicationRepository.save(publication);
    }

}
