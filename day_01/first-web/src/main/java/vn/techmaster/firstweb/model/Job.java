package vn.techmaster.firstweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Job {
    String id;
    String title;
    String description;
    String location;
    int minSalary;
    int maxSalary;
    String emailTo;
}
