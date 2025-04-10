package med.voll.api.service;

import med.voll.api.dto.EnderecoDTO;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverteDados {

    public Medico converteMedicoDTO(MedicoDTO medicoDTO){
        Endereco endereco = converteEnderecoDTO(medicoDTO.endereco());
        return new Medico(null, medicoDTO.nome(), medicoDTO.email(), medicoDTO.telefone(), medicoDTO.crm(), medicoDTO.especialidade(), endereco);
    }

    public Page<MedicoDTO> converteListaMedicos(Page<Medico> medicos){
        return medicos
                .map(m-> new MedicoDTO(
                        m.getNome(), m.getEmail(), m.getTelefone(),
                        m.getCrm(), m.getEspecialidade(), converteEndereco(m.getEndereco()))
                );
    }

    private Endereco converteEnderecoDTO(EnderecoDTO enderecoDTO){
        return new Endereco(enderecoDTO.logradouro(),
                enderecoDTO.bairro(),enderecoDTO.cep(),
                enderecoDTO.cidade(), enderecoDTO.uf(),
                enderecoDTO.numero(), enderecoDTO.complemento());
    }

    private EnderecoDTO converteEndereco(Endereco endereco){
        return new EnderecoDTO(endereco.getLogradouro(),
                endereco.getBairro(), endereco.getCep(),
                endereco.getCidade(), endereco.getUf(),
                endereco.getNumero(),
                endereco.getComplemento());
    }

}
