package com.emailsender.emailsender.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bug_list")
public class Bug {

    @Id
    @Column(name = "bug_list_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long bugListId;

    @Column(name = "sr_no")
    private String srNo;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "bug_id")
    private String bugId;

    @Column(name = "environment")
    private String environment;

    @Column(name = "summary")
    private String summary;

    @Column(name = "incident_type")
    private String incidentType;

    @Column(name = "reported_date")
    private String reportedDate;

    @Column(name = "date_fixed")
    private String dateFixed;

    @Column(name = "day_to_fix")
    private int dayToFix;

    @Column(name = "aging")
    private int aging;

    @Column(name = "assignee_to")
    private String assigneeTo;

    @Column(name = "assignee_to_email")
    private String assigneeToEmail;

    @Column(name = "status")
    private String status;

    @Column(name = "assigned_by")
    private String assignedBy;

    @Column(name = "remarks")
    private String remarks;
}
