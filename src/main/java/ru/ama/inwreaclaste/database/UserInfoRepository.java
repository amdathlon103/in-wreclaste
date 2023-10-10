package ru.ama.inwreaclaste.database;

import ru.ama.inwreaclaste.database.entities.UserInfoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, String> {
}
