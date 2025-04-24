package med.voll.api.domain.repository;

import med.voll.api.domain.model.Medico;
import med.voll.api.domain.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    @Query("SELECT p FROM Paciente p WHERE ativo = 1")
    Page<Paciente> buscarAtivos(Pageable paginacao);
    Paciente findByIdEqualsAndAtivoEquals(Long id, Short ativo);
}
