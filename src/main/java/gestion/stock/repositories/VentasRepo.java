package gestion.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gestion.stock.entities.Venta;

public interface VentasRepo extends JpaRepository<Venta, Long> {
}
