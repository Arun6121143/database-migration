package com.jwt.authentication.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Data
@Getter
@Table(name = "data_tbl")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataCsv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Integer dataId;
    @Column(name = "data_type")
    private String dataType;
    @Column(name = "phone")
    private String phoneNumber;
}
