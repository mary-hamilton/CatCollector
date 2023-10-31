package justrosa.catcollector.domain;

import jakarta.persistence.*;
import justrosa.catcollector.domain.enums.CoatColours;
import justrosa.catcollector.domain.enums.CoatLength;
import justrosa.catcollector.domain.utility.Coordinates;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String primaryName;

    @ElementCollection
    private Set<String> names = new HashSet<>();

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<CoatColours> coatColours = new HashSet<>();

    private CoatLength coatLength;

    @ManyToOne
    private User collector;

    private int timesSpotted;

    @ElementCollection
    private List<Coordinates> spottedLocations = new ArrayList<>();
}
