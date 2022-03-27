package com.uni.JobScraper;

public class JobOffers {
    static String JobRefNumber;
    static String JobLocation;
    static String JobSalary;
    static String JobName;
    static String JobPosition;
    static String JobLink;

    public String getJobRefNumber() {
        return JobRefNumber;
    }

    public void setJobRefNumber(String jobRefNumber) {
        JobRefNumber = jobRefNumber;
    }

    public String getJobLocation() {
        return JobLocation;
    }

    public void setJobLocation(String jobLocation) {
        JobLocation = jobLocation;
    }

    public String getJobSalary() {
        return JobSalary;
    }

    public void setJobSalary(String jobSalary) {
        JobSalary = jobSalary;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public String getJobPosition() {
        return JobPosition;
    }

    public void setJobPosition(String jobPosition) {
        JobPosition = jobPosition;
    }

    public String getJobLink() {
        return JobLink;
    }

    public void setJobLink(String jobLink) {
        JobLink = jobLink;
    }

    public JobOffers(String jobRefNumber, String jobLocation, String jobSalary, String jobName, String jobPosition, String jobLink) {
        JobRefNumber = jobRefNumber;
        JobLocation = jobLocation;
        JobSalary = jobSalary;
        JobName = jobName;
        JobPosition = jobPosition;
        JobLink = jobLink;
    }

    public JobOffers() {
    }

    @Override
    public String toString() {
        return  "\nJob Reference Number=" + getJobRefNumber() + '\n' +
                "Job Name=" + getJobName() + '\n' +
                "Job Position=" + getJobPosition() + '\n' +
                "Job Location=" + getJobLocation() + '\n' +
                "Job Salary=" + getJobSalary() + '\n' +
                "Job Link=" +getJobLink() + '\n';
    }
}
