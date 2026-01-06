package com.TaskManagemente1.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="workFlows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class WorkFlow {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="workFlow",cascade=CascadeType.ALL,orphanRemoval=true)
	private List<WorkFlowTransaction>transactions=new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WorkFlowTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<WorkFlowTransaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
	
	

}
