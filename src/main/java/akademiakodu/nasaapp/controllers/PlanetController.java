package akademiakodu.nasaapp.controllers;

import akademiakodu.nasaapp.models.Planet;
import akademiakodu.nasaapp.models.dtos.PlanetDto;
import akademiakodu.nasaapp.services.PlanetService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin //produkuje JSONa
@RequestMapping("/api/v1")
public class PlanetController {

    private PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/planets")
    public List<Planet> getPlanets(){
        return planetService.getPlanets();
    }
/*       todo sposób 1
    @GetMapping("/dto/planets")
    public List<PlanetDto> getPlanetsDto(){
        return planetService.getPlanetsDto();
    }

    @GetMapping("/dto/planets/{distance}")
    public List<PlanetDto> getPlanetsByDistanceFromSun(@PathVariable Long distance){
        return planetService.getPlanetsByDistanceFromSun(distance);
    }*/// todo sposób 2

    @GetMapping("/dto/planets/")
    public List<PlanetDto> getPlanetsByDistanceFromSun(@RequestParam(required = false) Long distance){
        if(distance != null && distance > 0) {
            return planetService.getPlanetsByDistanceFromSun(distance);
        } else {
            return planetService.getPlanetsDto();
        }
    }

    @PostMapping("/dto/planets")
    public Planet addPlanet(@RequestBody PlanetDto planetDto){
        return planetService.addPlanet(planetDto);
    }

    @PutMapping("/dto/planets")// Put aktualizuje a post dodaje nową
    public void updatePlanet(@RequestBody PlanetDto planetDto){
        planetService.updatePlanet(planetDto);
    }

    @DeleteMapping("/dto/planets/{planetName}")
    public void deletePlanet(@PathVariable String planetName){
        planetService.deletePlanet(planetName);
    }
}
