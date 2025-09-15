package co.edu.udem.ejemplodockercompose.service;

import co.edu.udem.ejemplodockercompose.model.Estudiante;
import co.edu.udem.ejemplodockercompose.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    public Estudiante create(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante update(Long id, Estudiante nuevo) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setNombres(nuevo.getNombres());
                    estudiante.setApellidos(nuevo.getApellidos());
                    estudiante.setEmail(nuevo.getEmail());
                    return estudianteRepository.save(estudiante);
                })
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));
    }
}
