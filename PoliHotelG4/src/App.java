//import DataAccess.DAO.DAO_PoliHabitacion;
import BussinessLogic.BLFactory;
import DataAccess.DAO.DAO_PoliHabitacion;
import DataAccess.DAO.DAO_PoliReserva;
import DataAccess.DTO.DTO_PoliHabitacion;
import DataAccess.DTO.DTO_PoliReseva;
import DataAccess.DAO.DAO_Polipersona;

public class App {
    public static void main(String[] args) throws Exception {
        BLFactory<DTO_PoliReseva> oDAO = new BLFactory<>(DAO_PoliReserva::new);
        for(DTO_PoliReseva r : oDAO.getAll()) {
            System.out.println(r);
           
        }
    }
}
