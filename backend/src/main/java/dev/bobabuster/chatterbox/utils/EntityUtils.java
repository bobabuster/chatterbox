package dev.bobabuster.chatterbox.utils;

import dev.bobabuster.chatterbox.management.UserManager;
import dev.bobabuster.chatterbox.model.Identifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Objects;

public final class EntityUtils {

    private EntityUtils() {}

    public static <T extends Identifiable<ID>, ID> T saveOrGetExisting(T entity, JpaRepository<T, ID> repo) {
        Objects.requireNonNull(entity, "entity must not be null");
        Objects.requireNonNull(repo, "repository must not be null");

        ID id = entity.getId();
        if (id == null || !repo.existsById(id)) {
            return repo.save(entity);
        } else {
            return repo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        }
    }
}
