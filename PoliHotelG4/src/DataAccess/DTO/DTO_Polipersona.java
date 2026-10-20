package DataAccess.DTO;

public class DTO_Polipersona {

    private Integer IdPersona;       
    private String  Nombre;
    private String  Apellido;
    private String  Telefono; 
    private String  Estado;
    private String  FechaRegistro; 
    private String  FechaModifica;
    
    
    public DTO_Polipersona() {}
    public DTO_Polipersona(Integer IdPersona, String Nombre, String Apellido, String Telefono, String FechaRegistro, String FechaModifica, String Estado) {
        this.IdPersona = IdPersona;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
        this.FechaRegistro = FechaRegistro;
        this.FechaModifica = FechaModifica;
        this.Estado = Estado;
    }

    public DTO_Polipersona(String Nombre, String Apellido, String Telefono) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Telefono = Telefono;
    }


    public Integer getIdPersona() {
        return IdPersona;
    }
    public void setIdPersona(Integer idPersona) {
        IdPersona = idPersona;
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

         
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
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

    @Override
    public String toString(){
        return getClass().getName()
        + "\n IdPersona:       "+ getIdPersona()       
        + "\n Nombre:       "+ getNombre() 
        + "\n Apellido:     "+ getApellido()  
        + "\n Telefono:     "+ getTelefono() 
        + "\n FechaRegistro:"+ getFechaRegistro()
        + "\n FechaModifica:"+ getFechaModifica()
        + "\n Estado:       "+ getEstado();    
        
       
    }
   
    
}
