package br.com.server.location.database.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "TB_CITY")
@GenericGenerator(
        name = "SEQ_CITY",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_CITY"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CITY")
    @Column(name = "ID_CITY", nullable = false)
    private Long id;

    @Column(name = "DSC_NAME", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "ID_STATE")
    private StateEntity state;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(
            name = "TB_CITY_ADDRESSES",
            joinColumns = @JoinColumn(name = "ID_CITY",
                                      referencedColumnName = "ID_CITY"),
            inverseJoinColumns = @JoinColumn(name = "ID_ADDRESS",
                                             referencedColumnName = "ID_ADDRESS"))
    private List<AddressEntity> addresses;

}