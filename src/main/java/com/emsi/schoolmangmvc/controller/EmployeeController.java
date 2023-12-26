package com.emsi.schoolmangmvc.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import com.emsi.schoolmangmvc.entity.Employee;
import com.emsi.schoolmangmvc.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor


public class EmployeeController {
    private EmployeeRepository employeeRepository;
    @GetMapping(path = "/user/indexT")
    public String Employee(Model model,  @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "5") int size,
                          @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Employee> employeePage = employeeRepository.findByFullnameContains(keyword,PageRequest.of(page,size));
        model.addAttribute("Listeemployee",employeePage.getContent());
        model.addAttribute("pages",new int[employeePage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "employee";
    }
    @GetMapping(path = "/admin/Delete")
    public String delete(Long id, String keyword,int page){
        employeeRepository.deleteById(id);
        return "redirect:/user/indexT?page="+page+"&keyword="+keyword;
    }
    @GetMapping(path = "/user/employee")
    @ResponseBody
    public List<Employee>employeeList(){
        return employeeRepository.findAll();
    }

    @GetMapping("/admin/formEmployee")
    public String formEmployee(Model model){
        model.addAttribute("employee",new Employee());
        return "formEmployee";
    }
    @PostMapping(path = "/admin/add")
    public  String add (Model model , @Valid Employee employee, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "") String keyword){
        if (bindingResult.hasErrors()) return "formEmployee";
        employeeRepository.save(employee);
        return "redirect:/user/indexT?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/editEmployee")
    public String editEmployee(Model model, Long id, String keyword,int page){
        Employee employee=employeeRepository.findById(id).orElse(null);
        if (employee==null) throw new RuntimeException("Employee introuvable");
        model.addAttribute("employee",employee);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editEmployee";
    }




}