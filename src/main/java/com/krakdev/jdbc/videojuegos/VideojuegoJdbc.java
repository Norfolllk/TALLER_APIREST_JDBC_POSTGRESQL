package com.krakdev.jdbc.videojuegos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakdev.jdbc.Conexion;
import com.krakdev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {
    private static final Logger Log = LogManager.getLogger(VideojuegoJdbc.class);

    public static Videojuego insertar(Videojuego videojuego) {
        Videojuego videojuegoCreado = null;

        Connection con = null;
        PreparedStatement ps = null;

        String sql = """
                insert into videojuegos
                (codigo, nombre, plataforma, precio, disponible, genero)
                values
                (?, ?, ?, ?, ?, ?);
                """;

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, videojuego.getCodigo());
            ps.setString(2, videojuego.getNombre());
            ps.setString(3, videojuego.getPlataforma());
            ps.setDouble(4, videojuego.getPrecio());
            ps.setBoolean(5, videojuego.isDisponible());
            ps.setString(6, videojuego.getGenero());

            int filas = ps.executeUpdate();

            videojuegoCreado = videojuego;

            Log.info("Filas creadas: " + filas);

        } catch (Exception e) {
            Log.error("Ocurrio un error al insertar" + e.getMessage());
        } finally {
            Conexion.cerrarConexiones(con, ps, null);
        }

        return videojuegoCreado;

    }
    
    public static List<Videojuego> listar() {
        List<Videojuego> videojuegos = new ArrayList<Videojuego>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = """
                select codigo, nombre, plataforma, precio, disponible, genero from videojuegos;
                """;

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Videojuego videojuego = new Videojuego();

                videojuego.setCodigo(rs.getString(1));
                videojuego.setNombre(rs.getString(2));
                videojuego.setPlataforma(rs.getString(3));
                videojuego.setPrecio(rs.getDouble(4));
                videojuego.setDisponible(rs.getBoolean(5));
                videojuego.setGenero(rs.getString(6));

                videojuegos.add(videojuego);
            }

            Log.info("Filas encontradas: " + videojuegos.size());

        } catch (Exception e) {
            Log.error("Ocurrio un error al listar " + e.getMessage());
        } finally {
            Conexion.cerrarConexiones(con, ps, rs);
        }

        return videojuegos;
    }
    
    public static Videojuego buscar(String codigo) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = """
                select codigo, nombre, plataforma, precio, disponible, genero from videojuegos
                where codigo = ?;
                """;

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, codigo);

            rs = ps.executeQuery();

            if (rs.next()) {
                Videojuego videojuego = new Videojuego();

                videojuego.setCodigo(rs.getString(1));
                videojuego.setNombre(rs.getString(2));
                videojuego.setPlataforma(rs.getString(3));
                videojuego.setPrecio(rs.getDouble(4));
                videojuego.setDisponible(rs.getBoolean(5));
                videojuego.setGenero(rs.getString(6));

                Log.info("Videojuego encontrado");

                return videojuego;
            }
        } catch (Exception e) {
            Log.error("Ocurrio un error al buscar por codigo: " + e.getMessage());
        } finally {
            Conexion.cerrarConexiones(con, ps, rs);
        }

        return null;
    }
    
    public static Videojuego actualizar(String codigo, Videojuego videojuego) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = """
                update videojuegos set
                nombre = ?,
                plataforma = ?,
                precio = ?,
                disponible = ?,
                genero = ?
                where codigo = ?;
                """;

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, videojuego.getNombre());
            ps.setString(2, videojuego.getPlataforma());
            ps.setDouble(3, videojuego.getPrecio());
            ps.setBoolean(4, videojuego.isDisponible());
            ps.setString(5, videojuego.getGenero());

            // condicion where
            ps.setString(6, codigo);

            int filas = ps.executeUpdate();

            Log.info("Filas actualizadas: " + filas);

            if (filas > 0) {
                Log.info("Video juego actualizdo con exito");
                return buscar(codigo);
            }

        } catch (Exception e) {
            Log.error("Ocurrio un error al actualiza videojuego: " + e.getMessage());
        } finally {
            Conexion.cerrarConexiones(con, ps, null);
        }

        return null;
    }
    
    public static boolean eliminar(String codigo) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = """
                delete from videojuegos
                where codigo = ?;
                """;

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, codigo);

            int filas = ps.executeUpdate();

            Log.info("Filas eliminadas: " + filas);

            if (filas > 0) {
                return true;
            }

        } catch (Exception e) {
            Log.error("Ocurrio un error al eliminar videojuego: " + e.getMessage());
        } finally {
            Conexion.cerrarConexiones(con, ps, null);
        }

        return false;
    }

}
