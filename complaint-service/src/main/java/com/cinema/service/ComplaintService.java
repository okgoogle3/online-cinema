package com.cinema.service;

import com.cinema.repo.ComplaintRepo;
import com.cinema.repo.model.Complaint;
import com.cinema.repo.model.ComplaintStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class ComplaintService {
    public final ComplaintRepo complaintRepo;

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public Complaint getComplaintByID(long id) throws Exception {
        final Optional<Complaint> maybeComplaint = complaintRepo.findById(id);

        if (maybeComplaint.isEmpty()) {
            throw new Exception();
        } else {
            return maybeComplaint.get();
        }
    }

    public long createComplaint(String theme, String text) throws Exception {
        final Complaint complaint = new Complaint(theme, text, ComplaintStatus.OPENED);
        final Complaint savedComplaint = complaintRepo.save(complaint);
        return savedComplaint.getId();
    }

    public void updateComplaintStatus(long id) throws Exception {
        final Optional<Complaint> maybeComplaint = complaintRepo.findById(id);
        if (maybeComplaint.isEmpty()) throw new Exception("Complaint not found");
        Complaint complaint = maybeComplaint.get();
        complaint.setStatus(ComplaintStatus.CLOSED);
        complaintRepo.save(complaint);
    }

}
