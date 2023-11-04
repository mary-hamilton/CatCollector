package justrosa.catcollector.domain;

import jakarta.persistence.*;
import justrosa.catcollector.domain.dto.CatDTO;
import justrosa.catcollector.domain.enums.CoatColours;
import justrosa.catcollector.domain.enums.CoatLength;
import justrosa.catcollector.domain.utility.Coordinates;

import java.util.List;


@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String primaryName;

    @ElementCollection
    private List<String> names;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<CoatColours> coatColours;

    private CoatLength coatLength;

    @ManyToOne
    private User collector;

    private int timesSpotted;

    @ElementCollection
    private List<Coordinates> spottedLocations;

    public Cat(String primaryName, List<CoatColours> coatColours, CoatLength coatLength, User collector, List<Coordinates> spottedLocations) {
        this.primaryName = primaryName;
        this.coatColours = coatColours;
        this.coatLength = coatLength;
        this.collector = collector;
        this.spottedLocations = spottedLocations;
    }

    public Cat() {
    }

    public CatDTO dto() {
        CatDTO catDTO = new CatDTO();
        catDTO.setId(this.id);
        catDTO.setPrimaryName(this.primaryName);
        catDTO.setNames(this.names);
        catDTO.setCoatColours(this.coatColours);
        catDTO.setCoatLength(this.coatLength);
        catDTO.setTimesSpotted(this.timesSpotted);
        catDTO.setSpottedLocations(this.spottedLocations);
        return catDTO;
    }

    public CatDTO dtoWithCollector() {
        CatDTO catDTO = this.dto();
        catDTO.setCollectorUsername(this.collector.getUsername());
        return catDTO;
    }

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

    public User getCollector() {
        return collector;
    }

    public void setCollector(User collector) {
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
