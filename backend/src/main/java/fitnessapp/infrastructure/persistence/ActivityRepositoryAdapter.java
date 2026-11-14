package fitnessapp.infrastructure.persistence;

import fitnessapp.domain.Activity;
import fitnessapp.domain.ActivityRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * Adapter implementing the domain {@link ActivityRepository} port on top of
 * Spring Data JPA. Numeric-id and String-id translation happens here.
 */
@Repository
public class ActivityRepositoryAdapter implements ActivityRepository {

    private final ActivityJpaRepository jpaRepository;

    public ActivityRepositoryAdapter(ActivityJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Activity save(Activity activity) {
        ActivityEntity saved = jpaRepository.save(toEntity(activity));
        return toDomain(saved);
    }

    @Override
    public Optional<Activity> findById(String id) {
        Long numericId = parseId(id);
        if (numericId == null) {
            return Optional.empty();
        }
        return jpaRepository.findById(numericId).map(ActivityRepositoryAdapter::toDomain);
    }

    @Override
    public List<Activity> findAll() {
        return jpaRepository.findAll().stream()
                .map(ActivityRepositoryAdapter::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        Long numericId = parseId(id);
        if (numericId == null) {
            return;
        }
        jpaRepository.deleteById(numericId);
    }

    @Override
    public boolean existsById(String id) {
        Long numericId = parseId(id);
        return numericId != null && jpaRepository.existsById(numericId);
    }

    private static ActivityEntity toEntity(Activity activity) {
        ActivityEntity entity = new ActivityEntity();
        if (activity.getId() != null) {
            Long numericId = parseId(activity.getId());
            if (numericId != null) {
                entity.setId(numericId);
            }
        }
        entity.setUserId(parseUserId(activity.getUserId()));
        entity.setType(activity.getType());
        entity.setDuration(activity.getDuration());
        entity.setIntensity(activity.getIntensity());
        return entity;
    }

    private static Long parseUserId(String userId) {
        try {
            return Long.parseLong(userId);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Long parseId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Activity toDomain(ActivityEntity entity) {
        Activity activity = new Activity();
        activity.setId(String.valueOf(entity.getId()));
        activity.setUserId(String.valueOf(entity.getUserId()));
        activity.setType(entity.getType());
        activity.setDuration(entity.getDuration());
        activity.setIntensity(entity.getIntensity());
        return activity;
    }
}
