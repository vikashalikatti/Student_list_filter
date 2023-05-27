
package org.jsp.projet.studentSpecifications;

import org.jsp.projet.dto.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecifications {
	public static Specification<Student> withName(String name) {
		return (root, query, criteriaBuilder) -> {
			if (name != null) {
				return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
			}
			return null;
		};
	}

	public static Specification<Student> withTotalMarks(Integer totalMarks) {
		return (root, query, criteriaBuilder) -> {
			if (totalMarks != null) {
				return criteriaBuilder.equal(root.get("totalMarks"), totalMarks);
			}
			return null;
		};
	}
}
