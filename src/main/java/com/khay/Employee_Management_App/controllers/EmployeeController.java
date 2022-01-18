package com.khay.Employee_Management_App.controllers;

import com.khay.Employee_Management_App.models.Employee;
import com.khay.Employee_Management_App.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/v1/employee")
public class EmployeeController {

    EmployeeRepository repository;

    @Autowired
    public EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/")
    public String findAll(Model model){
        List<Employee> list = loadEmployees();
        model.addAttribute("employees",list);
        return "index";
    }
    @GetMapping("/register")
    public String showEmployeeForm(Model model){
        model.addAttribute("employee",new Employee());
        return "employee-form";
    }

    @PostMapping("/")
    public String addEmployee(@ModelAttribute("employee")Employee employee,Model model){
        repository.save(employee);
        model.addAttribute("employee",employee);
        return "success";
    }

    @GetMapping("/update")
    public String updateEmployee(Model model,@RequestParam("id") long id){
        Employee employee = repository.getById(id);
        model.addAttribute("employee",employee);
        return "update-form";
    }

    @GetMapping("/update/confirm")
    public String confirmUpdate(@ModelAttribute("employee") Employee employee){
        repository.save(employee);
        return "update-success";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") long id){
        repository.deleteById(id);
        return "delete-success";
    }

    @GetMapping("/view")
    public String viewEmployee(@RequestParam("id") long id,Model model){
        Employee employee = repository.getById(id);
        model.addAttribute("employee",employee);
        return "view-page";
    }
    
    @PostConstruct
    public List<Employee> loadEmployees(){
        return repository.findAll();
    }
}
