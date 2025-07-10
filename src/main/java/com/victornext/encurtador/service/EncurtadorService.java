package com.victornext.encurtador.service;

import com.victornext.encurtador.model.Encurtador;
import com.victornext.encurtador.repository.EncurtadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EncurtadorService {
    private final EncurtadorRepository repository;



    //Metodo que encurta a URL
    public String encurtarURL(String OriginalURL){
        //Metodo gera o codigo unico
        String shortCode = gerarCodigoUnico();

        //E Logo Após povoa um novo Encurtador(URL encurtada e manda pro banco de dados)
        Encurtador encurtador = Encurtador.builder()
                .originalUrl(OriginalURL)
                .shortCode(shortCode)
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(encurtador);
        return shortCode;

    }

    //Gera um codigo unico com 6 caracteres e logo após verifica se é existente no banco de dados para não criar varios
    private String gerarCodigoUnico() {
        String codigo;

        do {
            //Gera um codigo curto baseado no ID (uuid)

            codigo = UUID.randomUUID().toString()
                    .replace("-", "")
                    .substring(0, 6);
        } while (repository.findByShortCode(codigo).isPresent());
                //Acima verifica se a URL já está presente dentro do banco de dados para não fazer varias

        return codigo;

    }

    //Aqui não tem muita explicação faz busca pela URL original
    public Optional<String> buscarOriginalUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .map(Encurtador::getOriginalUrl);
    }

}
