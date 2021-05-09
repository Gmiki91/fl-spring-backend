package com.lekvar.lingwitch.controller;

import com.lekvar.lingwitch.model.Publication;
import com.lekvar.lingwitch.repository.PublicationRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping("/publications")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicationController {
    @Autowired
    private PublicationRepository publicationRepository;

    @PostMapping
    public ResponseEntity<Publication> add(@RequestBody Publication publication, @RequestHeader (name="Authorization") String token) throws JSONException {
        Base64.Decoder decoder = Base64.getDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject json = new JSONObject(payload);
        publication.setUserId(json.get("userId").toString());
        publication.setDateOfPublish(new Date());
        publication.setDateOfLastLecture(null);
        publication.setAuthor(json.get("userName").toString());
        publication.setLanguage(json.get("userLanguage").toString());
        try {
            Publication pub = publicationRepository.save(publication);
            return new ResponseEntity<>(pub, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
