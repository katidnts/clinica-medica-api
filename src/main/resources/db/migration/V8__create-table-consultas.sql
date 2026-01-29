CREATE TABLE consultas (

    id BIGSERIAL PRIMARY KEY,

    paciente_id BIGINT NOT NULL,

    medico_id BIGINT NOT NULL,

    data TIMESTAMP NOT NULL,

    CONSTRAINT fk_consulta_medico FOREIGN KEY (medico_id) REFERENCES medicos(id),

    CONSTRAINT fk_consulta_paciente FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);
