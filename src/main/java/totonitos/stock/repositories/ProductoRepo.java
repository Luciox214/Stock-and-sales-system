package totonitos.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import totonitos.stock.entities.Producto;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Long> {
}
