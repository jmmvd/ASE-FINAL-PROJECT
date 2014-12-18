package servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.datatables.wrapper.AjaxRequest;
import net.datatables.wrapper.IDataHandler;

import model.Student;

/**
 * Data handler for Student class.
 * Note: the table was initialized as server side
 * the ItotalRecords method must return the size of origin list.
 * the ITotalDisplayRecords method must return size of list after filtered.
 * @author xuantuan.bui
 *
 */
public class StudentDataHandler implements IDataHandler<Student> {
	List<Student> list;
	int itotalRecords;
	int iTotalDisplay;
	
	public StudentDataHandler() {
		list = getAllStudents();
		itotalRecords = iTotalDisplay = list.size();
	}
	@Override
	public List<Student> getListData(AjaxRequest tableRequest,
			HttpServletRequest request) {
		return filterStudents(tableRequest);
	}

	@Override
	public int getITotalRecords() {
		return itotalRecords;
	}

	@Override
	public int getITotalDisplayRecords() {
		return iTotalDisplay;
	}
	
	
	
	//###################3Example data #########################3333
	public static List<Student> filterStudents(AjaxRequest tableReq) {

		List<Student> students = getAllStudents();
		//if request equals null means datatable is inited as static table.
		if (tableReq != null) {
			List<Student> tmpStudents = new ArrayList<>();
			for (int i = tableReq.iDisplayStart; i < students.size()
					&& i <= tableReq.iDisplayStart + tableReq.iDisplayLength; i++) {
				tmpStudents.add(students.get(i));
			}
			return tmpStudents;
		} else {
			return students;
		}
	}
	
	

	public static List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		//students.add(new Student(1, "ctu3an", 30, "120070903", null));
		students.add(new Student(16150480, "jaya sai", 22, "ase", "4922 grand ave"));
		students.add(new Student(16150482, "rahul", 22, "ase", "4922 grand ave"));
		students.add(new Student(16150481, "chaitanya", 22, "ase", "4922 grand ave"));
		students.add(new Student(16150483, "naveen", 22, "ase", "4922 grand ave"));
		return students;
	}

}

