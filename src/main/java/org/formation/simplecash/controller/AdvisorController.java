package org.formation.simplecash.controller;

import org.formation.simplecash.entity.Advisor;
import org.formation.simplecash.service.AdvisorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisors")
public class AdvisorController {

    private final AdvisorService advisorService;

    public AdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @PostMapping
    public Advisor create(@RequestBody Advisor advisor) {
        return advisorService.create(advisor);
    }

    @GetMapping("/{id}")
    public Advisor get(@PathVariable Long id) {
        return advisorService.getAdvisor(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        advisorService.delete(id);
    }

    @PutMapping
    public Advisor update(@RequestBody Advisor advisor) {
        return advisorService.update(advisor);
    }
}
