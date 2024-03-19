package b303.farm.crop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "image_url")
    private String imageUrl;

    private String category;

    private String temperature;

    private String difficulty;

    private String sunshine;

    @Column(name = "grow_time")
    private String growTime;

    private String height;

    @Column(name = "water_period")
    private String waterPeriod;

    private String humidity;

    @Column(name = "is_hydroponics")
    private Boolean isHydroponics;

    @Column(name = "grow_start")
    private String growStart;

    @Column(name = "water_exit")
    private String waterExit;

    @Column(name = "crop_info")
    private String cropInfo;

    @Column(name = "water_period_info")
    private String waterPeriodInfo;

    @Column(name = "sunshine_info")
    private String sunshineInfo;

    @Column(name = "humidity_info")
    private String humidityInfo;

    @Column(name = "temperature_info")
    private String temperatureInfo;

    @Column(name = "effect_info")
    private String effectInfo;

    private String link;

    @Column(name = "grow_info")
    private String growInfo;
}
