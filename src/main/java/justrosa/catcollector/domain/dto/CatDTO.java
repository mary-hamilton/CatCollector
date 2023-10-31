package justrosa.catcollector.domain.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import justrosa.catcollector.domain.User;
import justrosa.catcollector.domain.enums.CoatColours;
import justrosa.catcollector.domain.enums.CoatLength;
import justrosa.catcollector.domain.utility.Coordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CatDTO {

    private Integer id;

    private String primaryName;

    private List<String> names = new ArrayList<>();

    private List<CoatColours> coatColours = new ArrayList<>();

    private CoatLength coatLength;

    private String collectorUsername;
    private int timesSpotted;
    private List<Coordinates> spottedLocations = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<CoatColours> getCoatColours() {
        return coatColours;
    }

    public void setCoatColours(List<CoatColours> coatColours) {
        this.coatColours = coatColours;
    }

    public CoatLength getCoatLength() {
        return coatLength;
    }

    public void setCoatLength(CoatLength coatLength) {
        this.coatLength = coatLength;
    }

    public String getCollectorUsername() {
        return collectorUsername;
    }

    public void setCollectorUsername(String collectorUsername) {
        this.collectorUsername = collectorUsername;
    }

    public int getTimesSpotted() {
        return timesSpotted;
    }

    public void setTimesSpotted(int timesSpotted) {
        this.timesSpotted = timesSpotted;
    }

    public List<Coordinates> getSpottedLocations() {
        return spottedLocations;
    }

    public void setSpottedLocations(List<Coordinates> spottedLocations) {
        this.spottedLocations = spottedLocations;
    }
}