package cybersoft.java12.jsp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.jsp.model.Customer;
import cybersoft.java12.jsp.repository.CustomerRepository;
import cybersoft.java12.jsp.service.CustomerService;
import cybersoft.java12.jsp.util.PathUtils;
import cybersoft.java12.jsp.util.UrlUtils;

@WebServlet(name = "customerServlet", urlPatterns = {
		UrlUtils.CUSTOMER_DASHBOARD,
		UrlUtils.CUSTOMER_ADD,
		UrlUtils.CUSTOMER_UPDATE,
		UrlUtils.CUSTOMER_DELETE
})
public class CustomerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService service;
	private CustomerRepository repository;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new CustomerService();
		repository = new CustomerRepository();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
//		resp.setCharacterEncoding("utf-8");
//		resp.setContentType("html/text");
////		req.setCharacterEncoding("uft-8");
		
		switch (path) {
		case UrlUtils.CUSTOMER_DASHBOARD: 
			List<Customer> cus = repository.findAllCustomer();
			req.setAttribute("customers", cus);
			req.getRequestDispatcher(PathUtils.CUSTOMER_DASHBOARD).forward(req, resp);
			
//			List<Customer> customers = service.findAllCustomers();
//			req.setAttribute("customers", customers);
//			req.getRequestDispatcher(PathUtils.CUSTOMER_DASHBOARD)
//				.forward(req, resp);
			break;
			
		case UrlUtils.CUSTOMER_ADD:
			Customer customerToAdd = new Customer();
			
			customerToAdd.setName(req.getParameter("name"));
			customerToAdd.setAddress(req.getParameter("address"));
			customerToAdd.setEmail(req.getParameter("email"));
			
			req.setAttribute("customer", customerToAdd);
			req.getRequestDispatcher(PathUtils.CUSTOMER_ADD).forward(req, resp);
			
			
//			req.getRequestDispatcher(PathUtils.CUSTOMER_ADD)
//			.forward(req, resp);
			break;
		
		case UrlUtils.CUSTOMER_UPDATE:
			int codeToBeUpdate = Integer.parseInt(req.getParameter("code"));
			Customer customerToUpdate = repository.findCustomerByCode(codeToBeUpdate);
			req.setAttribute("customer", customerToUpdate);// đẩy đi
			req.getRequestDispatcher(PathUtils.CUSTOMER_UPDATE).forward(req, resp);
			
//			int codeToUpdate = Integer.parseInt(req.getParameter("code"));
//			Customer customerToUpdate = service.findCustomerByCode(codeToUpdate);
//			req.setAttribute("customer", customerToUpdate);
//			req.getRequestDispatcher(PathUtils.CUSTOMER_UPDATE)
//			.forward(req, resp);
			break;
			
		case UrlUtils.CUSTOMER_DELETE:
			int codeToBeDelete = Integer.parseInt(req.getParameter("code"));
			repository.deleteCustomerByCode(codeToBeDelete); // đã xoá ở DB
			
			resp.sendRedirect(req.getContextPath() + UrlUtils.CUSTOMER_DASHBOARD);

//			int codeToBeDeleted = Integer.parseInt(req.getParameter("code"));
//			service.deleteCustomerByCode(codeToBeDeleted);
//			resp.sendRedirect(req.getContextPath() + UrlUtils.CUSTOMER_DASHBOARD);
			break;
		
		default:
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("uft-8");
		String path = req.getServletPath();
		
		
		
		switch (path) {
			case UrlUtils.CUSTOMER_ADD:
				Customer cus = new Customer();
				
				cus.setName(req.getParameter("name"));
				cus.setAddress(req.getParameter("address"));
				cus.setEmail(req.getParameter("email"));
				
				repository.addNewCustomer(cus);
				resp.sendRedirect(req.getContextPath() + UrlUtils.CUSTOMER_DASHBOARD);
				
				
//				Customer newCustomer = new Customer();
//				newCustomer.setCode(Integer.parseInt(req.getParameter("code")));
//				newCustomer.setName(req.getParameter("name"));
//				newCustomer.setAddress(req.getParameter("address"));
//				newCustomer.setEmail(req.getParameter("email"));
//				
//				service.addNewCustomer(newCustomer);
//				
//				resp.sendRedirect(req.getContextPath() + UrlUtils.CUSTOMER_DASHBOARD);
				break;
			case UrlUtils.CUSTOMER_UPDATE:
				int codeToBeUpdate = Integer.parseInt(req.getParameter("code"));
				Customer customerToUpdate = repository.findCustomerByCode(codeToBeUpdate);
				
				customerToUpdate.setName(req.getParameter("name"));
				customerToUpdate.setAddress(req.getParameter("address"));
				customerToUpdate.setEmail(req.getParameter("email"));
				repository.updateCustomer(customerToUpdate);
				resp.sendRedirect(req.getContextPath() + UrlUtils.CUSTOMER_DASHBOARD);
				
//				int codeToUpdate = Integer.parseInt(req.getParameter("code"));
//				Customer customerToUpdate = service.findCustomerByCode(codeToUpdate);
//				customerToUpdate.setName(req.getParameter("name"));
//				customerToUpdate.setAddress(req.getParameter("address"));
//				customerToUpdate.setEmail(req.getParameter("email"));
				
//				service.update(customerToUpdate, codeToUpdate);
//				resp.sendRedirect(req.getContextPath() + UrlUtils.CUSTOMER_DASHBOARD);
				break;
		} 
		
			
	}
	
	
}
