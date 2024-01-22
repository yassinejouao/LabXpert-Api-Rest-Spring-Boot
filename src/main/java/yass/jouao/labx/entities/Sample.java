package yass.jouao.labx.entities;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import yass.jouao.labx.enums.SampleStatus;
import yass.jouao.labx.enums.SampleType;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sample {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Patient patient;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SampleType type;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SampleStatus status = SampleStatus.WAITING;
	@Column(nullable = false)
	private LocalDateTime date;
	@OneToMany(mappedBy = "sample", fetch = FetchType.LAZY)
	private Collection<Analysis> analysis;
}
