package totonitos.stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import totonitos.stock.entities.DetalleVenta;
import totonitos.stock.entities.Producto;
import totonitos.stock.entities.Venta;
import totonitos.stock.repositories.DetalleVentaRepository;
import totonitos.stock.repositories.ProductoRepo;
import totonitos.stock.repositories.VentasRepo;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentasRepo ventaRepository;

    @Autowired
    private ProductoRepo productoRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public ResponseEntity<?> crearVenta(Venta venta) {
        for (DetalleVenta detalle : venta.getDetalles()) {
            Optional<Producto> productoOpt = productoRepository.findById(detalle.getProducto().getId());
            if (productoOpt.isEmpty()) {
                return new ResponseEntity<>("Producto no encontrado con ID: " + detalle.getProducto().getId(), HttpStatus.BAD_REQUEST);
            }
            Producto producto = productoOpt.get();
            if (producto.getCantidad() < detalle.getCantidad()) {
                return new ResponseEntity<>("Stock insuficiente para el producto: " + producto.getNombre(), HttpStatus.BAD_REQUEST);
            }
            if (detalle.getPrecioVenta() < producto.getPrecio()) {
                return new ResponseEntity<>("El precio de venta no puede ser menor al precio de costo para el producto: " + producto.getNombre(), HttpStatus.BAD_REQUEST);
            }
        }


        for (DetalleVenta detalle : venta.getDetalles()) {
            detalle.setVenta(venta);
        }


        Venta nuevaVenta = ventaRepository.save(venta);


        for (DetalleVenta detalle : nuevaVenta.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId()).get();
            producto.setCantidad(producto.getCantidad() - detalle.getCantidad());
            productoRepository.save(producto);
        }

        return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
    }

    @Override
    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<?> actualizarVenta(Long id, Venta venta) {
        Optional<Venta> ventaOpt = ventaRepository.findById(id);
        if (ventaOpt.isEmpty()) {
            return new ResponseEntity<>("Venta no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
        }
        Venta ventaExistente = ventaOpt.get();
        ventaExistente.setNombre_venta(venta.getNombre_venta());
        ventaExistente.setFecha(venta.getFecha());
        ventaExistente.setDetalles(venta.getDetalles());
        ventaRepository.save(ventaExistente);
        return new ResponseEntity<>(ventaExistente, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> eliminarVenta(Long id) {
        Optional<Venta> ventaOpt = ventaRepository.findById(id);
        if (ventaOpt.isEmpty()) {
            return new ResponseEntity<>("Venta no encontrada con ID: " + id, HttpStatus.NOT_FOUND);
        }
        ventaRepository.deleteById(id);
        return new ResponseEntity<>("Venta eliminada con éxito", HttpStatus.OK);
    }

    // Método para calcular la ganancia total de una venta
    public double calcularGananciaTotal(Long ventaId) {
        Optional<Venta> ventaOpt = ventaRepository.findById(ventaId);
        if (ventaOpt.isEmpty()) {
            throw new RuntimeException("Venta no encontrada con ID: " + ventaId);
        }
        Venta venta = ventaOpt.get();
        return venta.getDetalles().stream()
                .mapToDouble(detalle -> (detalle.getPrecioVenta() - detalle.getProducto().getPrecio()) * detalle.getCantidad())
                .sum();
    }
}