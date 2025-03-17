package com.example.hopital.web;

import com.example.hopital.entities.Patient;
import com.example.hopital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
     @GetMapping("/index")
    public String index(Model model){
         List<Patient> patientList=patientRepository.findAll();
         model.addAttribute("listPatients",patientList);
         return "patients";

     }

}
