package akademiakodu.nasaapp.commons.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name = "role")
@Setter
@Getter
@Entity
public class Role {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto
    @Column(name = "role_id")
    private Integer roleId;

    private String role;


    @JsonIgnore
    @ManyToMany(mappedBy="roles")
    private Set<UserApp> users = new HashSet<>();





}
