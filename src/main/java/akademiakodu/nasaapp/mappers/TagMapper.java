package akademiakodu.nasaapp.mappers;

import akademiakodu.nasaapp.commons.Mapper;
import akademiakodu.nasaapp.models.Planet;
import akademiakodu.nasaapp.models.Tag;
import akademiakodu.nasaapp.models.dtos.TagDto;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TagMapper implements Mapper<Tag, TagDto> {

    @Override
    public TagDto map(Tag from) {
        Set<String> planets = from
                .getPlanets()
                .stream()
                .map(planetsToStrings.INSTANCE)
                .collect(Collectors.toSet());
        return TagDto.builder()
                .title(from.getTitle())
                .planets(planets)
                .build();
    }

    @Override
    public Tag revers(TagDto from) {
        return null;
    }

    private enum planetsToStrings implements Function<Planet, String> {
        INSTANCE;

        @Override
        public String apply(Planet planet) {
            return planet.getPlanetName();
        }
    }
}
