package endpoints;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apps.BlockHandler;
import bocks.BlockDataGrid;
import react.RePanelDynamic;

/**
 * Servlet implementation class JavascriptLoader
 */
@WebServlet("/JavascriptLoader")
public class JavascriptLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavascriptLoader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map mapParams = request.getParameterMap();
		String cmd="<unknown>";
		if(mapParams!=null) {
			cmd = ((String[])mapParams.get("cmd"))[0];
		}
		ServletContext context = getServletContext();
		String rootDir= context.getRealPath("/");
				
		System.out.println("JavascriptLoader cmd="+cmd);
		
		if( cmd.equals("app-home-dynamic") ){

			System.out.println("app-home-dynamic loading...");

			StringBuilder buf = new StringBuilder();			
			
			String[][] params = {
		               { "odata",
		                 "https://js.devexpress.com/Demos/SalesViewer/odata/DaySaleDtoes"
		               },
			          { "dataField", "Product", 
			        	"groupIndex", "0"
			          },
			          { "dataField", "Amount",
			        	"caption", "Sale Amount",
			            "dataType", "number",
			            "format", "currency",
			            "alignment", "right"
			         },
			         {
			           "dataField", "Discount",
			            "caption", "Discount %",
			            "dataType", "number",
			            "format", "percent",
			            "alignment", "right",
			            "#allowGrouping", "false",
			            "#cellTemplate", "discountCellTemplate",
			            "cssClass", "bullet"
			            },
			            {
			                "dataField", "SaleDate",
			                "dataType", "date"
			            },
			            {
			                "dataField", "Region",
			                "dataType", "string"
			            },
			            {
			                "dataField", "Sector",
			                "dataType", "string",
			            },
			            {
			                "dataField", "Channel",
			                "dataType", "string",
			            },
			            {
			                "dataField", "Customer",
			                "dataType", "string",
			                "#width", "150"
			            }
			};
			
			RePanelDynamic canvas0 = new RePanelDynamic("{APP-HOME-DYNAMIC}", rootDir+"templates/blocks/data_render.js", params );						
			canvas0.writeJS(buf);
			
			response.setContentType("text/javascript");	
			PrintWriter pwOutput;
			try {
					pwOutput = response.getWriter();
					pwOutput.print(buf);		
			} catch (IOException e) {
					e.printStackTrace();
			}//try	
			
		}else {
			response.setContentType("text/javascript");			
			PrintWriter pwOutput;
			try {
					pwOutput = response.getWriter();
					pwOutput.print("alert('unknown script');");		
			} catch (IOException e) {
					e.printStackTrace();
			}//try				
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
