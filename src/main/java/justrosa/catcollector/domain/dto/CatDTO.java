package justrosa.catcollector.domain.dto;

import justrosa.catcollector.domain.enums.CoatColours;
import justrosa.catcollector.domain.enums.CoatLength;
import justrosa.catcollector.domain.utility.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class CatDTO {

    private Integer id;

    private String primaryName;

    private String concatId;

    private List<String> names = new ArrayList<>();

    private List<CoatColours> coatColours = new ArrayList<>();

    private CoatLength coatLength;

    private UserDTO collector;
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

    public String getConcatId() {
        return concatId;
    }

    public void setConcatId(String concatId) {
        this.concatId = concatId;
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

    public UserDTO getCollector() {
        return collector;
    }

    public void setCollector(UserDTO collector) {
        this.collector = collector;
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