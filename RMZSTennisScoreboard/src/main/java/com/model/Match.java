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

    @Column(name = "player1")
    @ManyToOne()
    @JoinColumn(name = "id")
    private Player player1;

    @Column(name = "player2")
    @ManyToOne()
    @JoinColumn(name = "id")
    private Player player2;

    @Column(name = "winner")
    @ManyToOne()
    @JoinColumn(name = "id")
    private Player winner;

}
