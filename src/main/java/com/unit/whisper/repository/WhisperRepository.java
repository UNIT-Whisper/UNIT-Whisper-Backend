package com.unit.whisper.repository;


import com.unit.whisper.domain.whisper.Whisper;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WhisperRepository extends JpaRepository<Whisper, Long> {

    @Query(
            value =
                    "select * from letter\n"
                            + "where x between ?1 and ?2 and y between ?3 and ?4 and TIMESTAMPDIFF(HOUR, create_date, now()) > 1;",
            nativeQuery = true)
    List<Whisper> findWithinMap(Double startX, Double endX, Double startY, Double endY);

    @Query(
            value =
                    "select *, (6371*acos(cos(radians(x))*cos(radians(?1))*cos(radians(?2)-radians(y))\n"
                            + "+sin(radians(x))*sin(radians(?1)))) AS distance\n"
                            + "from letter\n"
                            + "where x between ?3 and ?4 and y between ?5 and ?6 and TIMESTAMPDIFF(HOUR, created_at, now()) > 1\n"
                            + "having distance <= 0.25\n"
                            + "order by distance DESC;",
            nativeQuery = true)
    List<Whisper> findWithinRadius(
            Double curMemberX,
            Double curMemberY,
            Double startX,
            Double endX,
            Double startY,
            Double endY);
}
