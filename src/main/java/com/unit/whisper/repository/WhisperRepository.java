package com.unit.whisper.repository;


import com.unit.whisper.domain.whisper.Whisper;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
                    "SELECT\n"
                            + "*\n"
                            + "FROM locationMap\n"
                            + "WHERE\n"
                            + "(6371*acos(cos(radians(37.579651))*cos(radians(:curMemberX))*cos(radians(:curMemberY)\n"
                            + "- radians(126.977041))+sin(radians(37.579651))*sin(radians(:curMemberX)))) < 20\n"
                            + "order by (6371*acos(cos(radians(37.579651))*cos(radians(:curMemberX))*cos(radians(:curMemberY)\n"
                            + "- radians(126.977041))+sin(radians(37.579651))*sin(radians(:curMemberX)))) asc\n"
                            + "limit 0, 30",
            nativeQuery = true)
    List<Whisper> findWithinRadius(
            @Param("curMemberX") Double curMemberX, @Param("curMemberY") Double curMemberY);

    Optional<Whisper> findByIdAndUserId(Long whisperId, Long userId);
}
