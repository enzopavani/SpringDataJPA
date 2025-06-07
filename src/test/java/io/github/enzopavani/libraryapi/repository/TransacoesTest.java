package io.github.enzopavani.libraryapi.repository;

import io.github.enzopavani.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService service;

    /**
     * Commit -> confirmar as alterações
     * Rollback -> desfazer as alterações
     */
    @Test
    void transacaoSimples() {
        service.executar();
    }

    @Test
    void transacaoEstadoManaged() {
        service.atualizacaoSemAtualizar();
    }
}
