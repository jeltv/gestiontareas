package es.securitasdirect.tareas.web.controller.task;

import es.securitasdirect.tareas.model.TareaAviso;
import es.securitasdirect.tareas.model.TareaMantenimiento;
import es.securitasdirect.tareas.model.external.Pair;
import es.securitasdirect.tareas.service.ExternalDataService;
import es.securitasdirect.tareas.service.QueryTareaService;
import es.securitasdirect.tareas.web.controller.BaseController;
import es.securitasdirect.tareas.web.controller.dto.TareaResponse;
import es.securitasdirect.tareas.web.controller.dto.request.maintenancetask.MaintenanceTaskCreateRequest;
import es.securitasdirect.tareas.web.controller.dto.request.maintenancetask.MaintenanceTaskFinalizeRequest;
import es.securitasdirect.tareas.web.controller.dto.request.notificationtask.*;
import es.securitasdirect.tareas.web.controller.dto.support.BaseResponse;
import es.securitasdirect.tareas.web.controller.dto.support.DummyResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wso2.ws.dataservice.DataServiceFault;

import javax.inject.Inject;
import java.util.List;

/**
 * @author jel
 */

@Controller
@RequestMapping("/notificationtask")
public class NotificationTaskController extends BaseController {

    @Inject
    private QueryTareaService queryTareaService;
    @Inject
    private ExternalDataService externalDataService;

    @RequestMapping(value = "/gettask", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    TareaResponse getNotificationTask(
            @RequestParam(value = "ccUserId", required = true) String ccUserId,
            @RequestParam(value = "callingList", required = true) String callingList,
            @RequestParam(value = "tareaId", required = true) String tareaId
    ) throws DataServiceFault {
        LOGGER.debug("Get notification task for params: \nccUserId:{}\ncallingList:{}\ntareaId:{}",ccUserId, callingList, tareaId);
        TareaAviso task = (TareaAviso)queryTareaService.queryTarea(ccUserId, callingList, tareaId);
        LOGGER.debug("Notification task obtained from service: \n{}", task);
        return toTareaResponse(task);
    }

    @RequestMapping(value = "/getClosing", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    List<Pair> getClosing() throws DataServiceFault {
        List<Pair> closingList = externalDataService.getClosing();
        return closingList;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationTaskController.class);

    @RequestMapping(value = "/aplazar", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse postpone( @RequestBody PostponeNotificationTaskRequest request) {
        LOGGER.debug("Aplazando tarea\nRequest:{}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.postpone.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.postpone.error"));
        }
        LOGGER.debug("Aplazamiento de tarea\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/atras", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse modify( @RequestBody BackNotificationTaskRequest request) {
        LOGGER.debug("Atrás\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.back.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.back.error"));
        }
        LOGGER.debug("Atrás tarea de aviso\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/modificar", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse modify( @RequestBody ModifyNotificationTaskRequest request) {
        LOGGER.debug("Modificar tarea\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.modify.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.modify.error"));
        }
        LOGGER.debug("Modificación de tarea\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/crearmantenimiento", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse modify( @RequestBody CreateMaintenanceNotificationTaskRequest request) {
        LOGGER.debug("Crear mantenimiento\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.createMaintenance.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.createMaintenance.error"));
        }
        LOGGER.debug("Creación de mantenimieto\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/descartar", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse discard( @RequestBody DiscardNotificationTaskRequest request) {
        LOGGER.debug("Descartar\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.discard.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.discard.error"));
        }
        LOGGER.debug("Descarte\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/finalizar", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse discard( @RequestBody FinalizeNotificationTaskRequest request) {
        LOGGER.debug("Finalizar\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.finalize.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.finalize.error"));
        }
        LOGGER.debug("Finalización\nResponse:{}", response);
        return response;
    }

}
