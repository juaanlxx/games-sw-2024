package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Jogo;
import application.record.JogoDTO;
import application.record.JogoListDTO;
import application.repository.JogoRepository;

@Service
public class JogoService {
    @Autowired
    private JogoRepository jogoRepo;

    public Iterable<JogoDTO> getAll() {
        return jogoRepo.findAll().stream().map(JogoDTO::new).toList();
    }

    public Iterable<JogoListDTO> getAllOnlyTitulo(){
        return jogoRepo.findAll().stream().map(JogoListDTO::new).toList();
    }

    public JogoDTO getOnebyId(long id){
        // return new JogoDTO(jogoRepo.findById(id).get());
        Optional<Jogo> resultado = jogoRepo.findById(id);
        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Jogo não encontrado"
            );
        }
        return new JogoDTO(resultado.get());
    }

    public JogoDTO add(JogoDTO jogo) {
        return new JogoDTO(jogoRepo.save(new Jogo(jogo)));
    }
}
