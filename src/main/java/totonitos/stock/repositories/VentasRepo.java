package totonitos.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import totonitos.stock.entities.Venta;

public interface VentasRepo extends JpaRepository<Venta, Long> {
}
