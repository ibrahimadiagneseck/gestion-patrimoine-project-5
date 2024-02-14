package sn.douanes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.douanes.model.CorpsAgent;


@Repository
public interface CorpsAgentRepository extends JpaRepository<CorpsAgent, String> {

}