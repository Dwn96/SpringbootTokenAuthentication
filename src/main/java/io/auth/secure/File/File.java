package io.auth.secure.File;

import io.auth.secure.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String contentType;
    @Column(nullable = false)
    private Long size;
    @Column(nullable = false)
    private byte[] data;


    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "owner_id"
    )

    private User user;

    public File(String name, String contentType, Long size, byte[] data, User user) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
        this.user = user;
    }

    public File(String name, String contentType, Long size, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
    }
}
