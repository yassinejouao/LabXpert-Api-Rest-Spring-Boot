package yass.jouao.labx.serviceImpl.Mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import yass.jouao.labx.DTOs.ReagentDTO;
import yass.jouao.labx.entities.Reagent;

@Service
public class ReagentMapper {
    public ReagentDTO fromReagentToReagentDTO(Reagent reagent) {
        ReagentDTO reagentDTO = new ReagentDTO();
        BeanUtils.copyProperties(reagent, reagentDTO);
        return reagentDTO;
    }
    public Reagent fromReagentDTOToReagent(ReagentDTO reagentDTO) {
        Reagent reagent = new Reagent();
        BeanUtils.copyProperties(reagentDTO, reagent);
        return reagent;
    }
}
