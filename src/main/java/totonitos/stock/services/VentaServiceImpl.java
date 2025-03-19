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
import totonitos.stock.services.VentaService;

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
        // Verificar que los productos existan y tengan suficiente stock
        for (DetalleVenta detalle : venta.getDetalles()) {
            Optional<Producto> productoOpt = productoRepository.findById(detalle.getProducto().getId());
            if (productoOpt.isEmpty()) {
                return new ResponseEntity<>("Producto no encontrado con ID: " + detalle.getProducto().getId(), HttpStatus.BAD_REQUEST);
            }
            Producto producto = productoOpt.get();
            if (producto.getCantidad() < detalle.getCantidad()) {
                return new ResponseEntity<>("Stock insuficiente para el producto: " + producto.getNombre(), HttpStatus.BAD_REQUEST);
            }
        }

        // Asignar la venta a cada detalle
        for (DetalleVenta detalle : venta.getDetalles()) {
            detalle.setVenta(venta);
        }

        // Guardar la venta (esto guardará también los detalles debido a CascadeType.ALL)
        Venta nuevaVenta = ventaRepository.save(venta);

        // Actualizar el stock de los productos
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
}