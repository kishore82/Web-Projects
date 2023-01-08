package com.project.staff.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.staff.model.StaffDetails;
import com.project.staff.repository.StaffRepository;

@CrossOrigin (origins = "http://localhost:8081")
@RestController
@RequestMapping ("/api")
public class StaffController
{

    @Autowired
    StaffRepository staffRepo;

    @GetMapping ("/staff")
    public ResponseEntity<List<StaffDetails>> getAllStaff(@RequestParam (required = false) String name)
    {
        try
        {
            List<StaffDetails> staffs = new ArrayList<StaffDetails>();

            if (name == null)
                staffRepo.findAll().forEach(staffs::add);
            else
                staffRepo.findByNameContaining(name).forEach(staffs::add);

            if (staffs.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(staffs, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping ("/staff/{id}")
    public ResponseEntity<StaffDetails> getStaffById(@PathVariable ("id") long id)
    {
        Optional<StaffDetails> staffData = staffRepo.findById(id);

        if (staffData.isPresent())
        {
            return new ResponseEntity<>(staffData.get(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("/staff")
    public ResponseEntity<StaffDetails> createStaff(@RequestBody StaffDetails staff)
    {
        try
        {
            StaffDetails _staff = staffRepo.save(new StaffDetails(staff.getName(), staff.getEmailId(), staff.getPhoneNumber()));
            return new ResponseEntity<>(_staff, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping ("/staff/{id}")
    public ResponseEntity<StaffDetails> updateStaff(@PathVariable ("id") long id, @RequestBody StaffDetails staff)
    {
        Optional<StaffDetails> staffData = staffRepo.findById(id);

        if (staffData.isPresent())
        {
            StaffDetails _staff = staffData.get();
            _staff.setName(staff.getName());
            _staff.setEmailId(staff.getEmailId());
            _staff.setPhoneNumber(staff.getPhoneNumber());
            return new ResponseEntity<>(staffRepo.save(_staff), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/staff/{id}")
    public ResponseEntity<HttpStatus> deleteStaff(@PathVariable ("id") long id)
    {
        try
        {
            staffRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping ("/staffs")
    public ResponseEntity<HttpStatus> deleteAllStaffs()
    {
        try
        {
            staffRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
