package kr.easw.lesson07.model.repository;

import kr.easw.lesson07.model.dto.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserDataEntity, Long> {
    Optional<UserDataEntity> findUserDataEntityByUserId(String userId);
}
