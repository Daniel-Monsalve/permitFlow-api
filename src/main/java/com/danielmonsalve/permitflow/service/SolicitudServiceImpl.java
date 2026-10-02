package com.danielmonsalve.permitflow.service;

import com.danielmonsalve.permitflow.dto.EstadoUpdateDTO;
import com.danielmonsalve.permitflow.dto.SolicitudRequestDTO;
import com.danielmonsalve.permitflow.dto.SolicitudResponseDTO;
import com.danielmonsalve.permitflow.entity.EstadoSolicitud;
import com.danielmonsalve.permitflow.entity.Solicitud;
import com.danielmonsalve.permitflow.entity.Usuario;
import com.danielmonsalve.permitflow.repository.SolicitudRepository;
import com.danielmonsalve.permitflow.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {

    private final UsuarioRepository usuarioRepository;
    private final SolicitudRepository solicitudRepository;

    @Override
    public SolicitudResponseDTO crearSolicitud(SolicitudRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Solicitud nuevaSolicitud = Solicitud.builder()
                .tipoLicencia(request.getTipoLicencia())
                .descripcion(request.getDescripcion())
                .estado(EstadoSolicitud.RADICADO)
                .usuario(usuario)
                .build();

        Solicitud solicitudGuardada = solicitudRepository.save(nuevaSolicitud);
        return mapToResponseDTO(solicitudGuardada);
    }

    @Override
    public List<SolicitudResponseDTO> obtenerTodasLasSolicitudes() {
        return solicitudRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
    private SolicitudResponseDTO mapToResponseDTO(Solicitud solicitud) {
        return SolicitudResponseDTO.builder()
                .id(solicitud.getId())
                .tipoLicencia(solicitud.getTipoLicencia())
                .descripcion(solicitud.getDescripcion())
                .estado(solicitud.getEstado())
                .nombreSolicitante(solicitud.getUsuario().getNombreCompleto())
                .fechaRadicacion(solicitud.getFechaRadicacion())
                .build();
    }

    @Override
    public SolicitudResponseDTO cambiarEstadoSolicitud(Long id, EstadoUpdateDTO request) {

        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + id));

        EstadoSolicitud estadoActual = solicitud.getEstado();
        EstadoSolicitud nuevoEstado = request.getNuevoEstado();

        if (estadoActual == EstadoSolicitud.APROBADO || estadoActual == EstadoSolicitud.RECHAZADO) {
            throw new RuntimeException("No se puede cambiar el estado de una solicitud ya aprobada o rechazada.");
        }

        if (estadoActual == EstadoSolicitud.RADICADO &&
                (nuevoEstado == EstadoSolicitud.APROBADO || nuevoEstado == EstadoSolicitud.RECHAZADO)) {
            throw new RuntimeException("La solicitud debe pasar primero por el estado EN_REVISION antes de ser resuelta.");
        }

        solicitud.setEstado(nuevoEstado);
        Solicitud solicitudActualizada = solicitudRepository.save(solicitud);
        return mapToResponseDTO(solicitudActualizada);
    }
}
