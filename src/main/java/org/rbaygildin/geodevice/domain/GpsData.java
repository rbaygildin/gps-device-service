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
    @SequenceGenerator(name = "gps_data_id_seq", sequenceName = "gps_data_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gps_data_id_seq")
    @Column(name = "id")
    private Integer gpdDataId;

    private Double latitude;
    private Double longitude;
    private Double radius;
    private OffsetDateTime time;

    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id", nullable = false)
    private Device device;
}