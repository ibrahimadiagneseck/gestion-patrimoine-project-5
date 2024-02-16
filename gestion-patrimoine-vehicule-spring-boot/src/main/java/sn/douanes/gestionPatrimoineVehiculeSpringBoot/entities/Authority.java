package sn.douanes.gestionPatrimoineVehiculeSpringBoot.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "authority_id", nullable = false, updatable = false)
    private Long authorityId;

    private String code;

    private String name;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "utilisateur_id")
//    private Utilisateur utilisateur;


//    @ManyToMany(mappedBy = "authorities")
//    Set<Utilisateur> utilisateurs = new HashSet<>();

}
