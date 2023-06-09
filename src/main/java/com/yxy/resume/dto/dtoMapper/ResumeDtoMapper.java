package com.yxy.resume.dto.dtoMapper;

import com.yxy.resume.dto.ResumeDto;
import com.yxy.resume.pojo.Resume;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/9 17:13
 */
public class ResumeDtoMapper {
    public static Resume mapResumeDtoToResume(ResumeDto resumeDto) {
        Resume resume = new Resume();

        resume.setId(resumeDto.getId());

        if (resumeDto.getName() != null && !resumeDto.getName().isEmpty()) {
            resume.setName(resumeDto.getName().get(0));
        }

        if (resumeDto.getGender() != null && !resumeDto.getGender().isEmpty()) {
            resume.setGender(resumeDto.getGender().get(0));
        }

        if (resumeDto.getBirthPlace() != null && !resumeDto.getBirthPlace().isEmpty()) {
            resume.setBrithPlace(resumeDto.getBirthPlace().get(0));
        }

        if (resumeDto.getBirthday() != null && !resumeDto.getBirthday().isEmpty()) {
            resume.setBirthday(resumeDto.getBirthday().get(0));
        }

        if (resumeDto.getAge() != null && !resumeDto.getAge().isEmpty()) {
            resume.setAge(resumeDto.getAge().get(0));
        }

        if (resumeDto.getPoliticalStatus() != null && !resumeDto.getPoliticalStatus().isEmpty()) {
            resume.setPolitStatus(resumeDto.getPoliticalStatus().get(0));
        }

        if (resumeDto.getCellphone() != null && !resumeDto.getCellphone().isEmpty()) {
            resume.setCellphone(resumeDto.getCellphone().get(0));
        }

        if (resumeDto.getAcademicCareer() != null && !resumeDto.getAcademicCareer().isEmpty()) {
            resume.setAcademicCareer(resumeDto.getAcademicCareer().get(0));
        }

        if (resumeDto.getAddress() != null && !resumeDto.getAddress().isEmpty()) {
            resume.setAddress(resumeDto.getAddress().get(0));
        }

        if (resumeDto.getWorkExperience() != null && !resumeDto.getWorkExperience().isEmpty()) {
            resume.setWorkExperience(resumeDto.getWorkExperience().get(0));
        }

        resume.setGraduateDates(resumeDto.getGraduateDates());
        resume.setGraduateSchools(resumeDto.getGraduateSchools());
        resume.setJobCompanies(resumeDto.getJobCompanies());
        resume.setJobFunction(resumeDto.getJobFunctions());
        resume.setJobTimes(resumeDto.getJobTimes());
        resume.setJobContents(resumeDto.getJobContents());
        resume.setEducationDegrees(resumeDto.getEducationDegrees());
        resume.setProjectExperiences(resumeDto.getProjectExperiences());
        resume.setProjectNames(resumeDto.getProjectNames());
        resume.setProjectTimes(resumeDto.getProjectTimes());

        return resume;
    }
}
