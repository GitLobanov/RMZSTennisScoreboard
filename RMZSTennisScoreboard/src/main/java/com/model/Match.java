package com.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "player1")
    private Player player1;

    @ManyToOne()
    @JoinColumn(name = "player2")
    private Player player2;

    @ManyToOne()
    @JoinColumn(name = "winner")
    private Player winner;

}
