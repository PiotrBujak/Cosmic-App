package akademiakodu.nasaapp.models.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanetDto {
    private String planetName;
    private long distanceFromSun;
    private double oneWayLightTimeToTheSun;
    private long lengthOfYear;
    private String planetType;
    private String planetInfo;
    private String planetImage;
}
