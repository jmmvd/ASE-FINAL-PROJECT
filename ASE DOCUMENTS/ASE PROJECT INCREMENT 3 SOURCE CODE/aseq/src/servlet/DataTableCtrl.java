package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import net.datatables.wrapper.DataTableWrapper;
import net.datatables.wrapper.model.DataTable;
import net.datatables.wrapper.model.FnCallBack;


/**
 * 
 * @author xuantuan.bui
 *
 */
public class DataTableCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DataTableCtrl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		
		DataTableWrapper tableWrapper = new DataTableWrapper();
		DataTable<Student> table = new DataTable<>(Student.class);
		
		//table.bServerSide = true;
		table.bDestroy = true;
		table.sPaginationType = "full_numbers";
		table.fnServerData = new FnCallBack("fnDataTablesPipeline");
		
		tableWrapper.setDataHandler(StudentDataHandler.class);
		tableWrapper.setDataTable(table);

		try {
			tableWrapper.wrap(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
