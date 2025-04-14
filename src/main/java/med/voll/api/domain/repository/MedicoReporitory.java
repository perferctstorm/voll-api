package med.voll.api.domain.repository;

import med.voll.api.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoReporitory extends JpaRepository<Medico, Long> {
    @Query("SELECT m FROM Medico m WHERE ativo = 1")
    Page<Medico> buscarAtivos(Pageable paginacao);
    Medico findByIdEqualsAndAtivoEquals(Long id, Short ativo);
}
