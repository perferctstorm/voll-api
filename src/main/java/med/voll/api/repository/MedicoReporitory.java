package med.voll.api.repository;

import med.voll.api.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoReporitory extends JpaRepository<Medico, Long> {
}
