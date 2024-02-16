package sn.douanes.gestionstockpostgres.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.douanes.gestionstockpostgres.entities.LieuStockageVehicule;

@Repository
public interface LieuStockageVehiculeRepository extends JpaRepository<LieuStockageVehicule, String> {
}
