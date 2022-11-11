package vn.techmaster.firstweb.service;

import org.springframework.stereotype.Service;
import vn.techmaster.firstweb.model.Job;
import vn.techmaster.firstweb.request.UpsertJobRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final List<Job> jobs;

    public JobService() {
        jobs = new ArrayList<>();
        jobs.add(new Job("01", "Job1", "Desc1", "Loc1", 100, 1000, "email1@example.com"));
        jobs.add(new Job("02", "Job2", "Desc2", "Loc2", 200, 2000, "email2@example.com"));
        jobs.add(new Job("03", "Job3", "Desc3", "Loc3", 300, 3000, "email3@example.com"));
        jobs.add(new Job("04", "Job4", "Desc4", "Loc4", 400, 4000, "email4@example.com"));
        jobs.add(new Job("05", "Job5", "Desc5", "Loc5", 500, 5000, "email5@example.com"));
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public Job getJobById(String id) {
        return jobs.stream().filter(job -> job.getId().equals(id)).findFirst().orElse(null);
    }

    public Job createJob(UpsertJobRequest request) {
        Random rd = new Random();
        String id = ((Integer) rd.nextInt(10000)).toString();
        Job job = new Job(
                id,
                request.getTitle(),
                request.getDescription(),
                request.getLocation(),
                request.getMinSalary(),
                request.getMaxSalary(),
                request.getEmailTo()
        );
        jobs.add(job);
        return job;
    }

    public Job updateJob(String id, UpsertJobRequest request) {
        Job job = jobs.stream().filter(j -> j.getId().equals(id)).findFirst().orElse(null);
        if (job != null) {
            job.setTitle(request.getTitle());
            job.setDescription(request.getDescription());
            job.setLocation(request.getLocation());
            job.setMinSalary(request.getMinSalary());
            job.setMaxSalary(request.getMaxSalary());
            job.setEmailTo(request.getEmailTo());
        }
        return job;
    }

    public void deleteJob(String id) {
        jobs.removeIf(job -> job.getId().equals(id));
    }

    public Job getRandomJob() {
        Random rd = new Random();
        return jobs.get(rd.nextInt(jobs.size()));
    }

    public List<Job> getSortedJobs(String max_salary) {
        if(max_salary.equals("desc")) {
            return jobs.stream().sorted(Comparator.comparingInt(Job::getMaxSalary).reversed()).collect(Collectors.toList());
        }
        return jobs.stream().sorted(Comparator.comparingInt(Job::getMaxSalary)).collect(Collectors.toList());
    }
}
