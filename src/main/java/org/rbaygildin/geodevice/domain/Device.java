package org.rbaygildin.geodevice.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "devices")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "device")
    private List<GpsData> gpsDataList;
}