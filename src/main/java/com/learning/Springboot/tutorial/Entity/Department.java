package com.learning.Springboot.tutorial.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;


    @NotBlank(message = "Kindly provide department name")
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;

}

/*    Hibernates validation annotation.
      @NotBlank
      @Positive
      @PositiveOrZero
      @Negative
      @NegativeOrZero
      @Email
      @Size(min=5 , max= 10 ,message = "Please enter data within size")
      @Length(min = 1 , max = 5 , message = "Enter data length in this range")
*/