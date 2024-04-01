package com.illia.project.ntilliaproject.infrastructure.entity;

import com.illia.project.ntilliaproject.commonTypes.LoanStatus;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanStatus status = LoanStatus.PENDING;

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}