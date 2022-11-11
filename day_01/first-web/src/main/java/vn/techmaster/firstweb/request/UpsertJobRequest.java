package vn.techmaster.firstweb.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpsertJobRequest {
    String title;
    String description;
    String location;
    int minSalary;
    int maxSalary;
    String emailTo;
}
