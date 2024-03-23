package com.lalitha.busReservationBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="passenger")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private  String passengerName;
    @Column(nullable = false)
    private Long aadharId;
    @Column(nullable = false)
    private Long contactNo;
    @Column(nullable = false,unique = true)
    private  String email;
    private int age;
    @Column(name="TicketCancelled")
    public boolean cancelStat;
    @Column(name="JourneyStatus")
    private boolean journeyStat;



}
