package com.axioma.axiomatrainee.model.files;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "image_files")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "size")
    Long size;

    @Column(name = "name")
    String name;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "link")
    String link;
}
