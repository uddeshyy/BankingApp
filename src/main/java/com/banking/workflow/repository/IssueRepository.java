package com.banking.workflow.repository;

import org.springframework.data.repository.CrudRepository;

import com.banking.workflow.entity.Issue;


public interface IssueRepository extends CrudRepository<Issue, Long> {

}
