package com.illia.project.ntilliaproject.infrastructure.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "loans", schema = "library")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loanID")
    private int loanID;

    @ManyToOne
    @JoinColumn(name = "bookID", referencedColumnName = "bookID")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private UserEntity user;

    @Basic
    @Column(name = "loanDate")
    @Temporal(TemporalType.DATE)
    private Date loanDate;

    @Basic
    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Basic
    @Column(name = "returnDate")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

}