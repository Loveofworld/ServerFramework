package com.loveofworld.mvc.controller.abstracts;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.loveofworld.mvc.common.abstracts.CommonService;
import com.loveofworld.mvc.view.View;


/*

- Method prototype sample about return View - ModelAndView :
	@RequestMapping(value="ServicName/MethodName.do", method='RequestMethod.POST or RequestMethod.GET')
	@ResponseBody
	public ModelAndView functionName(HttpServletRequest request, Params params){
		
		....
		
		return  getView('Result View Name');
	}

- myBatis or iBatis Database Handler
 	boolean DAOService.insertInfo(String sqlName, Object object);
 	boolean DAOService.deleteInfo(String sqlName, Object object);
 	boolean DAOService.updateInfo(String sqlName, Object object);
 	Object  DAOService.selectInfo(String sqlName, Object object);
 	Object  DAOService.selectListInfo(String sqlName, Object object);
 	Object  DAOService.selectMapInfo(String sqlName, Object object, String columnId);

*/

public abstract class ViewControllerSevice extends CommonService{
	
	protected View view;

	protected ViewControllerSevice(){
		this.setView(View.getInstance());
	}
	
	protected ModelAndView getView(String viewName, Map<String, Object> mav) {
		return this.view.getView(viewName, mav);
	}
	
	protected ModelAndView getView(String viewName) {
		return this.view.getView(viewName);
	}

	private void setView(View view) {
		this.view = view;
	}
	
}
