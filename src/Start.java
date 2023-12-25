import dao.Business;
import dao.Data;
import view.*;

public class Start {
    private Data data;

    public Start() {
        data = new Data();
        new Business(data);
//        new Business(data);
          new BusinessInterface(data);
//           new DeleteLineInterface(data);
//        new LoginInterface(data);
//        new ErrorInterface("123");
    }
}
