package yass.jouao.labx.DTOs;

import java.util.Collection;

import lombok.Data;

@Data
public class TestTypeDTO {
	private long id;
	private String name;
	private Double max;
	private Double min;
	private Collection<TestDTO> tests;
}
