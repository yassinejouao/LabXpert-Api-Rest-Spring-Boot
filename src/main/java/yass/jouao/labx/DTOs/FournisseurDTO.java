package yass.jouao.labx.DTOs;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.enums.StatusField;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurDTO {
	public interface viewFournisseur {
	}

	public interface saveFournisseur {
	}

	@JsonView({ viewFournisseur.class })
	private long id;
	@JsonView({ viewFournisseur.class, saveFournisseur.class })
	private String name;
	@JsonView({ viewFournisseur.class, saveFournisseur.class })
	private StatusField status;
}