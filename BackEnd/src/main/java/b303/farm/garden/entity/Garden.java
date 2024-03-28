package b303.farm.garden.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Garden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farm_id;
    private String farm_owner;
    private String farm_name;
    private String farm_address;
    private Long farm_size;
    private String farm_extra;
    private String farm_apply;
    private Double farm_lat;
    private Double farm_long;
}
