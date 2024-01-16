package yass.jouao.labx.DTOs;

import java.time.LocalDate;
import java.util.Collection;

import lombok.Data;

@Data
public class ReagentDTO {
	private long id;
	private String name;
	private String Description;
	private Long stock;
	private double price;
	private LocalDate expirationDate;
	private Collection<TestReagentDTO> testReagents;

}
