package telran.courses.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.courses.jpa.entities.CourseJpa;

public interface CoursesRepository extends JpaRepository<CourseJpa, Integer> {

}
