package yass.jouao.labx.DTOs;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class FournisseurDTO {
	public interface viewFournisseur {
	}
	public interface saveFournisseur {
	}
	@JsonView({viewFournisseur.class })
	private long id;
	@JsonView({viewFournisseur.class,saveFournisseur.class })
	private String name;
}