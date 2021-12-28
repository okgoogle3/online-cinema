package com.cinema.api;

import com.cinema.api.dto.ComplaintDTO;
import com.cinema.repo.model.Complaint;
import com.cinema.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/complaints")
public class ComplaintController {
    private final ComplaintService complaintService;

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaint() {
        List<Complaint> complaints = complaintService.getAllComplaints();
        return ResponseEntity.ok(complaints);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintByID(@PathVariable long id) {
        try {
            Complaint complaint = complaintService.getComplaintByID(id);
            return ResponseEntity.ok(complaint);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createComplaint(@RequestBody ComplaintDTO complaintDTO) {
        final String theme = complaintDTO.getComplaintTheme();
        final String text = complaintDTO.getComplaintText();
        try {
            final long id = complaintService.createComplaint(theme, text);
            final String location = String.format("/complaints/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable long id, @PathVariable String username, @PathVariable String password) {
        try{
            ResponseEntity<Void> response =
                    new RestTemplate().exchange("http://localhost:8081/users/permission/"+username+"/"+password+"/"+"MODERATOR", HttpMethod.GET, null, Void.class);
            response.getStatusCode();
            if (response.getStatusCodeValue() == 200) {
                complaintService.updateComplaintStatus(id);
                return ResponseEntity.noContent().build();
            }
            throw new Exception("You don't have permission for this");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
