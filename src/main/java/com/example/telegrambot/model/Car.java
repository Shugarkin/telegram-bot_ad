package com.example.telegrambot.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "car_region")
    private int carRegion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Car(long id, String carNumber, int carRegion, User user) {
        this.id = id;
        this.carNumber = carNumber;
        this.carRegion = carRegion;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Транспорт " + "\n" +
                "ID транспорта=" + id +
                "Номер='" + carNumber + '\'' + "\n" +
                "Регон=" + carRegion + "\n" +
                "Пользователь=" + user.getFirstName();
    }


    //список неисправностей контретной машины
    //private Malfunctions listMalfunctions;
}
