package io.auth.secure.File;

import io.auth.secure.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name="Files")
public class File {
    @SequenceGenerator(
            name= "image_sequence",
            sequenceName = "image_sequence",
            allocationSize=1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )

    private Long id;
    private String name;
    private String contentType;
    private Long size;
    @Lob
    private byte[] data;


    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;



}
