package vn.techmaster.firstweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.firstweb.model.Job;
import vn.techmaster.firstweb.request.UpsertJobRequest;
import vn.techmaster.firstweb.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping("/jobs")
    List<Job> getJobs() {
        return jobService.getJobs();
    }

    @GetMapping("/jobs/{id}")
    Job getJob(@PathVariable String id) {
        return jobService.getJobById(id);
    }

    @GetMapping("/jobs/random")
    Job getRandomJob() {
        return jobService.getRandomJob();
    }

    @GetMapping("/jobs/sort")
    List<Job> getJobsSorted(@RequestParam String max_salary) {
        return jobService.getSortedJobs(max_salary);
    }

    @PostMapping("/jobs")
    public Job createJob(@RequestBody UpsertJobRequest request) {
        return jobService.createJob(request);
    }

    @PutMapping("/jobs/{id}")
    public Job updateJob(@PathVariable String id, @RequestBody UpsertJobRequest request) {
        return jobService.updateJob(id, request);
    }

    @DeleteMapping("/jobs/{id}")
    public void deleteBook(@PathVariable String id) {
        jobService.deleteJob(id);
    }
}
