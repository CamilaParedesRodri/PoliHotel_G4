package DataAccess.DTO;

import java.math.BigDecimal;

public class DTO_PoliHabitacion {
    private Integer IdHabitacion;       
    private String  TipoHabitacion;
    private BigDecimal PrecioHabitacion;
    private Integer Cantidad; 
    private String  FechaRegistro; 
    private String  FechaModifica;
    private String  Estado;

    public DTO_PoliHabitacion() {}
    public DTO_PoliHabitacion(Integer IdHabitacion, String TipoHabitacion, BigDecimal PrecioHabitacion, Integer Cantidad, String FechaRegistro, String FechaModifica, String Estado) {
        this.IdHabitacion = IdHabitacion;
        this.TipoHabitacion = TipoHabitacion;
        this.PrecioHabitacion = PrecioHabitacion;
        this.Cantidad = Cantidad;
        this.FechaRegistro = FechaRegistro;
        this.FechaModifica = FechaModifica;
        this.Estado = Estado;
    } 

    public DTO_PoliHabitacion(Integer IdHabitacion, String TipoHabitacion, Integer PrecioHabitacion, Integer Cantidad, String FechaRegistro, String FechaModifica, String Estado) {
        this(IdHabitacion, TipoHabitacion, BigDecimal.valueOf(PrecioHabitacion), Cantidad, FechaRegistro, FechaModifica, Estado);
    }

    public DTO_PoliHabitacion(String TipoHabitacion, BigDecimal PrecioHabitacion, Integer Cantidad) {
        this.TipoHabitacion = TipoHabitacion;
        this.PrecioHabitacion = PrecioHabitacion;
        this.Cantidad = Cantidad;
    }

    public DTO_PoliHabitacion(String TipoHabitacion, Integer PrecioHabitacion, Integer Cantidad) {
        this(TipoHabitacion, BigDecimal.valueOf(PrecioHabitacion), Cantidad);
    }

    public Integer getIdHabitacion() {
        return IdHabitacion;
    }
    public void setIdHabitacion(Integer idHabitacion) {
        IdHabitacion = idHabitacion;
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
    public Integer getCantidad() {
        return Cantidad;
    }
    public void setCantidad(Integer cantidad) {
        Cantidad = cantidad;
    }
    public String getFechaRegistro() {
        return FechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        FechaRegistro = fechaRegistro;
    }
    public String getFechaModifica() {
        return FechaModifica;
    }
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
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
        + "\n IdHabitacion:         "+ getIdHabitacion()       
        + "\n TipoHabitacion:       "+ getTipoHabitacion() 
        + "\n Precio:               "+ getPrecioHabitacion()  
        + "\n Cantidad:             "+ getCantidad() 
        + "\n FechaRegistro:        "+ getFechaRegistro()
        + "\n FechaModifica:        "+ getFechaModifica()
        + "\n Estado:               "+ getEstado();    
        
       
    }
}
