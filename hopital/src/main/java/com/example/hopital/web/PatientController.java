package com.example.hopital.web;

import com.example.hopital.entities.Patient;
import com.example.hopital.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;
     @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0")int p,
                        @RequestParam(name = "size",defaultValue = "4")int s  ,
                        @RequestParam(name = "keyword",defaultValue = "")String kw
                        ){
         Page<Patient> patientPage=patientRepository.findByNomContains(kw,PageRequest.of(p,s));
         model.addAttribute("listPatients",patientPage.getContent());
         model.addAttribute("pages",new int[patientPage.getTotalPages()]);
         model.addAttribute("currentPage",p);
         model.addAttribute("keyword",kw);
         return "patients";

     }

    @GetMapping("/delete")
    public String  delete(@RequestParam("id") Long id,
                          @RequestParam(name = "keyword", defaultValue = "") String keyword,
                          @RequestParam(name = "page", defaultValue = "0") int p) {
         patientRepository.deleteById(id);
         return "redirect:/index?page=" + p + "&keyword=" + keyword;}

    @GetMapping("/")
    public String  home() {
        return "redirect:/index";}

}
