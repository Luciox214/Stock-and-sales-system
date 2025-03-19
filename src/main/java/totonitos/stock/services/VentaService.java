package totonitos.stock.services;

import org.springframework.http.ResponseEntity;
import totonitos.stock.entities.Venta;

import java.util.List;

public interface VentaService {
    ResponseEntity crearVenta(Venta venta);
    List<Venta> obtenerVentas();
    Venta obtenerVentaPorId(Long id);
    ResponseEntity actualizarVenta(Long id, Venta venta);
    ResponseEntity eliminarVenta(Long id);
}