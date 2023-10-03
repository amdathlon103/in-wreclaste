package ru.ama.inwreaclaste.database;

import ru.ama.inwreaclaste.database.entities.BaseUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser, String> {

    Optional<BaseUser> findByLogin( String login );
    Optional<BaseUser> findByEmail( String email );
}
