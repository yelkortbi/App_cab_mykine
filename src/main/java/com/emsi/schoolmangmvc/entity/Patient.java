package com.emsi.schoolmangmvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatient;
    @NotEmpty
    @Size(min = 4,max = 30)
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRdv;
    private String typeAssurance;

}
