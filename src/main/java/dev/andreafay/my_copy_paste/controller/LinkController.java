package dev.andreafay.my_copy_paste.controller;

import dev.andreafay.my_copy_paste.dto.LinkAddRequestDTO;
import dev.andreafay.my_copy_paste.dto.LinkByEmailRequestDTO;
import dev.andreafay.my_copy_paste.model.Link;
import dev.andreafay.my_copy_paste.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/links")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @PostMapping("/add")
    public String addLink(@RequestBody LinkAddRequestDTO linkRequestDTO) {
        try {
            return linkService.addLinkToUser(linkRequestDTO.getUserEmail(), linkRequestDTO.getLinkName(), linkRequestDTO.getLinkUrl());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GetMapping("/get-links")
    public List<Link> getLinksByEmail(@RequestBody LinkByEmailRequestDTO linkByEmailRequestDTO){
        return linkService.getUserLinksByEmail(linkByEmailRequestDTO.getUserEmail());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLink(@RequestParam Long id){
        boolean deleted = linkService.deleteLink(id);
        if (deleted) {
            return ResponseEntity.ok("Link deleted!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Link not found!");
        }
    }
}
