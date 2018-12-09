package endpoints;

import java.io.IOException;
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

/**
 * Servlet implementation class ContentLoader
 */
@WebServlet("/ContentLoader")
public class ContentLoader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentLoader() {
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
		// TODO Auto-generated method stub
		Map mapParams = request.getParameterMap();
		String cmd="<unknown>";
		if(mapParams!=null) {
			cmd = ((String[])mapParams.get("cmd"))[0];
		}
		ServletContext context = getServletContext();
		String rootDir= context.getRealPath("/");
		
		BlockHandler block = new BlockHandler(request,response,rootDir);
		
		if( cmd.equals("watch") ){
			try {

				// sleep 5 seconds
				System.out.println("T+0s...");
				Thread.sleep(3000);
				System.out.println("T+5s...");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			response.getWriter().append("Dynamically loading content for cmd=[" + cmd + "] ")
					.append(request.getContextPath());

		}else if( cmd.equals("orders")  ) {
			BlockDataGrid grid = new BlockDataGrid( block, 
									"{GRID-1}", 
									rootDir+"templates/blocks/data_init.js", 
									rootDir+"templates/blocks/data_render.js" );
			
			//block.addChild(grid);
			//app.execute();
			StringBuilder buf = grid.render();
			
			System.out.println("[BLOCK-HTML]\n"+buf+"\n[/BLOCK-HTML]");
			block.writeHTML(buf);

			
		}else {
			response.getWriter().append("Unknown command cmd=[" + cmd + "] ")
			.append(request.getContextPath());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
