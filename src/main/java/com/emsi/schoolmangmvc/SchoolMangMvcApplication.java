package com.emsi.schoolmangmvc;

import com.emsi.schoolmangmvc.entity.Branch;
import com.emsi.schoolmangmvc.entity.Courses;
import com.emsi.schoolmangmvc.entity.Patient;
import com.emsi.schoolmangmvc.entity.Employee;
import com.emsi.schoolmangmvc.repositories.BranchRepository;
import com.emsi.schoolmangmvc.repositories.CoursesRepository;
import com.emsi.schoolmangmvc.repositories.PatientRepository;
import com.emsi.schoolmangmvc.repositories.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SchoolMangMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolMangMvcApplication.class, args);
    }

   //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
           patientRepository.save(new Patient(null,"Ahmed said", java.sql.Date.valueOf("2023-12-22"),"CNSS"));
            patientRepository.save(new Patient(null,"Ali Assif", java.sql.Date.valueOf("2023-12-21"),"CNOPS"));
            patientRepository.save(new Patient(null,"Sara cherif", java.sql.Date.valueOf("2023-12-29"),"CNSS"));
            patientRepository.save(new Patient(null,"Aya bnjeloun", java.sql.Date.valueOf("2023-11-30"),"CMIR"));

            patientRepository.findAll().forEach(s->{
                System.out.println(s.getName());
            });
        };
    }

    //@Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository){
        return args -> {
            employeeRepository.save(new Employee(null,"Ettamen",new Date(),11));
            employeeRepository.save(new Employee(null,"Sadrafe",new Date(),6));
            employeeRepository.save(new Employee(null,"Elmasoudi",new Date(),5));
            employeeRepository.save(new Employee(null,"Bahou",new Date(),10));
            employeeRepository.save(new Employee(null,"Ezzaki",new Date(),14));

            employeeRepository.findAll().forEach(t->{
                System.out.println(t.getFullname());
            });

        };
    }

    //@Bean
    CommandLineRunner commandLineRunner(BranchRepository branchRepository){
        return args -> {
            branchRepository.save(new Branch(null,"Software Engineering",true));
            branchRepository.save(new Branch(null,"Finance and audit Engineering",false));
            branchRepository.save(new Branch(null,"Civil Engineering",true));
          branchRepository.findAll().forEach(b->{
              System.out.println(b.getName());
          });
        };
    }
    //@Bean
    CommandLineRunner commandLineRunner(CoursesRepository coursesRepository){
        return args -> {
            coursesRepository.save(new Courses(null,"Software Engineering","Web Programmation","Mobile Programmation","Devops","Big Data"));
            coursesRepository.save(new Courses(null,"Finance and audit Engineering","Strategic Planning","Cost Control","Financial Services","Modelling Financial"));
            coursesRepository.save(new Courses(null,"Civil Engineering","Structural mechanics","Maths","Hydraulics","Engineering geology"));

            coursesRepository.findAll().forEach(c->{
                System.out.println(c.getBranch());
            });

        };
    }

}
