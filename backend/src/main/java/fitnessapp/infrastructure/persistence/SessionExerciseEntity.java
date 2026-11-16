package fitnessapp.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "session_exercises")
public class SessionExerciseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private WorkoutSessionEntity session;

    @Column(name = "exercise_id")
    private Long exerciseId;

    @Column(name = "exercise_name")
    private String exerciseName;

    @Column(name = "default_sets")
    private Integer defaultSets;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkoutSessionEntity getSession() {
        return session;
    }

    public void setSession(WorkoutSessionEntity session) {
        this.session = session;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Integer getDefaultSets() {
        return defaultSets;
    }

    public void setDefaultSets(Integer defaultSets) {
        this.defaultSets = defaultSets;
    }
}