package me.kreal.attendance.service;

import lombok.Synchronized;
import me.kreal.attendance.DTO.ParticipantDTO;
import me.kreal.attendance.domain.Participant;
import me.kreal.attendance.repo.ParticipantRepo;
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
        assert participantUuid != null;
        return this.participantRepo.findByParticipantUuid(participantUuid);
    }


    // Advance functions
    public Participant createParticipantFromDTO(ParticipantDTO participantDTO) {

        Participant participant = Participant.builder()
                .participantUuid(participantDTO.getParticipant_user_id())
                .email(participantDTO.getEmail())
                .userName(participantDTO.getUser_name())
                .build();

        return this.saveParticipant(participant);

    }

    @Synchronized
    public Participant findOrCreateFrom(ParticipantDTO participantDTO) {

        // zoom user
        Optional<Participant> participantOptional = this.findParticipantByUuid(participantDTO.getParticipant_user_id());

        // Exist
        if (participantOptional.isPresent()) return participantOptional.get();

        // Not exist
        return this.createParticipantFromDTO(participantDTO);

    }

}
