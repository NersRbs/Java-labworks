package ru.gunkin.abstractions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gunkin.models.Owner;

@Repository
public interface OwnersRepository extends JpaRepository<Owner, Long> {
}
