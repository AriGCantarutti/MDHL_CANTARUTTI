package BaseDatos;

import Modelo.*;

public class BaseDatos{

    // Datos base
    private Rol[] roles = {
        new Rol("Tecnico", "Encargado de inspecciones"),
        new Rol("Supervisor", "Supervisa tareas")
    };

    private Empleado[] empleados = {
        new Empleado("Juan", "Perez", "juan@example.com", 123456789, roles[0]),
        new Empleado("Ana", "Lopez", "ana@example.com", 987654321, roles[1])
    };

    private Usuario[] usuarios = {
        new Usuario("jperez", 1234, empleados[0]),
        new Usuario("alopez", 5678, empleados[1])
    };

    private Sesion[] sesiones = {
        new Sesion("20240501", "20240501", usuarios[0])
    };

    private Estado[] estados = {
        new Estado("Activo", "Operativo"),
        new Estado("Fuera de servicio", "Mantenimiento")
    };

    private MotivosTipos[] tiposMotivo = {
        new MotivosTipos("Fallo electrico"),
        new MotivosTipos("Mantenimiento preventivo")
    };

    private MotivoFueraServicio[] motivos = {
        new MotivoFueraServicio("Sin energia", tiposMotivo[0]),
        new MotivoFueraServicio("Revision programada", tiposMotivo[1])
    };

    private CambioEstado[] cambiosEstado = {
        new CambioEstado("20240101", "20240301", estados[1], motivos[0]),
        new CambioEstado("20240401", "20240501", estados[1], motivos[1])
    };

    private EstacionSismologica[] estaciones = {
        new EstacionSismologica(101, "Cert001", "20230101", 345, 678, "Estacion Norte", 9999),
        new EstacionSismologica(102, "Cert002", "20230201", 123, 456, "Estacion Sur", 8888)
    };

    private Sismografo[] sismografos = {
        new Sismografo(1, "20220101", 54321, cambiosEstado[0], estados[0], estaciones[0]),
        new Sismografo(2, "20220202", 98765, cambiosEstado[1], estados[0], estaciones[1])
    };

    private OrdenInspeccion[] ordenes = {
        new OrdenInspeccion(1001, "20240520", "Todo OK", "20240521", "20240521", empleados[0], estados[0], estaciones[0]),
        new OrdenInspeccion(1002, "20240522", "Cambio de bateria", "20240523", "20240523", empleados[1], estados[0], estaciones[1]),
        new OrdenInspeccion(1003, "20240524", "Sin novedades", "20240525", "20240525", empleados[0], estados[0], estaciones[0]),
        new OrdenInspeccion(1004, "20240526", "Revisión completada", "20240527", "20240527", empleados[1], estados[0], estaciones[1])
    };

    // Getters
    public Rol[] getRoles() { return roles; }
    public Empleado[] getEmpleados() { return empleados; }
    public Usuario[] getUsuarios() { return usuarios; }
    public Sesion[] getSesiones() { return sesiones; }
    public Estado[] getEstados() { return estados; }
    public MotivosTipos[] getTiposMotivo() { return tiposMotivo; }
    public MotivoFueraServicio[] getMotivos() { return motivos; }
    public CambioEstado[] getCambiosEstado() { return cambiosEstado; }
    public EstacionSismologica[] getEstaciones() { return estaciones; }
    public Sismografo[] getSismografos() { return sismografos; }
    public OrdenInspeccion[] getOrdenes() { return ordenes; }
}