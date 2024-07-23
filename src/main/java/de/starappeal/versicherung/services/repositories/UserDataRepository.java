package de.starappeal.versicherung.services.repositories;

import de.starappeal.versicherung.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {}
