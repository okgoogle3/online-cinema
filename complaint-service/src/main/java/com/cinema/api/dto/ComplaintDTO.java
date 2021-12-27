package com.cinema.api.dto;

import com.cinema.repo.model.ComplaintStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComplaintDTO {
    private long id;
    //private long userId;
    private ComplaintStatus status;
    private String complaintTheme;
    private String complaintText;
}
