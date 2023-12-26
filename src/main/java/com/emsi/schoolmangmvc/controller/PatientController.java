package com.emsi.schoolmangmvc.controller;

import javax.validation.Valid;
import com.emsi.schoolmangmvc.entity.Patient;
import com.emsi.schoolmangmvc.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping(path = "/user/index")
    public String Patients(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword){
        // pour la pagination
        Page<Patient> pagePatient = patientRepository.findByNameContains(keyword,PageRequest.of(page,size));
        model.addAttribute("patientList",pagePatient  .getContent());
        //getContent => donner la liste students de la page
        model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }

    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
        //redirection sous la page
    }


    // possible de supp
    /**
     *   @GetMapping("/home")
     *     public String home(){
     *         return "home";
     *     }
     */

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/home";
    }

    @GetMapping("/user/patients")
    @ResponseBody //methode return un format json
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }

    @GetMapping("/admin/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

    @PostMapping(path = "/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editPatient/{idPatient}")
    public String editPatient(Model model, @PathVariable Long idPatient, @RequestParam String keyword, @RequestParam int page) {
        Patient patient = patientRepository.findById(idPatient).orElse(null);
        if (patient == null) {
            throw new RuntimeException("Patient Not found");
        }
        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";
    }


    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }



}
