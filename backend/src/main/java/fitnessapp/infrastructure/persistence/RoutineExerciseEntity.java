package fitnessapp.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "routine_exercises")
public class RoutineExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private RoutineEntity routine;

    @Column(name = "exercise_id")
    private Long exerciseId;

    @Column(name = "position_index")
    private Integer positionIndex;

    @Column(name = "default_sets")
    private Integer defaultSets;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoutineEntity getRoutine() {
        return routine;
    }

    public void setRoutine(RoutineEntity routine) {
        this.routine = routine;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Integer getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(Integer positionIndex) {
        this.positionIndex = positionIndex;
    }

    public Integer getDefaultSets() {
        return defaultSets;
    }

    public void setDefaultSets(Integer defaultSets) {
        this.defaultSets = defaultSets;
    }
}