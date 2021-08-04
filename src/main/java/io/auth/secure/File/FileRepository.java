package io.auth.secure.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {

    @Query
    Optional<File> findById(Long id);


    @Query(value = "SELECT * FROM files f where f.name =?#{#name}",nativeQuery = true)
    Optional<File> findbyName(String name);

}
