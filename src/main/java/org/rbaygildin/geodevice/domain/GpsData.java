package org.rbaygildin.geodevice.domain;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "gps_data")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class GpsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double latitude;
    private Double longitude;
    private Double radius;
    private OffsetDateTime time;

    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;
}