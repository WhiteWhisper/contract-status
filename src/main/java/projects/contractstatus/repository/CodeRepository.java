package projects.contractstatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projects.contractstatus.entity.Code;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    Code findOneByCode(int code);
}
