package com.unit.whisper.repository;


import com.unit.whisper.domain.whisper.Whisper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhisperRepository extends JpaRepository<Whisper, Long> {}
