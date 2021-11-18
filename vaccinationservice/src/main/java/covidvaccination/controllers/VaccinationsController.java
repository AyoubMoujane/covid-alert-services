package covidvaccination.controllers;

import covidvaccination.models.Vaccination;
import covidvaccination.repositories.VaccinationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vaccinations")
public class VaccinationsController {

    private final VaccinationRepository vaccinationRepository;

    public VaccinationsController(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }

    @GetMapping
    public List<Vaccination> list() {
        return vaccinationRepository.findAll();
    }


    @GetMapping
    @RequestMapping("{id}")
    public Vaccination get(@PathVariable Long id) {
        if(vaccinationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaccination with ID "+id+" not found");
        }
        return vaccinationRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vaccination create(@RequestBody Vaccination vaccination) {
        return  vaccinationRepository.saveAndFlush(vaccination);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // TODO: Ajouter ici une validation si tous les champs ont ete passesSinon, retourner une erreur 400 (Bad Payload)
        if(vaccinationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaccination with ID "+id+" not found");
        }
        vaccinationRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Vaccination update(@PathVariable Long id, @RequestBody Vaccination vaccination) {
        // TODO: Ajouter ici une validation si tous les champs ont ete passes Sinon, retourner une erreur 400 (Bad Payload)
        if(vaccinationRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaccination with ID "+id+" not found");
        }
        Vaccination existingVaccine = vaccinationRepository.findById(id).get();
        BeanUtils.copyProperties(vaccination,existingVaccine,"vaccination_id");
        return vaccinationRepository.saveAndFlush(existingVaccine);
    }

    @RequestMapping("by_user/{id}")
    public List<Vaccination> byUser(@PathVariable("id") String id) {
        try {
            return vaccinationRepository.getUserVaccination(id);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw exception;

        }
    }

    @RequestMapping("is_vaccinated/{id}")
    public boolean isVaccinated (@PathVariable("id") String id){
        try {
            return vaccinationRepository.isUserVaccineted(id);
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw exception;

        }
    }






}
