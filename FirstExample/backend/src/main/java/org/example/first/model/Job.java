package org.example.first.model;

import javax.persistence.*;

@Entity
@Table(name = "Jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;
}
