package med.voll.api.domain.service;

import med.voll.api.domain.dto.AtualizarMedicoDTO;
import med.voll.api.domain.dto.EnderecoDTO;
import med.voll.api.domain.dto.ListarMedicoDTO;
import med.voll.api.domain.dto.MedicoDTO;
import med.voll.api.domain.model.Endereco;
import med.voll.api.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ConverteDados {

    public AtualizarMedicoDTO converteParaAtualizarMedicoDTO(Medico medico) {
        return new AtualizarMedicoDTO(medico.getId(), medico.getNome(),
                medico.getTelefone(), converteParaEnderecoDTO(medico.getEndereco()));
    }

    private EnderecoDTO converteParaEnderecoDTO(Endereco endereco) {
        return new EnderecoDTO(endereco.getLogradouro(),
                endereco.getBairro(), endereco.getCep(),
                endereco.getCidade(), endereco.getUf(),
                endereco.getNumero(),
                endereco.getComplemento());
    }

    public Medico converteParaMedico(ListarMedicoDTO medicoDTO){
        return new Medico(null, medicoDTO.nome(), medicoDTO.email(), medicoDTO.telefone(),
                medicoDTO.crm(), medicoDTO.especialidade(), converteParaEndereco(medicoDTO.endereco()));
    }

    private Endereco converteParaEndereco(EnderecoDTO enderecoDTO){
        return new Endereco(enderecoDTO.logradouro(),
                enderecoDTO.bairro(),enderecoDTO.cep(),
                enderecoDTO.cidade(), enderecoDTO.uf(),
                enderecoDTO.numero(), enderecoDTO.complemento());
    }

    public Page<ListarMedicoDTO> converteParaListarMedicoDTO(Page<Medico> medicos){
        return medicos
            .map(m-> new ListarMedicoDTO(
                    m.getId(), m.getNome(), m.getEmail(), m.getTelefone(),
                    m.getCrm(), m.getEspecialidade(), converteParaEnderecoDTO(m.getEndereco()))
            );
    }

    public MedicoDTO converteParaMedicoDTO(Medico medico) {
        return new MedicoDTO(medico.getId(), medico.getNome(), medico.getEmail(),
                medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(),
                converteParaEnderecoDTO(medico.getEndereco()));
    }
}
