package sn.douanes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "corps_agent")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorpsAgent {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(name = "code_corps_agent", nullable = false, updatable = false)
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "code_corps_agent", length = 3)
    private String codeCorpsAgent;


    @Column(name = "libelle_corps_agent", length = 100)
    private String libelleCorpsAgent;


}