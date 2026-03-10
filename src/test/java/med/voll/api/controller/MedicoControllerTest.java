package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.medico.CadastroDadosMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CadastroDadosMedico> cadastroDadosMedicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJson;

    private DadosEndereco endereco() {
        return new DadosEndereco("Rua das flores",
                "52",
                "902",
                "Centro",
                "22200005",
                "Rio de Janeiro",
                "RJ");
    }

    @Test
    @DisplayName("Deveria devolver 201 ao cadastrar médico com dados válidos")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {

        var cadastroDadosMedico = new CadastroDadosMedico(
                "Marcelo da Costa",
                "marcelodacosta@clinicamedica.com",
                "04299987653",
                "002033",
                Especialidade.ORTOPEDIA,
                endereco()
        );

        var response = mvc
                .perform(
                        post("/medicos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(cadastroDadosMedicoJson.write(cadastroDadosMedico)
                                        .getJson())
                )
                .andReturn().getResponse();

        var dadosDetalhamento = new DadosDetalhamentoMedico(
                1L,
                "Marcelo da Costa",
                "marcelodacosta@clinicamedica.com",
                "04299987653",
                "002033",
                Especialidade.ORTOPEDIA,
                endereco()
        );

        var jsonEsperado = dadosDetalhamentoMedicoJson.write(
                dadosDetalhamento
        ).getJson();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    @Test
    @DisplayName("Deveria devolver 400 quando dados obrigatórios não forem informados")
    @WithMockUser
    void cadastrar_cenario2() throws Exception {

        var response = mvc.perform(
                post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cadastroDadosMedicoJson.write(new CadastroDadosMedico("Marcelo da Costa", "marcelodacosta@clinicamedica.com", "04299987653", null, null, endereco()))
                                .getJson()
                        )

        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
}