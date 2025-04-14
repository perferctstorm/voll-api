package med.voll.api.domain.model;

public enum Especialidade {
    ORTOPEDIA,
    CARDIOLOGIA,
    GINECOLOGIA,
    DERMATOLOGIA;

    public static Especialidade fromString(String especialidade){
        for(Especialidade esp:Especialidade.values()){
            if(esp.toString().equalsIgnoreCase(especialidade.trim())){
                return esp;
            }
        }
        throw new IllegalArgumentException("Nenhuma especialidade encontrada para a string fornecida: " + especialidade);
    }
}
