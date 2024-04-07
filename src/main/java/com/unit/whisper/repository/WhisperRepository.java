package com.unit.whisper.repository;


import com.unit.whisper.domain.whisper.Whisper;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WhisperRepository extends JpaRepository<Whisper, Long> {

    @Query(
            value =
                    "select * from whisper\n"
                            + "where latitude between ?1 and ?2 and longitude between ?3 and ?4 and TIMESTAMPDIFF(HOUR, create_date, now()) > 1;",
            nativeQuery = true)
    List<Whisper> findWithinMap(Double startX, Double endX, Double startY, Double endY);

    @Query(
            value =
                    "select *, (6371*acos(cos(radians(latitude))*cos(radians(?1))*cos(radians(?2)-radians(longitude))\n"
                            + "+sin(radians(latitude))*sin(radians(?1)))) AS distance\n"
                            + "from whisper\n"
                            + "where latitude between ?3 and ?4 and longitude between ?5 and ?6 and TIMESTAMPDIFF(HOUR, last_notification_date_time, now()) > 24\n"
                            //                            + "having distance <= 0.1\n"
                            + "having distance <= 1.0\n"
                            + "order by distance DESC;",
            nativeQuery = true)
    List<Whisper> findWithinRadius(
            Double curMemberX,
            Double curMemberY,
            Double startX,
            Double endX,
            Double startY,
            Double endY);

    Optional<Whisper> findByIdAndUserId(Long whisperId, Long userId);
}
