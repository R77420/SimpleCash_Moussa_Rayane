package org.formation.simplecash.adapter.in.web;

import org.formation.simplecash.adapter.in.web.dto.AdvisorDTO;
import org.formation.simplecash.adapter.in.web.dto.WebMapper;
import org.formation.simplecash.application.port.in.AdvisorUseCase;
import org.formation.simplecash.domain.Advisor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advisors")
public class AdvisorController {

    private final AdvisorUseCase advisorUseCase;
    private final WebMapper webMapper;

    public AdvisorController(AdvisorUseCase advisorUseCase, WebMapper webMapper) {
        this.advisorUseCase = advisorUseCase;
        this.webMapper = webMapper;
    }

    @PostMapping
    public AdvisorDTO create(@RequestBody AdvisorDTO advisorDTO) {
        Advisor advisor = webMapper.toDomain(advisorDTO);
        return webMapper.toDTO(advisorUseCase.create(advisor));
    }

    @GetMapping("/{id}")
    public AdvisorDTO get(@PathVariable Long id) {
        return webMapper.toDTO(advisorUseCase.getAdvisor(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        advisorUseCase.delete(id);
    }

    @PutMapping
    public AdvisorDTO update(@RequestBody AdvisorDTO advisorDTO) {
        Advisor advisor = webMapper.toDomain(advisorDTO);
        return webMapper.toDTO(advisorUseCase.update(advisor));
    }
}
