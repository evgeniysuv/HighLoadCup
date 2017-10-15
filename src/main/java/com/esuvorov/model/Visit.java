package com.esuvorov.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "VISITS")
public class Visit {
    @Id
    @Column(name = "VISIT_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @Column(name = "VISITED_AT")
    private Long visitedAt;

    @Column(name = "MARK")
    private Long mark;
}
