package com.krakdev.jdbc.videojuegos.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.krakdev.jdbc.videojuegos.VideojuegoJdbc;
import com.krakdev.videojuegos.entidades.Videojuego;

@Service
public class ServicioVideojuegoJdbc {
    private static final Logger Log = LogManager.getLogger(ServicioVideojuegoJdbc.class);

    public Videojuego crear(Videojuego videojuego) {
        Log.info("Servicio crear");
        return VideojuegoJdbc.insertar(videojuego);
    }

    public List<Videojuego> listar() {
        Log.info("Servicio Listar");
        return VideojuegoJdbc.listar();
    }

    public Videojuego buscarPorCodigo(String codigo) {
        Log.info("Servicio buscar por codigo");
        return VideojuegoJdbc.buscar(codigo);
    }

    public Videojuego actualizar(String codigo, Videojuego videojuego) {
        Log.info("Servicio actualizar");
        return VideojuegoJdbc.actualizar(codigo, videojuego);
    }

    public boolean eliminar(String codigo) {
        Log.info("Servicio eliminar");
        return VideojuegoJdbc.eliminar(codigo);
    }
}