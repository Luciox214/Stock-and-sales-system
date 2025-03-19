package totonitos.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import totonitos.stock.entities.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}