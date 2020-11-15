package com.aeox.auth.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "roles",    
    indexes = {
        @Index(name = "roles_name_index", columnList = "name", unique = true)
    }
)
public class Role extends AbstractEntity {
    @Column(unique = true)
    private String name;

    private String description;

	public static Optional<Role> findByName(String name) {
		final Role result = find("name = ?1", name).firstResult();
        return Optional.ofNullable(result);
	}
}
