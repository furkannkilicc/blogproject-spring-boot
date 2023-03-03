package com.example.myblogv1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "user")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    @JsonIgnore
     String password;
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    private Date created;
//


//      @Temporal(TemporalType.TIMESTAMP)
//      Date createDate;

    @Enumerated(EnumType.STRING)
    @Column(name="roles")
    Role  roles;


   @OneToMany(fetch = FetchType.LAZY , mappedBy = "user")
   List<Post> posts;

}
