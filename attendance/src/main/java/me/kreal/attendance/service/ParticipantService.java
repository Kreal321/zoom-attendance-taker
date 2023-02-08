package me.kreal.attendance.service;

import me.kreal.attendance.DTO.ParticipantDTO;
import me.kreal.attendance.domain.Participant;
import me.kreal.attendance.repo.ParticipantRepo;
import me.kreal.attendance.request.ParticipantUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepo participantRepo;

    @Autowired
    public ParticipantService(ParticipantRepo participantRepo) {
        this.participantRepo = participantRepo;
    }

    // Basic functions
    public Participant saveParticipant(Participant participant) {
        assert participant.getParticipantUuid() != null;
        return this.participantRepo.save(participant);
    }

    public Optional<Participant> findParticipantByUuid(String participantUuid) {
        return this.participantRepo.findById(participantUuid);
    }


    // Advance functions
    public Participant createParticipantFromDTO(ParticipantDTO participantDTO) {

        Participant participant = Participant.builder()
                .participantUuid(participantDTO.getParticipant_uuid())
                .email(participantDTO.getEmail())
                .userName(participantDTO.getUser_name())
                .build();

        return this.saveParticipant(participant);

    }

    public Participant findOrCreateFrom(ParticipantDTO participantDTO) {

        Optional<Participant> participantOptional = this.findParticipantByUuid(participantDTO.getParticipant_uuid());

        // Exist
        if (participantOptional.isPresent()) return participantOptional.get();

        // Not exist
        return this.createParticipantFromDTO(participantDTO);

    }

    public Participant saveParticipant(ParticipantUpdateRequest request, Participant p) {
        assert p != null;
        p.setEmail(request.getEmail());
        p.setUserName(request.getUserName());
        p.setFirstName(request.getFirstName());
        p.setLastName(request.getLastName());
        p.setPreferredName(request.getPreferredName());
        return this.saveParticipant(p);

    }







}
