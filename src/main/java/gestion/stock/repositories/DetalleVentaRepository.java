package gestion.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gestion.stock.entities.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}