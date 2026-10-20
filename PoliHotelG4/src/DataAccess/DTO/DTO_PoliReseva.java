package DataAccess.DTO;

import java.math.BigDecimal;

public class DTO_PoliReseva {

    private Integer idReserva;
    private Integer idPersona;
    private Integer idHabitacion;
    private String Nombre; 
    private String Apellido;
    private String Telefono;
    private String TipoHabitacion;
    private BigDecimal PrecioHabitacion;
    private String FechaIngreso;
    private String FechaSalida;
    private String Estado;

    public DTO_PoliReseva(Integer idReserva, Integer idPersona, Integer idHabitacion, String nombre, String apellido, String telefono, String tipoHabitacion, BigDecimal precioHabitacion, String fechaIngreso, String fechaSalida, String estado) {
        this.idReserva = idReserva;
        this.idPersona = idPersona;
        this.idHabitacion = idHabitacion;
        Nombre = nombre;
        Apellido = apellido;
        Telefono = telefono;
        TipoHabitacion = tipoHabitacion;
        PrecioHabitacion = precioHabitacion;
        FechaIngreso = fechaIngreso;
        FechaSalida = fechaSalida;
        Estado = estado;
    }

    public DTO_PoliReseva(Integer idReserva, String nombre, String apellido, String telefono, String tipoHabitacion, BigDecimal precioHabitacion, String fechaIngreso, String fechaSalida, String estado) {
        this(idReserva, null, null, nombre, apellido, telefono, tipoHabitacion, precioHabitacion, fechaIngreso, fechaSalida, estado);
    }

    public DTO_PoliReseva(Integer idReserva, String nombre, String apellido, String telefono, String tipoHabitacion, Integer precioHabitacion, String fechaIngreso, String fechaSalida, String estado) {
        this(idReserva, null, null, nombre, apellido, telefono, tipoHabitacion, BigDecimal.valueOf(precioHabitacion), fechaIngreso, fechaSalida, estado);
    }

    public DTO_PoliReseva() {
    }

    public DTO_PoliReseva(String Nombre, String Apellido, String Telefono, String TipoHabitacion, BigDecimal PrecioHabitacion, String FechaIngreso, String FechaSalida) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.TipoHabitacion = TipoHabitacion;
        this.PrecioHabitacion = PrecioHabitacion;
        this.FechaIngreso = FechaIngreso;
        this.FechaSalida = FechaSalida;
    }

    public DTO_PoliReseva(String Nombre, String Apellido, String Telefono, String TipoHabitacion, Integer PrecioHabitacion, String FechaIngreso, String FechaSalida) {
        this(Nombre, Apellido, Telefono, TipoHabitacion, BigDecimal.valueOf(PrecioHabitacion), FechaIngreso, FechaSalida);
    }

    public Integer getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }
    public Integer getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    public Integer getIdHabitacion() {
        return idHabitacion;
    }
    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    public String getTelefono() {
        return Telefono;
    }
    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
    public String getTipoHabitacion() {
        return TipoHabitacion;
    }
    public void setTipoHabitacion(String tipoHabitacion) {
        TipoHabitacion = tipoHabitacion;
    }
    public BigDecimal getPrecioHabitacion() {
        return PrecioHabitacion;
    }
    public void setPrecioHabitacion(BigDecimal precioHabitacion) {
        PrecioHabitacion = precioHabitacion;
    }
    public void setPrecioHabitacion(Integer precioHabitacion) {
        PrecioHabitacion = BigDecimal.valueOf(precioHabitacion);
    }
    public String getFechaIngreso() {
        return FechaIngreso;
    }
    public void setFechaIngreso(String fechaIngreso) {
        FechaIngreso = fechaIngreso;
    }
    public String getFechaSalida() {
        return FechaSalida;
    }
    public void setFechaSalida(String fechaSalida) {
        FechaSalida = fechaSalida;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    
  @Override
    public String toString(){
        return getClass().getName()
        + "\n IdReserva:       "+ getIdReserva()       
        + "\n Nombre:       "+ getNombre() 
        + "\n Apellido:     "+ getApellido()  
        + "\n Telefono:     "+ getTelefono()
        + "\n TipoHabitacion:"+ getTipoHabitacion()
        + "\n PrecioHabitacion:"+ getPrecioHabitacion()
        + "\n FechaRegistro:"+ getFechaIngreso()
        + "\n FechaSalida:"  + getFechaSalida()
        + "\n Estado:       "+ getEstado();    
        
       
    }

   
    



    

    
}
    