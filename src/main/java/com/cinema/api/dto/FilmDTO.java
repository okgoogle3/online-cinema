package com.cinema.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmDTO {
    private long videoId;
    private String title;
    private String producer;
    private String date;
    private long boxOffice;
}
