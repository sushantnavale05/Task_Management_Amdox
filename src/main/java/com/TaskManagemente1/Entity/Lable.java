package com.TaskManagemente1.Entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="lables")
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,nullable=false)
	private String lableName;
	
	public Lable(String lableName) {
		this.lableName=lableName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	
	
	
	
	
	
	
}

