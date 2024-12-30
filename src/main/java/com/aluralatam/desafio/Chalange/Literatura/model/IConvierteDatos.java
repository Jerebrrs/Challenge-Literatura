package com.aluralatam.desafio.Chalange.Literatura.model;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
