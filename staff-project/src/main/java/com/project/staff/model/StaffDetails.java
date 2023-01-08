package com.project.staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "staffdetails")
public class StaffDetails
{

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    int id;

    @Column (name = "name")
    String name;

    @Column (name = "emailId")
    private String emailId;

    @Column (name = "phoneNumber")
    private long phoneNumber;

    public StaffDetails()
    {

    }

    public StaffDetails(String name, String emailId, long phoneNumber)
    {
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }

    public long getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString()
    {
        return "Staff [id=" + id + ", name=" + name + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber + "]";
    }

}
