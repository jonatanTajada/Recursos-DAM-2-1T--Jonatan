package com.miempresa.tienda.sistema_gestion_tienda.servicios;

import com.miempresa.tienda.sistema_gestion_tienda.modelo.Categoria;
import java.util.List;

public interface CategoriaService {
	
    List<Categoria> obtenerTodas();
    Categoria obtenerPorId(int id);
    boolean guardar(Categoria categoria);
    boolean actualizar(Categoria categoria);
    boolean eliminar(int id);
    
}
